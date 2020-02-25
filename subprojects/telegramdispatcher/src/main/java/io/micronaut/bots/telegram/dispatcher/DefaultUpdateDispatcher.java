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
import io.micronaut.bots.telegram.core.ChatType;
import io.micronaut.bots.telegram.core.Send;
import io.micronaut.bots.telegram.core.SendMessage;
import io.micronaut.bots.telegram.core.Update;
import io.micronaut.bots.telegram.httpclient.TelegramBot;
import io.micronaut.context.ApplicationContext;
import io.micronaut.inject.qualifiers.Qualifiers;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class DefaultUpdateDispatcher implements UpdateDispatcher {

    private final ApplicationContext applicationContext;

    public DefaultUpdateDispatcher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Optional<Send> dispatch(@NonNull TelegramBot telegramBot, @NonNull Update update) {
        String text = CommandHandler.parseText(update);
        String type = CommandHandler.parseType(update);

        boolean isPrivateMessage = (type != null && type.equals(ChatType.PRIVATE.toString()));

        boolean isMessageTargetToTheBot = text != null && text.contains(telegramBot.getAtUsername());

        if (text != null && (isPrivateMessage || isMessageTargetToTheBot)) {
            if (applicationContext.containsBean(CommandHandler.class, Qualifiers.byName(text))) {
                CommandHandler handler = applicationContext.getBean(CommandHandler.class, Qualifiers.byName(text));
                return handler.handle(update);
            }
            return handleUpdateNotProcessedByCommands(telegramBot, update);
        }
        return Optional.empty();
    }

    protected Optional<Send> handleUpdateNotProcessedByCommands(@NonNull TelegramBot telegramBot, Update update) {
        Integer chatId = CommandHandler.parseChatId(update);
        if (chatId != null) {
            return Optional.of(defaultMessage(chatId));
        }
        return Optional.empty();
    }

    protected SendMessage defaultMessage(Integer chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("I don't understand. Please type /help");
        return sendMessage;
    }

}
