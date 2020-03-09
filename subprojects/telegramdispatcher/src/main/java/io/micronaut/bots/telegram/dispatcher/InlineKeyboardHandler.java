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
package io.micronaut.bots.telegram.dispatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.bots.core.ChatBot;
import io.micronaut.bots.core.ChatBotMessageReceive;
import io.micronaut.bots.core.ChatBotMessageSend;
import io.micronaut.bots.core.CommandHandler;
import io.micronaut.bots.core.MessageComposer;
import io.micronaut.bots.telegram.core.InlineKeyboardMarkup;
import io.micronaut.bots.telegram.core.SendMessage;
import io.micronaut.bots.telegram.core.Update;
import io.micronaut.bots.telegram.httpclient.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public abstract class InlineKeyboardHandler implements CommandHandler {
    private static final Logger LOG = LoggerFactory.getLogger(InlineKeyboardHandler.class);

    protected final ObjectMapper objectMapper;
    protected final MessageComposer messageComposer;

    protected InlineKeyboardHandler(ObjectMapper objectMapper,
                                    MessageComposer messageComposer) {
        this.objectMapper = objectMapper;
        this.messageComposer = messageComposer;
    }

    @Override
    public <T extends ChatBotMessageSend> Optional<T> handle(@NonNull ChatBot bot,
                                                             @NonNull ChatBotMessageReceive chatUpdate) {


        if (!(chatUpdate instanceof Update) || !(bot instanceof TelegramBot)) {
            return Optional.empty();
        }
        TelegramBot telegramBot = (TelegramBot) bot;
        Update update = (Update) chatUpdate;
        String text = messageText(telegramBot, update);
        Optional<ChatBotMessageSend> chatBotResponseOptional = messageComposer.compose(text, chatUpdate);
        if (chatBotResponseOptional.isPresent()) {
            ChatBotMessageSend chatBotResponse = chatBotResponseOptional.get();
            if (chatBotResponse instanceof SendMessage) {
                SendMessage sendMessage = (SendMessage) chatBotResponse;
                InlineKeyboardMarkup inlineKeyboardMarkup = createInlineKeyboardMarkup(telegramBot, update);
                Optional<String> json = serializeInlineKeyboardMarkup(inlineKeyboardMarkup);
                if (json.isPresent()) {
                    LOG.error("No keyboard created");
                }
                json.ifPresent(sendMessage::setReplyMarkup);

                return (Optional) Optional.of(sendMessage);
            }
        }
        return Optional.empty();
    }

    @NonNull
    protected abstract String messageText(@NonNull TelegramBot telegramBot,
                                          @NonNull Update update);

    @NonNull
    protected abstract InlineKeyboardMarkup createInlineKeyboardMarkup(@NonNull TelegramBot telegramBot,
                                                                       @NonNull Update update);

    @NonNull
    protected Optional<String> serializeInlineKeyboardMarkup(@NonNull InlineKeyboardMarkup inlineKeyboardMarkup) {
        try {
            return Optional.of(objectMapper.writeValueAsString(inlineKeyboardMarkup));
        } catch (JsonProcessingException e) {
            if (LOG.isErrorEnabled()) {
                LOG.error("error generating serialized JSON of inlineKeyboard", e);
            }
        }
        return Optional.empty();
    }
}
