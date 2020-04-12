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
package io.micronaut.bots.telegram.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micronaut.bots.core.ChatBotMessageDispatcher;
import io.micronaut.bots.core.ChatBotMessageSend;
import io.micronaut.bots.telegram.core.Send;
import io.micronaut.bots.telegram.core.Update;
import io.micronaut.bots.telegram.httpclient.TelegramBot;
import io.micronaut.context.ApplicationContextBuilder;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.function.aws.MicronautRequestHandler;
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

@Introspected
public class TelegramWebhookHandler extends MicronautRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(TelegramWebhookHandler.class);
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String TEXT_PLAIN = "text/plain";
    public static final String PATH_START = "/";
    public static final String ENV_EAGER_INIT_CONFIGURATION = "MICRONAUT_EAGER_INIT_CONFIGURATION";
    public static final String ENV_EAGER_INIT_SINGLETONS = "MICRONAUT_EAGER_INIT_SINGLETONS";
    public static final boolean DEFAULT_EAGER_INIT_CONFIGURATION = true;
    public static final boolean DEFAULT_EAGER_INIT_SINGLETONS = true;

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private ChatBotMessageDispatcher messageDispatcher;

    @Inject
    Collection<TelegramBot> telegramBots;

    public TelegramWebhookHandler() {
    }

    @Nonnull
    @Override
    protected ApplicationContextBuilder newApplicationContextBuilder() {
        ApplicationContextBuilder builder = super.newApplicationContextBuilder();

        boolean eagerInitSingletons = DEFAULT_EAGER_INIT_SINGLETONS;
        if (System.getenv(ENV_EAGER_INIT_SINGLETONS) != null) {
            eagerInitSingletons = Boolean.parseBoolean(System.getenv(ENV_EAGER_INIT_SINGLETONS));
        }
        if (LOG.isTraceEnabled()) {
            LOG.trace("eager init singletons {}", eagerInitSingletons);
        }
        builder.eagerInitSingletons(eagerInitSingletons);
        // If singletons are eagerly loaded, it does not matter whether you set eagerInitConfiguration,
        // ConfigurationReaders are singletons and thus they will be eagerly loaded due to eagerInitSingletons
        if (!eagerInitSingletons) {
            boolean eagerInitConfiguration = DEFAULT_EAGER_INIT_CONFIGURATION;
            if (System.getenv(ENV_EAGER_INIT_CONFIGURATION) != null) {
                eagerInitConfiguration = Boolean.parseBoolean(System.getenv(ENV_EAGER_INIT_CONFIGURATION));
            }
            if (LOG.isTraceEnabled()) {
                LOG.trace("eager init configuration {}", eagerInitConfiguration);
            }
            builder.eagerInitConfiguration(eagerInitConfiguration);
        }
        return builder;
    }

    @Override
    public APIGatewayProxyResponseEvent execute(APIGatewayProxyRequestEvent input) {

        LOG.info("body is " + input.getBody());
        final String path = input.getPath();
        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();

        if (telegramBots.stream().noneMatch(bot -> (PATH_START + bot.getToken()).equals(path))) {
            LOG.warn("not telegram bot found for token");
            apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.UNAUTHORIZED.getCode());
            return apiGatewayProxyResponseEvent;
        }
        Map<String, String> headers = new HashMap<>();

        try {
            Optional<TelegramBot> telegramBotOptional = telegramBots.stream().filter(bot -> (PATH_START + bot.getToken()).equals(path)).findFirst();
            if (telegramBotOptional.isPresent()) {
                TelegramBot telegramBot = telegramBotOptional.get();

                if (input.getBody() != null && !input.getBody().trim().isEmpty()) {
                    Update update = objectMapper.readValue(input.getBody(), Update.class);
                    Optional<ChatBotMessageSend> message = messageDispatcher.dispatch(telegramBot, update);
                    if (message.isPresent()) {
                        ChatBotMessageSend chatBotResponse = message.get();
                        if (chatBotResponse instanceof Send) {
                            Send send = ((Send) chatBotResponse);
                            try {
                                String json  = objectMapper.writeValueAsString(send);
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
                            apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.OK.getCode());
                        }
                    } else {
                        apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.OK.getCode());
                    }
                } else {
                    LOG.warn("body is null");
                    headers.put(CONTENT_TYPE, TEXT_PLAIN);
                    apiGatewayProxyResponseEvent.setBody("body is null");
                    apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.BAD_REQUEST.getCode());
                }
            } else {
                LOG.warn("telegram bot not found");
                headers.put(CONTENT_TYPE, TEXT_PLAIN);
                apiGatewayProxyResponseEvent.setBody("telegram bot not found");
                apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.BAD_REQUEST.getCode());
            }
            apiGatewayProxyResponseEvent.setHeaders(headers);
            return apiGatewayProxyResponseEvent;

        } catch (IOException e) {
            LOG.error("error binding body " + input.getBody() + " to " + Update.class.getSimpleName());
            apiGatewayProxyResponseEvent.setStatusCode(400);
            return apiGatewayProxyResponseEvent;
        }
    }

}
