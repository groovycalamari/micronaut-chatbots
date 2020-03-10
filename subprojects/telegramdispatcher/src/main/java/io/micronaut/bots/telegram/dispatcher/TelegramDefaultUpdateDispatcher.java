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
import io.micronaut.bots.core.ChatBot;
import io.micronaut.bots.core.ChatBotMessageReceive;
import io.micronaut.bots.core.ChatBotMessageParser;
import io.micronaut.bots.core.ChatBotMessageSend;
import io.micronaut.bots.core.CommandHandler;
import io.micronaut.bots.core.DefaultUpdateDispatcher;
import io.micronaut.bots.core.MatcherCommandHandler;
import io.micronaut.bots.telegram.core.AnswerCallbackQuery;
import io.micronaut.bots.telegram.core.ChatType;
import io.micronaut.bots.telegram.core.Update;
import io.micronaut.bots.telegram.httpclient.TelegramBot;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.Optional;

@Replaces(DefaultUpdateDispatcher.class)
@Singleton
public class TelegramDefaultUpdateDispatcher extends DefaultUpdateDispatcher {
    private static final Logger LOG = LoggerFactory.getLogger(TelegramDefaultUpdateDispatcher.class);

    public TelegramDefaultUpdateDispatcher(ApplicationContext applicationContext,
                                           ChatBotMessageParser updateParser,
                                           Collection<MatcherCommandHandler> matcherCommandHandlers) {
        super(applicationContext, updateParser, matcherCommandHandlers);
    }

    @Override
    public Optional<ChatBotMessageSend> dispatch(@NonNull ChatBot chatBot, @NonNull ChatBotMessageReceive chatBotMessageReceive) {
        if (!(chatBotMessageReceive instanceof Update) || !(chatBot instanceof TelegramBot)) {
            return Optional.empty();
        }
        TelegramBot telegramBot = (TelegramBot) chatBot;
        Update update = (Update) chatBotMessageReceive;
        handleCallbackQuery(telegramBot, update);
        if (super.shouldHandleMessage(chatBot, chatBotMessageReceive)) {
            Optional<CommandHandler> handler = parseCommandHandler(chatBot, chatBotMessageReceive);
            if (!handler.isPresent()) {
                handler = parseHandlerFromCommand(DEFAULT_COMMAND);
            }
            if (handler.isPresent()) {
                return handler.get().handle(chatBot, chatBotMessageReceive);
            }
        }

        return Optional.empty();
    }

    @Override
    protected boolean shouldHandleMessage(@NonNull ChatBot bot, @NonNull ChatBotMessageReceive update) {
        String text = messageParser.parseText(update).orElse(null);
        if (LOG.isInfoEnabled()) {
            LOG.info("text parsed: {}", text);
        }

        String type = null;
        if (messageParser instanceof TelegramChatBotMessageParser) {
            type = ((TelegramChatBotMessageParser) messageParser).parseChatType(update).orElse(null);
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("type parsed: {}", type);
        }
        boolean isPrivateMessage = (type != null && type.equals(ChatType.PRIVATE.toString()));
        boolean isMessageTargetToTheBot = text != null && text.contains(bot.getAtUsername());
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
}
