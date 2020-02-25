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

import io.micronaut.bots.telegram.core.Send;
import io.micronaut.bots.telegram.core.Update;
import io.micronaut.bots.telegram.dispatcher.UpdateDispatcher;
import io.micronaut.function.aws.MicronautRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TelegramWebhookHandler extends MicronautRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(TelegramWebhookHandler.class);
    private static final String ENV_TOKEN = "TOKEN";
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String TEXT_PLAIN = "text/plain";

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private UpdateDispatcher updateDispatcher;

    public TelegramWebhookHandler() {

    }

    @Override
    public APIGatewayProxyResponseEvent execute(APIGatewayProxyRequestEvent input) {
        LOG.info("body is " + input.getBody());


        String path = input.getPath();
        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
        if (!(("/" + System.getenv(ENV_TOKEN)).equals(path))) {
            apiGatewayProxyResponseEvent.setStatusCode(401);
            return apiGatewayProxyResponseEvent;
        }
        Map<String, String> headers = new HashMap<>();

        try {
            if (input.getBody() != null && !input.getBody().trim().isEmpty()) {
                Update update = objectMapper.readValue(input.getBody(), Update.class);

                Optional<Send> message = updateDispatcher.dispatch(update);
                if (message.isPresent()) {
                    Send send = message.get();
                    try {
                        String json  = objectMapper.writeValueAsString(send);
                        LOG.info("response json is:" + json);
                        headers.put(CONTENT_TYPE, APPLICATION_JSON);
                        apiGatewayProxyResponseEvent.setBody(json);
                        apiGatewayProxyResponseEvent.setStatusCode(200);
                    } catch (JsonProcessingException e) {
                        LOG.info("json proccession error marshalling the send message " + e.getMessage());
                        headers.put(CONTENT_TYPE, TEXT_PLAIN);
                        apiGatewayProxyResponseEvent.setBody("error converting message to json string");
                        apiGatewayProxyResponseEvent.setStatusCode(400);
                    }
                } else {
                    apiGatewayProxyResponseEvent.setStatusCode(200);
                }
            } else {
                LOG.warn("body is null");
                headers.put(CONTENT_TYPE, TEXT_PLAIN);
                apiGatewayProxyResponseEvent.setBody("body is null");
                apiGatewayProxyResponseEvent.setStatusCode(400);
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
