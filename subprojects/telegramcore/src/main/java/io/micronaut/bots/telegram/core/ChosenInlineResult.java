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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Represents a result of an inline query that was chosen by the user and sent to their chat partner.
 * @see <a href="https://core.telegram.org/bots/api#choseninlineresult">ChoseInlineResult</a>
 */
@Introspected
public class ChosenInlineResult {
    /**
     * The unique identifier for the result that was chosen
     */
    @Nonnull
    @NotBlank
    @JsonProperty("resultid")
    private String resultId;

    /**
     * The user that chose the result
     */
    @Nonnull
    @NotNull
    @Valid
    private User from;

    /**
     * Sender location, only for bots that require user location.
     */
    @Nullable
    private Location location;

    /**
     * Identifier of the sent inline message. Available only if there is an inline keyboard attached to the message. Will be also received in callback queries and can be used to edit the message.
     */
    @Nullable
    @JsonProperty("inline_message_id")
    private String inlineMessageId;

    /**
     * The query that was used to obtain the result
     */
    @Nonnull
    @NotBlank
    private String query;

    public ChosenInlineResult() {
    }

    @Nonnull
    public String getResultId() {
        return resultId;
    }

    public void setResultId(@Nonnull String resultId) {
        this.resultId = resultId;
    }

    @Nonnull
    public User getFrom() {
        return from;
    }

    public void setFrom(@Nonnull User from) {
        this.from = from;
    }

    @Nullable
    public Location getLocation() {
        return location;
    }

    public void setLocation(@Nullable Location location) {
        this.location = location;
    }

    @Nullable
    public String getInlineMessageId() {
        return inlineMessageId;
    }

    public void setInlineMessageId(@Nullable String inlineMessageId) {
        this.inlineMessageId = inlineMessageId;
    }

    @Nonnull
    public String getQuery() {
        return query;
    }

    public void setQuery(@Nonnull String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "ChosenInlineResult{" +
                "resultId='" + resultId + '\'' +
                ", from=" + (from != null ? from.toString() : "") +
                ", location=" + (location != null ? location.toString() : "") +
                ", inlineMessageId='" + inlineMessageId + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
