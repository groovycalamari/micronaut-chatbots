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
package io.micronaut.bots.googlechat.core;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

/**
 * A paragraph of text. Formatted text supported.
 */
@Introspected
public class GoogleChatTextParagraph {
    @NotBlank
    @NonNull
    private String text;

    public GoogleChatTextParagraph() {
    }

    public GoogleChatTextParagraph(@NonNull @NotBlank String text) {
        this.text = text;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "GoogleChatTextParagraph{" +
                "text='" + text + '\'' +
                '}';
    }
}
