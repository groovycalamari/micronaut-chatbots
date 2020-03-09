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

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.bots.core.ChatBotMessageReceive;
import io.micronaut.bots.core.ChatBotMessageParser;
import io.micronaut.bots.core.ChatBotMessageSend;
import io.micronaut.bots.core.MessageComposer;
import io.micronaut.bots.core.ParseMode;
import io.micronaut.bots.telegram.core.SendMessage;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Singleton
public class TelegramMessageComposer implements MessageComposer {

    protected final ChatBotMessageParser parser;

    public TelegramMessageComposer(ChatBotMessageParser parser) {
        this.parser = parser;
    }

    @Override
    public <T extends ChatBotMessageSend> Optional<T> compose(@NonNull @NotBlank String text,
                                                              @NonNull @Valid @NotNull ChatBotMessageReceive update) {
        if (!(this.parser instanceof TelegramChatBotMessageParser)) {
            return Optional.empty();
        }
        return (Optional) ((TelegramChatBotMessageParser)parser).parseChatId(update).map(chatId -> {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(text);
            return sendMessage;
        });
    }

    @Override
    public <T extends ChatBotMessageSend> Optional<T> compose(@NonNull @NotBlank String text,
                                                              @NonNull ParseMode parseMode,
                                                              @NonNull @Valid @NotNull ChatBotMessageReceive update) {
        if (!(this.parser instanceof TelegramChatBotMessageParser)) {
            return Optional.empty();
        }
        return (Optional) compose(text, update).map(chatBotResponse -> {
            if (chatBotResponse instanceof SendMessage) {
                SendMessage sendMessage = (SendMessage) chatBotResponse;
                sendMessage.setParseMode(parseMode.toString());
                return sendMessage;
            }
            return chatBotResponse;
        });
    }
}
