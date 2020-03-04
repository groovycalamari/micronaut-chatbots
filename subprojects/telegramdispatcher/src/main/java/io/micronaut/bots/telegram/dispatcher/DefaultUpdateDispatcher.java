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
import io.micronaut.bots.telegram.core.AnswerCallbackQuery;
import io.micronaut.bots.telegram.core.Chat;
import io.micronaut.bots.telegram.core.ChatType;
import io.micronaut.bots.telegram.core.Send;
import io.micronaut.bots.telegram.core.SendMessage;
import io.micronaut.bots.telegram.core.Update;
import io.micronaut.bots.telegram.httpclient.TelegramBot;
import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpResponse;
import io.micronaut.inject.qualifiers.Qualifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class DefaultUpdateDispatcher implements UpdateDispatcher {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultUpdateDispatcher.class);
    public static final String SPACE = " ";
    public static final String DEFAULT_MESSAGE = "I don't understand. Please type /help";

    protected final ApplicationContext applicationContext;
    protected final UpdateParser updateParser;

    public DefaultUpdateDispatcher(ApplicationContext applicationContext,
                                         UpdateParser updateParser) {
        this.applicationContext = applicationContext;
        this.updateParser = updateParser;
    }

    @Override
    public Optional<Send> dispatch(@NonNull TelegramBot telegramBot, @NonNull Update update) {
        handleCallbackQuery(telegramBot, update);
        if (shouldHandleMessage(telegramBot, update)) {
            Optional<CommandHandler> handler = parseCommandHandler(telegramBot, update);
            if (handler.isPresent()) {
                return handler.get().handle(telegramBot, update);
            }
            return handleUpdateNotProcessedByCommands(telegramBot, update);
        }
        return Optional.empty();
    }

    protected Optional<CommandHandler> parseCommandHandler(@NonNull TelegramBot telegramBot, @NonNull Update update) {
        Optional<String> commandOptional = parseCommandAtUpdate(telegramBot, update);
        if (commandOptional.isPresent()) {
            String command = commandOptional.get();
            if (applicationContext.containsBean(CommandHandler.class, Qualifiers.byName(command))) {
                return Optional.of(applicationContext.getBean(CommandHandler.class, Qualifiers.byName(command)));
            }
        }
        return Optional.empty();
    }

    protected boolean shouldHandleMessage(@NonNull TelegramBot telegramBot, @NonNull Update update) {
        String text = updateParser.parseText(update).orElse(null);
        if (LOG.isInfoEnabled()) {
            LOG.info("text parsed: {}", text);
        }
        String type = updateParser.parseChat(update).map(Chat::getType).orElse(null);
        if (LOG.isInfoEnabled()) {
            LOG.info("type parsed: {}", type);
        }
        boolean isPrivateMessage = (type != null && type.equals(ChatType.PRIVATE.toString()));
        boolean isMessageTargetToTheBot = text != null && text.contains(telegramBot.getAtUsername());
        return (text != null && (isPrivateMessage || isMessageTargetToTheBot));
    }

    protected void handleCallbackQuery(@NonNull TelegramBot telegramBot, @NonNull Update update) {
        if (update.getCallbackQuery() != null) {
            AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
            answerCallbackQuery.setCallbackQueryId(update.getCallbackQuery().getInlineMessageId() != null ?
                    update.getCallbackQuery().getInlineMessageId() : update.getCallbackQuery().getId());
            try {
                HttpResponse rsp = telegramBot.answerCallbackQuery(answerCallbackQuery).blockingGet();
                if (LOG.isInfoEnabled()) {
                    LOG.info("answercallback query response with http status {}", rsp.getStatus());
                }
            } catch (Exception e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error("exception sending answer to callback query {}", e.getMessage());
                }
            }
        }
    }

    protected Optional<String> parseCommandAtUpdate(@NonNull TelegramBot telegramBot, @NonNull Update update) {
        String commandText = updateParser.parseTextWithoutBotName(telegramBot, update).orElse(null);
        return parseCommand(commandText);
    }

    protected Optional<String> parseCommand(@NonNull String commandText) {
        if (LOG.isInfoEnabled()) {
            LOG.info("text parsed: {}", commandText);
        }
        if (commandText.contains(SPACE)) {
            String[] arr = commandText.split(SPACE);
            if (arr.length >= 1) {
                commandText = arr[0];
            }
        }
        if (commandText.startsWith(CommandHandler.COMMAND_PREFIX)) {
            return Optional.of(commandText.substring(1));
        }
        return Optional.empty();
    }

    protected Optional<Send> defaultHandleUpdateNotProcessedByCommands(@NonNull TelegramBot telegramBot,
                                                                       @NonNull Update update) {
        Optional<Chat> chatOptional = updateParser.parseChat(update);
        return chatOptional.map(chat -> defaultMessage(chat.getId()));
    }

    protected Optional<Send> handleUpdateNotProcessedByCommands(@NonNull TelegramBot telegramBot,
                                                                Update update) {
        return defaultHandleUpdateNotProcessedByCommands(telegramBot, update);
    }

    protected SendMessage defaultMessage(Integer chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(DEFAULT_MESSAGE);
        return sendMessage;
    }

}
