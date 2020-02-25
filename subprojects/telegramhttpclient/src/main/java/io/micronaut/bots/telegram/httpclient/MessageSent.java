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
package io.micronaut.bots.telegram.httpclient;

import io.micronaut.bots.telegram.core.Message;
import io.micronaut.core.annotation.Introspected;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

@Introspected
public class MessageSent {
    @Nonnull
    @NotNull
    private Boolean ok;

    @Nullable
    private Message result;

    public MessageSent() {
    }

    @Nonnull
    public Boolean getOk() {
        return ok;
    }

    public void setOk(@Nonnull Boolean ok) {
        this.ok = ok;
    }

    @Nullable
    public Message getResult() {
        return result;
    }

    public void setResult(@Nullable Message result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MessageSent{" +
                "ok=" + ok +
                ", result=" + (result != null ? result.toString() : "") +
                '}';
    }
}
