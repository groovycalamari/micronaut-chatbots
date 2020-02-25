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
import io.micronaut.bots.telegram.core.Send;
import io.micronaut.bots.telegram.core.SendMessage;
import io.micronaut.bots.telegram.core.Update;

import java.util.Optional;

public abstract class TextCommandHandler implements CommandHandler {
    @NonNull
    protected abstract Optional<String> replyUpdate(@NonNull Update update);

    @Override
    public <T extends Send> Optional<T> handle(@NonNull Update update) {
        Optional<String> textOpt = replyUpdate(update);
        if (!textOpt.isPresent()) {
            return Optional.empty();
        }
        Integer chatId = CommandHandler.parseChatId(update);
        if (chatId == null) {
            return Optional.empty();
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(textOpt.get());
        return (Optional) Optional.of(sendMessage);
    }
}