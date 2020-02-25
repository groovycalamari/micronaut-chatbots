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
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.bots.telegram.core.Send;
import io.micronaut.bots.telegram.core.Update;
import java.util.Optional;

public interface CommandHandler {
    String COMMAND_PREFFIX = "/";

    <T extends Send> Optional<T> handle(@NonNull Update update);

    @Nullable
    static String parseText(@NonNull Update update) {
        if (update.getEditedMessage()!=null) {
            return update.getEditedMessage().getText();
        } else if (update.getMessage() != null) {
            return update.getMessage().getText();
        }
        return null;
    }

    @Nullable
    static Integer parseChatId(@NonNull Update update) {
        if (update.getEditedMessage()!=null) {
            return update.getEditedMessage().getChat().getId();
        } else if (update.getMessage() != null) {
            return update.getMessage().getChat().getId();
        }
        return null;
    }
}
