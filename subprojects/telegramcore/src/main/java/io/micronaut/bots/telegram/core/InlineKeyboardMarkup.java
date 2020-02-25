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
package io.micronaut.bots.telegram.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This object represents an inline keyboard that appears right next to the message it belongs to.
 * @see <a href="https://core.telegram.org/bots/api#inlinekeyboardmarkup">InlineKeyboardMarkup</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineKeyboardMarkup {
    /**
     * Array of button rows, each represented by an Array of InlineKeyboardButton objects.
     */
    @Nonnull
    @NotNull
    @JsonProperty("inline_keyboard")
    private List<List<InlineKeyboardButton>> inlineKeyboard;

    public InlineKeyboardMarkup() {
    }

    @Nonnull
    public List<List<InlineKeyboardButton>>  getInlineKeyboard() {
        return inlineKeyboard;
    }

    public void setInlineKeyboard(@Nonnull List<List<InlineKeyboardButton>>  inlineKeyboard) {
        this.inlineKeyboard = inlineKeyboard;
    }

    @Override
    public String toString() {
        return "InlineKeyboardMarkup{" +
                "inlineKeyboard=" + (inlineKeyboard != null ? String.join(",", inlineKeyboard.stream().map(keyboard -> String.join(",", keyboard.stream().map(InlineKeyboardButton::toString).collect(Collectors.toList()))).collect(Collectors.toList())) : "") +
                '}';
    }
}
