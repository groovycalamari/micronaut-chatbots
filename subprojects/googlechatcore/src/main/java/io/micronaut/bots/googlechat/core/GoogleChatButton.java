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
 * A button. Can be a text button or an image button.
 */
@Introspected
public class GoogleChatButton {
    /**
     * A button with text and onclick action.
     */
    private GoogleChatTextButton textButton;
    /**
     * A button with image and onclick action.
     */
    private GoogleChatImageButton imageButton;

    public GoogleChatButton() {
    }

    public GoogleChatTextButton getTextButton() {
        return textButton;
    }

    public void setTextButton(GoogleChatTextButton textButton) {
        this.textButton = textButton;
    }

    public GoogleChatImageButton getImageButton() {
        return imageButton;
    }

    public void setImageButton(GoogleChatImageButton imageButton) {
        this.imageButton = imageButton;
    }

    @Override
    public String toString() {
        return "GoogleChatButton{" +
                "textButton=" + (textButton != null ? textButton.toString() : null) +
                ", imageButton=" + (imageButton != null ? imageButton.toString() : null) +
                '}';
    }
}
