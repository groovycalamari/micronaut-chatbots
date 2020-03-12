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
import io.micronaut.core.annotation.Introspected;

import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * This object represents type of a poll, which is allowed to be created and sent when the corresponding button is pressed.
 * @see <a href="https://core.telegram.org/bots/api#keyboardbuttonpolltype">KeybaordButtonPollType</a>.
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeyboardButtonPollType {

    /**
     * If quiz is passed, the user will be allowed to create only polls in the quiz mode. If regular is passed, only regular polls will be allowed. Otherwise, the user will be allowed to create a poll of any type.
     */
    @Nullable
    private String type;

    public KeyboardButtonPollType() {
    }

    @Nullable
    public String getType() {
        return type;
    }

    public void setType(@Nullable String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "KeyboardButtonPollType{" +
                "type='" + type + '\'' +
                '}';
    }
}
