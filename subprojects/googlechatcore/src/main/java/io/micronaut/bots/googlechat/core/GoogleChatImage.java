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

import io.micronaut.core.annotation.Introspected;

/**
 * An image that is specified by a URL and can have an onclick action.
 */
@Introspected
public class GoogleChatImage {
    /**
     * The URL of the image.
     */
    private String imageUrl;
    /**
     * The onclick action.
     */
    private GoogleChatOnClick onClick;

    /**
     * The aspect ratio of this image (width/height). This field allows clients to reserve the right height for the image while waiting for it to load. It will not override the native aspect ratio of the image. If unset, the server will fill it by prefetching the image.
     */
    private Float aspectRatio;

    public GoogleChatImage() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public GoogleChatOnClick getOnClick() {
        return onClick;
    }

    public void setOnClick(GoogleChatOnClick onClick) {
        this.onClick = onClick;
    }

    public Float getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }
}
