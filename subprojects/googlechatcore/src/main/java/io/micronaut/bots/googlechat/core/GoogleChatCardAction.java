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
 * A card action is the action associated with the card. For an invoice card, a typical action would be: delete invoice, email invoice or open the invoice in browser.
 */
@Introspected
public class GoogleChatCardAction {

    /**
     * The label used to be displayed in the action menu item.
     */
    @Nullable
     private String actionLabel;

    /**
     * The onclick action for this action item.
     */
    @Nullable
    private GoogleChatOnClick onClick;

    public GoogleChatCardAction() {
    }

    @Nullable
    public String getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(@Nullable String actionLabel) {
        this.actionLabel = actionLabel;
    }

    @Nullable
    public GoogleChatOnClick getOnClick() {
        return onClick;
    }

    public void setOnClick(@Nullable GoogleChatOnClick onClick) {
        this.onClick = onClick;
    }

    @Override
    public String toString() {
        return "GoogleChatCardAction{" +
                "actionLabel='" + actionLabel + '\'' +
                ", onClick=" + (onClick != null ? onClick.toString() : null) +
                '}';
    }
}

