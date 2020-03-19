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
package io.micronaut.chatbots.googlechat.httpserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.bots.core.ChatBotMessageDispatcher;
import io.micronaut.bots.core.ChatBotMessageSend;
import io.micronaut.bots.telegram.core.Send;
import io.micronaut.bots.telegram.core.Update;
import io.micronaut.bots.telegram.httpclient.TelegramBot;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class WebhookController {
    private static final Logger LOG = LoggerFactory.getLogger(WebhookController.class);

    private final Map<String, TelegramBot> configuration;
    private final ChatBotMessageDispatcher messageDispatcher;
    private final ObjectMapper objectMapper;

    public WebhookController(Collection<TelegramBot> telegramBots,
                             ChatBotMessageDispatcher messageDispatcher,
                             ObjectMapper objectMapper) {
        this.messageDispatcher = messageDispatcher;
        this.objectMapper = objectMapper;
        this.configuration = new HashMap<>();

        for (TelegramBot bot : telegramBots) {
            this.configuration.put(bot.getToken(), bot);
        }
    }

    @Post("/{token}")
    public HttpResponse update(@PathVariable String token,
                               @Body Update update) {
        if (!configuration.containsKey(token)) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Configuration does not contain supplied token. rejected update {}", update.toString());
            }
            return HttpResponse.unauthorized();
        }

        Optional<ChatBotMessageSend> message = messageDispatcher.dispatch(configuration.get(token), update);
        if (message.isPresent()) {
            ChatBotMessageSend chatBotResponse = message.get();
            if (LOG.isInfoEnabled()) {
                LOG.info("Returning {}", chatBotResponse.toString());
            }
            if (chatBotResponse instanceof Send) {
                Send send = ((Send) chatBotResponse);
                return HttpResponse.ok(send);
            }
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("Returning just 200 OK");
        }
        return HttpResponse.ok();
    }
}