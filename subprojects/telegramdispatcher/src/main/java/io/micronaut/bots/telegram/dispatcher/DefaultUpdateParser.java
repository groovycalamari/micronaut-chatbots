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
import io.micronaut.bots.telegram.core.Chat;
import io.micronaut.bots.telegram.core.Update;
import io.micronaut.bots.telegram.httpclient.TelegramBot;
import io.micronaut.core.util.StringUtils;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class DefaultUpdateParser implements UpdateParser {

    public Optional<Chat> parseChat(@NonNull Update update) {
        if (update.getCallbackQuery() != null) {
            if (update.getCallbackQuery().getMessage() != null) {
                return Optional.of(update.getCallbackQuery().getMessage().getChat());
            }
        }
        if (update.getEditedMessage()!=null) {
            return Optional.of(update.getEditedMessage().getChat());
        } else if (update.getMessage() != null) {
            return Optional.of(update.getMessage().getChat());
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> parseText(@NonNull Update update) {
        if (update.getCallbackQuery() != null) {
            return Optional.ofNullable(update.getCallbackQuery().getData());
        }
        if (update.getEditedMessage()!=null) {
            return Optional.ofNullable(update.getEditedMessage().getText());
        }
        if (update.getMessage() != null) {
            return Optional.ofNullable(update.getMessage().getText());
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> parseTextWithoutBotName(@NonNull TelegramBot telegramBot, @NonNull Update update) {
        Optional<String> textOptional = parseText(update);
        if (textOptional.isPresent()) {
            String text = textOptional.get();
            return Optional.of(text.replaceAll(telegramBot.getAtUsername(), "").trim());
        }
        return Optional.empty();
    }


    @Override
    public Optional<Integer> parseUserId(Update update) {
        if (update.getCallbackQuery() != null) {
            return Optional.of(update.getCallbackQuery().getFrom().getId());
        }
        if (update.getEditedMessage()!=null) {
            if (update.getEditedMessage().getFrom() != null) {
                return Optional.of(update.getEditedMessage().getFrom().getId());
            }
        }
        if (update.getMessage() != null) {
            if (update.getMessage().getFrom() != null) {
                return Optional.of(update.getMessage().getFrom().getId());
            }
        }
        return Optional.empty();
    }
}
