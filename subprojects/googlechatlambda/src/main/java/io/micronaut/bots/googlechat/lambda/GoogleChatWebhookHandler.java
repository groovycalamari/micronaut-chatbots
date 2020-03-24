/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020 Sergio del Amo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.bots.googlechat.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.bots.core.ChatBotMessageDispatcher;
import io.micronaut.bots.core.ChatBotMessageSend;
import io.micronaut.bots.googlechat.core.Event;
import io.micronaut.bots.googlechat.core.GoogleChatBot;
import io.micronaut.bots.googlechat.security.GoogleChatBearerTokenVerifier;
import io.micronaut.bots.googlechat.security.UnauthorizedGoogleChatToken;
import io.micronaut.context.ApplicationContextBuilder;
import io.micronaut.function.aws.MicronautRequestHandler;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GoogleChatWebhookHandler extends MicronautRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(GoogleChatWebhookHandler.class);
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String TEXT_PLAIN = "text/plain";
    public static final String PATH_START = "/";

    static String AUDIENCE = System.getenv("PROJECT_ID");

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private ChatBotMessageDispatcher messageDispatcher;

    @Inject
    Collection<GoogleChatBot> googleChatBots;

    @Inject
    private GoogleChatBearerTokenVerifier googleChatBearerTokenVerifier;

    @Nonnull
    @Override
    protected ApplicationContextBuilder newApplicationContextBuilder() {
        ApplicationContextBuilder builder = super.newApplicationContextBuilder();
        builder.eagerInitConfiguration(true);
        builder.eagerInitSingletons(true);
        return builder;
    }

    @Override
    public APIGatewayProxyResponseEvent execute(APIGatewayProxyRequestEvent input) {
        LOG.info("body is {}", input.getBody());
        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();

        Map<String, String> headers = new HashMap<>();

        String bearerToken = input.getHeaders().get(HttpHeaders.AUTHORIZATION);
        LOG.info("bearerToken is {}", bearerToken);

        if (bearerToken == null) {
            LOG.warn("bearerToken is null");
            headers.put(CONTENT_TYPE, TEXT_PLAIN);
            apiGatewayProxyResponseEvent.setBody("bearerToken is null");
            apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.UNAUTHORIZED.getCode());
            return apiGatewayProxyResponseEvent;
        }

        try {
            googleChatBearerTokenVerifier.verify(bearerToken);
        } catch (UnauthorizedGoogleChatToken e) {
            LOG.warn("Unauthorized google chat token {}", e.getMessage());
            headers.put(CONTENT_TYPE, TEXT_PLAIN);
            apiGatewayProxyResponseEvent.setBody(e.getMessage());
            apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.UNAUTHORIZED.getCode());
            return apiGatewayProxyResponseEvent;
        }

        try {
            Optional<GoogleChatBot> botOptional = googleChatBots.stream().filter(bot -> bot.getProjectId().equals(AUDIENCE)).findFirst();
            if (botOptional.isPresent()) {
                GoogleChatBot bot = botOptional.get();
                if (input.getBody() != null && !input.getBody().trim().isEmpty()) {
                    Event update = objectMapper.readValue(input.getBody(), Event.class);
                    LOG.info("dispatching message to bot {}", bot.getAtUsername());
                    Optional<ChatBotMessageSend> message = messageDispatcher.dispatch(bot, update);
                    if (message.isPresent()) {
                        try {
                            String json  = objectMapper.writeValueAsString(message);
                            LOG.info("response json is:" + json);
                            headers.put(CONTENT_TYPE, APPLICATION_JSON);
                            apiGatewayProxyResponseEvent.setBody(json);
                            apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.OK.getCode());
                        } catch (JsonProcessingException e) {
                            LOG.info("json proccession error marshalling the send message " + e.getMessage());
                            headers.put(CONTENT_TYPE, TEXT_PLAIN);
                            apiGatewayProxyResponseEvent.setBody("error converting message to json string");
                            apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.BAD_REQUEST.getCode());
                        }
                    } else {
                        LOG.info("Empty ChatBotMessageSend");
                        apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.OK.getCode());
                    }
                } else {
                    LOG.warn("body is null");
                    headers.put(CONTENT_TYPE, TEXT_PLAIN);
                    apiGatewayProxyResponseEvent.setBody("body is null");
                    apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.BAD_REQUEST.getCode());
                }
            } else {
                LOG.warn("google chat bot not found");
                headers.put(CONTENT_TYPE, TEXT_PLAIN);
                apiGatewayProxyResponseEvent.setBody("google chat bot not found");
                apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.BAD_REQUEST.getCode());
            }
            apiGatewayProxyResponseEvent.setHeaders(headers);
            return apiGatewayProxyResponseEvent;

        } catch (IOException e) {
            LOG.error("error binding body " + input.getBody() + " to " + Event.class.getSimpleName());
            apiGatewayProxyResponseEvent.setStatusCode(400);
            return apiGatewayProxyResponseEvent;
        }
    }

}
