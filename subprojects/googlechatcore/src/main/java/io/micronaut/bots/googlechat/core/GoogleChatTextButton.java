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
public class GoogleChatTextButton {

    /**
     * The text of the button.
     */
    private String text;

    /**
     * The onclick action of the button.
     */
    private GoogleChatOnClick onClick;

    public GoogleChatTextButton() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GoogleChatOnClick getOnClick() {
        return onClick;
    }

    public void setOnClick(GoogleChatOnClick onClick) {
        this.onClick = onClick;
    }

    @Override
    public String toString() {
        return "GoogleChatTextButton{" +
                "text='" + text + '\'' +
                ", onClick=" + (onClick != null ? onClick.toString() : null) +
                '}';
    }
}
