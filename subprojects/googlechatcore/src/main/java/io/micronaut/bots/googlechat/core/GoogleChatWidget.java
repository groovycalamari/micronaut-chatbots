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

import java.util.List;
import java.util.stream.Collectors;

@Introspected
public class GoogleChatWidget {

    private List<GoogleChatButton> buttons;

    //TODO
    //Union field data . A WidgetMarkup can only have one of the following items. You can use multiple WidgetMarkup fields to display more items. data can be only one of the following:

    /**
     * Display a text paragraph in this widget.
     */
    private GoogleChatTextParagraph textParagraph;

    /**
     * Display an image in this widget.
     */
    private GoogleChatImage image;

    /**
     * Display a key value item in this widget.
     */
    private GoogleChatKeyValue keyValue;

    public GoogleChatWidget() {
    }

    public GoogleChatWidget(List<GoogleChatButton> buttons) {
        this.buttons = buttons;
    }

    public GoogleChatWidget(List<GoogleChatButton> buttons, GoogleChatTextParagraph textParagraph) {
        this.buttons = buttons;
        this.textParagraph = textParagraph;
    }

    public List<GoogleChatButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<GoogleChatButton> buttons) {
        this.buttons = buttons;
    }

    public GoogleChatTextParagraph getTextParagraph() {
        return textParagraph;
    }

    public void setTextParagraph(GoogleChatTextParagraph textParagraph) {
        this.textParagraph = textParagraph;
    }

    public GoogleChatImage getImage() {
        return image;
    }

    public void setImage(GoogleChatImage image) {
        this.image = image;
    }

    public GoogleChatKeyValue getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(GoogleChatKeyValue keyValue) {
        this.keyValue = keyValue;
    }

    @Override
    public String toString() {
        return "GoogleChatWidget{" +
                "buttons=" + (buttons != null ? buttons.stream().map(GoogleChatButton::toString).collect(Collectors.joining(",")) : null) +
                ", textParagraph=" + (textParagraph != null ? textParagraph.toString() : null) +
                ", image=" + (image != null ? image.toString() : null) +
                ", keyValue=" + (keyValue != null ? keyValue.toString() : null) +
                '}';
    }
}
