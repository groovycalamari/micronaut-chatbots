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
package io.micronaut.bots.core;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.context.ApplicationContext;
import io.micronaut.inject.qualifiers.Qualifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public class DefaultUpdateDispatcher implements ChatBotMessageDispatcher {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultUpdateDispatcher.class);
    public static final String SPACE = " ";
    public static final String DEFAULT_COMMAND = "default";

    protected final ApplicationContext applicationContext;
    protected final ChatBotMessageParser messageParser;
    protected final Collection<MatcherCommandHandler> matcherCommandHandlers;
    protected final Map<String, CommandHandler> commandToHandler = new HashMap<>();

    public DefaultUpdateDispatcher(ApplicationContext applicationContext,
                                   ChatBotMessageParser messageParser,
                                   Collection<MatcherCommandHandler> matcherCommandHandlers) {
        this.applicationContext = applicationContext;
        this.messageParser = messageParser;
        this.matcherCommandHandlers = matcherCommandHandlers;
    }

    @Override
    public Optional<ChatBotMessageSend> dispatch(@NonNull ChatBot chatBot, @NonNull ChatBotMessageReceive messageReceive) {
        if (shouldHandleMessage(chatBot, messageReceive)) {
            Optional<CommandHandler> handler = parseCommandHandler(chatBot, messageReceive);
            if (handler.isEmpty()) {
                handler = parseHandlerFromCommand(DEFAULT_COMMAND);
            }
            if (handler.isPresent()) {
                return handler.get().handle(chatBot, messageReceive);
            }
        }

        return Optional.empty();
    }

    protected Optional<CommandHandler> parseHandlerFromCommand(@NonNull String command) {
        if (commandToHandler.containsKey(command)) {
            return Optional.of(commandToHandler.get(command));
        }
        if (applicationContext.containsBean(CommandHandler.class, Qualifiers.byName(command))) {
            CommandHandler handler = applicationContext.getBean(CommandHandler.class, Qualifiers.byName(command));
            commandToHandler.put(command, handler);
            return Optional.of(handler);
        }
        return Optional.empty();
    }

    protected Optional<CommandHandler> parseCommandHandler(@NonNull ChatBot chatBot,
                                                           @NonNull ChatBotMessageReceive messageReceive) {
        Optional<String> commandOptional = parseCommandAtUpdate(chatBot, messageReceive);
        if (commandOptional.isPresent()) {
            String command = commandOptional.get();
            Optional<CommandHandler> commandHandlerOptional = parseHandlerFromCommand(command);
            if (commandHandlerOptional.isPresent()) {
                return commandHandlerOptional;
            }
        }
        Optional<String> textOptional = messageParser.parseText(messageReceive);
        if (textOptional.isPresent()) {
            String text = textOptional.get();
            if (LOG.isInfoEnabled()) {
                LOG.info("text parsed: {}", text);
            }
            for (MatcherCommandHandler handler : matcherCommandHandlers) {
                if (handler.matches(text)) {
                    return Optional.of(handler);
                }
            }
        }
        return Optional.empty();
    }

    protected boolean shouldHandleMessage(@NonNull ChatBot bot, @NonNull ChatBotMessageReceive update) {
        return true;
    }

    protected Optional<String> parseCommandAtUpdate(@NonNull ChatBot chatBot, @NonNull ChatBotMessageReceive update) {
        String commandText = messageParser.parseTextWithoutBotName(chatBot, update).orElse(null);
        if (commandText == null) {
            return Optional.empty();
        }
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
}
