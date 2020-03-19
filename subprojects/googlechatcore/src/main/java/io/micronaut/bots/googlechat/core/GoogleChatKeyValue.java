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
 * A UI element contains a key (label) and a value (content). And this element may also contain some actions such as onclick button.
 */
@Introspected
public class GoogleChatKeyValue {
    private String topLabel;
    private String content;
    private Boolean contentMultiline;
    private String bottomLabel;
    private GoogleChatOnClick onClick;

    //TODO Union field icons can be only one of the following:
    private GoogleChatIcon icon;

    private String iconUrl;

    //TODO  End of list of possible types for union field icons.
    private GoogleChatButton button;

    public GoogleChatKeyValue() {
    }

    public String getTopLabel() {
        return topLabel;
    }

    public void setTopLabel(String topLabel) {
        this.topLabel = topLabel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getContentMultiline() {
        return contentMultiline;
    }

    public void setContentMultiline(Boolean contentMultiline) {
        this.contentMultiline = contentMultiline;
    }

    public String getBottomLabel() {
        return bottomLabel;
    }

    public void setBottomLabel(String bottomLabel) {
        this.bottomLabel = bottomLabel;
    }

    public GoogleChatOnClick getOnClick() {
        return onClick;
    }

    public void setOnClick(GoogleChatOnClick onClick) {
        this.onClick = onClick;
    }

    public GoogleChatIcon getIcon() {
        return icon;
    }

    public void setIcon(GoogleChatIcon icon) {
        this.icon = icon;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public GoogleChatButton getButton() {
        return button;
    }

    public void setButton(GoogleChatButton button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "GoogleChatKeyValue{" +
                "topLabel='" + topLabel + '\'' +
                ", content='" + content + '\'' +
                ", contentMultiline=" + contentMultiline +
                ", bottomLabel='" + bottomLabel + '\'' +
                ", onClick=" + (onClick != null ? onClick.toString() : null) +
                ", icon=" + icon +
                ", iconUrl='" + iconUrl + '\'' +
                ", button=" + (button != null ? button.toString() : null) +
                '}';
    }
}
