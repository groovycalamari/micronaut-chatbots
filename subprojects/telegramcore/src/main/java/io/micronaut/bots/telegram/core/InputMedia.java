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
import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;

/**
 * This object represents the content of a media message to be sent. It should be one of:
 * - {@link InputMediaAnimation}
 * - {@link InputMediaDocument}
 * - {@link InputMediaPhoto}
 * - {@link InputMediaVideo}
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class InputMedia {
    /**
     * Type of the result, must be photo.
     */
    @Nonnull
    @NotBlank
    private String type;


    /**
     * File to send. Pass a file_id to send a file that exists on the Telegram servers (recommended), pass an HTTP URL for Telegram to get a file from the Internet, or pass “attach://<file_attach_name>” to upload a new one using multipart/form-data under <file_attach_name> name.
     * @see <a href="https://core.telegram.org/bots/api#sending-files">More info on Sending Files</a>
     */
    @Nonnull
    @NotBlank
    private String media;


    /**
     * Caption of the audio to be sent, 0-1024 characters
     */
    @Nullable
    private String caption;

    /**
     * Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in the media caption.
     */
    @Nullable
    @JsonProperty("parse_mode")
    private String parseMode;

    public InputMedia(@Nonnull String type) {
        this.type = type;
    }

    @Nonnull
    public String getType() {
        return type;
    }

    public void setType(@Nonnull String type) {
        this.type = type;
    }

    @Nonnull
    public String getMedia() {
        return media;
    }

    public void setMedia(@Nonnull String media) {
        this.media = media;
    }

    @Nullable
    public String getCaption() {
        return caption;
    }

    public void setCaption(@Nullable String caption) {
        this.caption = caption;
    }

    @Nullable
    public String getParseMode() {
        return parseMode;
    }

    public void setParseMode(@Nullable String parseMode) {
        this.parseMode = parseMode;
    }

    @Override
    public String toString() {
        return "" +
                ", type='" + type + '\'' +
                ", media='" + media + '\'' +
                ", caption='" + caption + '\'' +
                ", parseMode='" + parseMode + '\'';
    }
}
