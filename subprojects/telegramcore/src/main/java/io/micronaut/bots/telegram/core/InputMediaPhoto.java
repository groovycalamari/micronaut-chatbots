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

/**
 * Represents a photo to be sent.
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputMediaPhoto extends InputMedia {

    public static final String TYPE_PHOTO = "photo";


    public InputMediaPhoto() {
        super(TYPE_PHOTO);
    }

    @Override
    public String toString() {
        return "InputMediaPhoto{"+
                super.toString() +
                "}";
    }
}
