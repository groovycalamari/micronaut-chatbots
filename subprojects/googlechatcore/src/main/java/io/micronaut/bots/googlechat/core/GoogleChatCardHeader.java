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

@Introspected
public class GoogleChatCardHeader {


    /**
     * The title must be specified. The header has a fixed height: if both a title and subtitle is specified, each will take up 1 line. If only the title is specified, it will take up both lines.
     */
    private String title;

    /**
     * The subtitle of the card header.
     */
    private String subtitle;

    /**
     * The image's type (e.g. square border or circular border).
     */
    private GoogleChatImageStyle imageStyle;

    /**
     * The URL of the image in the card header.
     */
    private String imageUrl;

    public GoogleChatCardHeader() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public GoogleChatImageStyle getImageStyle() {
        return imageStyle;
    }

    public void setImageStyle(GoogleChatImageStyle imageStyle) {
        this.imageStyle = imageStyle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "GoogleChatCardHeader{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", imageStyle=" + imageStyle +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
