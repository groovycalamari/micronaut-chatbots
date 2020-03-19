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
package io.micronaut.bots.googlechat.core;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.bots.core.ChatBot;
import io.micronaut.bots.core.ChatBotMessageParser;
import io.micronaut.bots.core.ChatBotMessageReceive;
import io.micronaut.bots.core.ChatBotSpace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.Serializable;
import java.util.Optional;

@Singleton
public class GoogleChatMessageParser implements ChatBotMessageParser {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleChatMessageParser.class);

    @Override
    public Optional<ChatBotSpace> parseChat(@NonNull ChatBotMessageReceive messageReceive) {
        if (!(messageReceive instanceof Event)) {
            LOG.warn("Message not of type Event");
            return Optional.empty();
        }
        Event event = (Event) messageReceive;
        return Optional.of(event.getSpace());
    }

    @Override
    public Optional<String> parseText(@NonNull ChatBotMessageReceive messageReceive) {
        if (!(messageReceive instanceof Event)) {
            LOG.warn("Message not of type Event");
            return Optional.empty();
        }
        Event event = (Event) messageReceive;
        if (event.getAction() !=null) {
            // TODO should we do something with params
            String actionMethodName = event.getAction().getActionMethodName();
            if (actionMethodName != null) {
                return Optional.of(actionMethodName);
            }
        }
        if (event.getMessage() != null) {
            String text = event.getMessage().getText();
            if (text != null) {
                return Optional.of(text);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> parseTextWithoutBotName(@NonNull ChatBot bot, @NonNull ChatBotMessageReceive messageReceive) {
        if (!(messageReceive instanceof Event)) {
            LOG.warn("Message not of type Event");
            return Optional.empty();
        }
        Event event = (Event) messageReceive;

        Optional<String> textOptional = parseText(event);
        if (textOptional.isPresent()) {
            String text = textOptional.get();
            return Optional.of(text.replaceAll(bot.getAtUsername(), "").trim());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Serializable> parseUserId(ChatBotMessageReceive messageReceive) {
        if (!(messageReceive instanceof Event)) {
            LOG.warn("Message not of type Event");
            return Optional.empty();
        }
        Event event = (Event) messageReceive;
        if (event.getMessage() != null) {
            return Optional.ofNullable(event.getMessage().getSender().getName());
        }
        return Optional.empty();
    }
}
