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
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This object represent a user's profile pictures.
 * @see <a href="https://core.telegram.org/bots/api#userprofilephotos">UserProfilePhotos</a>
 */
@Introspected
public class UserProfilePhotos {

    /**
     * Total number of profile pictures the target user has.
     */
    @JsonProperty("total_count")
    @Nonnull
    @NotNull
    private Integer totalCount;

    /**
     * Array of PhotoSize	Requested profile pictures (in up to 4 sizes each)
     */
    @Nonnull
    @NotNull
    private List<PhotoSize> photos;

    public UserProfilePhotos() {
    }

    @Nonnull
    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(@Nonnull Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Nonnull
    public List<PhotoSize> getPhotos() {
        return photos;
    }

    public void setPhotos(@Nonnull List<PhotoSize> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "UserProfilePhotos{" +
                "totalCount=" + totalCount +
                ", photos=" + (photos != null ? String.join(",", photos.stream().map(PhotoSize::toString).collect(Collectors.toList())) : "") +

                '}';
    }
}
