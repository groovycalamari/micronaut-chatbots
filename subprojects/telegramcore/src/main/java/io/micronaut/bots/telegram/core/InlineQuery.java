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

import io.micronaut.core.annotation.Introspected;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This object represents an incoming inline query. When the user sends an empty query, your bot could return some default or trending results.
 * @see ≤a href="https://core.telegram.org/bots/api#inlinequery">InlineQuery</a>
 */
@Introspected
public class InlineQuery {

    /**
     * Unique identifier for this query.
     */
    @Nonnull
    @NotBlank
    private String id;

    /**
     * Sender.
     */
    @Nonnull
    @NotNull
    @Valid
    private User from;

    /**
     * Sender location, only for bots that request user location.
     */
    @Nullable
    @Valid
    private Location location;

    /**
     * Text of the query (up to 512 characters)
     */
    @Nonnull
    @NotBlank
    private String query;

    /**
     * Offset of the results to be returned, can be controlled by the bot
     */
    @Nonnull
    @NotBlank
    private String offset;

    public InlineQuery() {
    }

    @Nonnull
    public String getId() {
        return id;
    }

    public void setId(@Nonnull String id) {
        this.id = id;
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

    @Nonnull
    public String getQuery() {
        return query;
    }

    public void setQuery(@Nonnull String query) {
        this.query = query;
    }

    @Nonnull
    public String getOffset() {
        return offset;
    }

    public void setOffset(@Nonnull String offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "InlineQuery{" +
                "id='" + id + '\'' +
                ", from=" + (from !=null ? from.toString() : "") +
                ", location=" + (location != null ? location.toString() : "") +
                ", query='" + query + '\'' +
                ", offset='" + offset + '\'' +
                '}';
    }
}
