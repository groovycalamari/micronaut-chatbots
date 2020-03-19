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

@Introspected
public class GoogleChatOnClick {

    //TODO Union field data .
    //
    //data can be only one of the following:

    /**
     * A form action will be trigger by this onclick if specified.
     */
    @Nullable
    private GoogleChatFormAction action;

    /**
     * This onclick triggers an open link action if specified.
     */
    @Nullable
    private GoogleChatOpenLink openLink;

    public GoogleChatOnClick() {
    }

    public GoogleChatOnClick(@Nullable GoogleChatFormAction action) {
        this.action = action;
    }

    public GoogleChatOnClick(@Nullable GoogleChatOpenLink openLink) {
        this.openLink = openLink;
    }


    @Nullable
    public GoogleChatFormAction getAction() {
        return action;
    }

    public void setAction(@Nullable GoogleChatFormAction action) {
        this.action = action;
    }

    @Nullable
    public GoogleChatOpenLink getOpenLink() {
        return openLink;
    }

    public void setOpenLink(@Nullable GoogleChatOpenLink openLink) {
        this.openLink = openLink;
    }

    @Override
    public String toString() {
        return "GoogleChatOnClick{" +
                "action=" + (action != null ? action.toString() : null) +
                ", openLink=" + (openLink != null ? openLink.toString() : null) +
                '}';
    }
}
