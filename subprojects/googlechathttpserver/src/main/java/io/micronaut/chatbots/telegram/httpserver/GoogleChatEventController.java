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
package io.micronaut.chatbots.telegram.httpserver;

import io.micronaut.bots.core.ChatBotMessageDispatcher;
import io.micronaut.bots.core.ChatBotMessageSend;
import io.micronaut.bots.googlechat.core.Event;
import io.micronaut.bots.googlechat.core.GoogleChatBot;
import io.micronaut.bots.googlechat.security.GoogleChatBearerTokenVerifier;
import io.micronaut.bots.googlechat.security.UnauthorizedGoogleChatToken;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class GoogleChatEventController {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleChatEventController.class);

    protected final GoogleChatBearerTokenVerifier googleChatBearerTokenVerifier;
    protected final ChatBotMessageDispatcher messageDispatcher;
    private final Collection<GoogleChatBot> googleChatBots;

    public GoogleChatEventController(GoogleChatBearerTokenVerifier googleChatBearerTokenVerifier,
                                     Collection<GoogleChatBot> googleChatBots,
                                     ChatBotMessageDispatcher messageDispatcher) {
        this.googleChatBearerTokenVerifier = googleChatBearerTokenVerifier;
        this.messageDispatcher = messageDispatcher;
        this.googleChatBots = googleChatBots;
    }

    @Post("/")
    public HttpResponse update(@Body Event event, @Header(HttpHeaders.AUTHORIZATION) String authorization) {
        if (LOG.isInfoEnabled()) {
            LOG.info("Received event {}", event.toString());
        }
        if (authorization == null) {
            if (LOG.isWarnEnabled()) {
                LOG.warn("bearerToken is null");
            }
            return HttpResponse.unauthorized().body("bearerToken is null").contentType(MediaType.TEXT_PLAIN_TYPE);
        }
        try {
            List<String> audiencies = googleChatBearerTokenVerifier.verify(authorization);
            if (audiencies == null) {
                if (LOG.isWarnEnabled()) {
                    LOG.warn("audiencies could not be parsed from bearer token {}", authorization);
                }
                return HttpResponse.badRequest()
                        .body("audiencies could not be parsed from bearer token")
                        .contentType(MediaType.TEXT_PLAIN_TYPE);
            }
            Optional<GoogleChatBot> optionalGoogleChatBot = googleChatBots.stream()
                    .filter(bot -> audiencies.contains(bot.getProjectId()))
                    .findFirst();
            if (!optionalGoogleChatBot.isPresent()) {
                if (LOG.isWarnEnabled()) {
                    LOG.warn("no google chat bot found for audiencies {}", audiencies);
                }
                return HttpResponse.badRequest()
                        .body("no google chat bot found for audiencies " + audiencies)
                        .contentType(MediaType.TEXT_PLAIN_TYPE);
            }
            GoogleChatBot chatBot = optionalGoogleChatBot.get();
            Optional<ChatBotMessageSend> message = messageDispatcher.dispatch(chatBot, event);
            if (LOG.isInfoEnabled()) {
                LOG.info("Returning {}", message.toString());
            }
            return HttpResponse.ok(message);

        } catch (UnauthorizedGoogleChatToken e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn("Unauthorized google chat token {}", e.getMessage());
            }
            return HttpResponse.unauthorized().body(e.getMessage()).contentType(MediaType.TEXT_PLAIN_TYPE);
        }
    }
}