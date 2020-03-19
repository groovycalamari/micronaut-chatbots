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

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.Introspected;

/**
 * An image button with an onclick action.
 */
@Introspected
public class GoogleChatImageButton {

    /**
     * The onclick action.
     */
    private GoogleChatOnClick onClick;

    /**
     * The name of this imageButton which will be used for accessibility. Default value will be provided if developers don't specify.
     */
    private String name;

    // TODO Union field icons can be only one of the following:
    /**
     * The icon specified by an enum that indices to an icon provided by Chat API.
     */
    @Nullable
    private GoogleChatIcon icon;

    /**
     * The icon specified by a URL.
     */
    @Nullable
    private String iconUrl;
    // End of list of possible types for union field icons.


    public GoogleChatImageButton() {
    }

    public GoogleChatOnClick getOnClick() {
        return onClick;
    }

    public void setOnClick(GoogleChatOnClick onClick) {
        this.onClick = onClick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public GoogleChatIcon getIcon() {
        return icon;
    }

    public void setIcon(@Nullable GoogleChatIcon icon) {
        this.icon = icon;
    }

    @Nullable
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(@Nullable String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return "GoogleChatImageButton{" +
                "onClick=" + (onClick != null ? onClick.toString() : null) +
                ", name='" + name + '\'' +
                ", icon=" + icon +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }
}
