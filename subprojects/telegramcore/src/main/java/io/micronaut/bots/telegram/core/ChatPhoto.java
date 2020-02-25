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
import javax.validation.constraints.NotBlank;

/**
 * This object represents a chat photo.
 * @see <a href="https://core.telegram.org/bots/api#chatphoto>ChatPhoto</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatPhoto {
    /**
     * File identifier of small (160x160) chat photo. This file_id can be used only for photo download and only for as long as the photo is not changed.
     */
    @Nonnull
    @NotBlank
    @JsonProperty("small_file_id")
    private String smallFileId;

    /**
     * Unique file identifier of small (160x160) chat photo, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
     */
    @Nonnull
    @NotBlank
    @JsonProperty("small_file_unique_id")
    private String smallFileUniqueId;

    /**
     * File identifier of big (640x640) chat photo. This file_id can be used only for photo download and only for as long as the photo is not changed.
     */
    @Nonnull
    @NotBlank
    @JsonProperty("big_file_id")
    private String bigFileId;

    /**
     * Unique file identifier of big (640x640) chat photo, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
     */
    @Nonnull
    @NotBlank
    @JsonProperty("big_file_unique_id")
    private String bigFileUniqueId;

    public ChatPhoto() {
    }

    @Nonnull
    public String getSmallFileId() {
        return smallFileId;
    }

    public void setSmallFileId(@Nonnull String smallFileId) {
        this.smallFileId = smallFileId;
    }

    @Nonnull
    public String getBigFileId() {
        return bigFileId;
    }

    public void setBigFileId(@Nonnull String bigFileId) {
        this.bigFileId = bigFileId;
    }

    @Nonnull
    public String getSmallFileUniqueId() {
        return smallFileUniqueId;
    }

    public void setSmallFileUniqueId(@Nonnull String smallFileUniqueId) {
        this.smallFileUniqueId = smallFileUniqueId;
    }

    @Nonnull
    public String getBigFileUniqueId() {
        return bigFileUniqueId;
    }

    public void setBigFileUniqueId(@Nonnull String bigFileUniqueId) {
        this.bigFileUniqueId = bigFileUniqueId;
    }

    @Override
    public String toString() {
        return "ChatPhoto{" +
                "smallFileId='" + smallFileId + '\'' +
                ", smallFileUniqueId='" + smallFileUniqueId + '\'' +
                ", bigFileId='" + bigFileId + '\'' +
                ", bigFileUniqueId='" + bigFileUniqueId + '\'' +
                '}';
    }
}