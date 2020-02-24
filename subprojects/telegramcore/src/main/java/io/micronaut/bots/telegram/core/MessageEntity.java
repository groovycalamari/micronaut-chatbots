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
 * This object represents one special entity in a text message. For example, hashtags, usernames, URLs, etc.
 * @see <a href="https://core.telegram.org/bots/api#messageentity">MessageEntity</a>
 */
@Introspected
public class MessageEntity {

    /**
     * Type of the entity. Can be mention (@username), hashtag, cashtag, bot_command, url, email, phone_number, bold (bold text), italic (italic text), code (monowidth string), pre (monowidth block), text_link (for clickable text URLs), text_mention (for users without usernames)
     */
    @Nonnull
    @NotBlank
    private String type;

    /**
     * Offset in UTF-16 code units to the start of the entity
     */
    @Nonnull
    @NotNull
    private Integer offset;

    /**
     * Length of the entity in UTF-16 code units
     */
    @Nonnull
    @NotNull
    private Integer length;

    /**
     * For “text_link” only, url that will be opened after user taps on the text
     */
    @Nullable
    private String url;

    /**
     * For “text_mention” only, the mentioned user
     */
    @Nullable
    @Valid
    private User user;

    public MessageEntity() {
    }

    @Nonnull
    public String getType() {
        return type;
    }

    public void setType(@Nonnull String type) {
        this.type = type;
    }

    @Nonnull
    public Integer getOffset() {
        return offset;
    }

    public void setOffset(@Nonnull Integer offset) {
        this.offset = offset;
    }

    @Nonnull
    public Integer getLength() {
        return length;
    }

    public void setLength(@Nonnull Integer length) {
        this.length = length;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Nullable
    public User getUser() {
        return user;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "type='" + type + '\'' +
                ", offset=" + offset +
                ", length=" + length +
                ", url='" + url + '\'' +
                ", user=" + (user != null ? user.toString() : "") +
                '}';
    }
}
