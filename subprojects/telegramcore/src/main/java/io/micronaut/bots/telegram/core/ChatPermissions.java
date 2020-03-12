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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Describes actions that a non-administrator user is allowed to take in a chat.
 * @see <a href="https://core.telegram.org/bots/api#chatpermissions">ChatPermissions</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatPermissions {

    /**
     * True, if the user is allowed to send text messages, contacts, locations and venues.
     */
    @JsonProperty("can_send_messages")
    @Nullable
    private Boolean canSendMessages;

    /**
     * True, if the user is allowed to send audios, documents, photos, videos, video notes and voice notes, implies can_send_messages.
     */
    @Nullable
    @JsonProperty("can_send_media_messages")
    private Boolean canSendMediaMessages;

    /**
     * True, if the user is allowed to send polls, implies can_send_messages.
     */
    @JsonProperty("can_send_polls")
    @Nullable
    private Boolean canSendPolls;

    /**
     * True, if the user is allowed to send animations, games, stickers and use inline bots, implies can_send_media_messages.
     */
    @JsonProperty("can_send_other_messages")
    @Nullable
    private Boolean canSendOtherMessages;

    /**
     * True, if the user is allowed to add web page previews to their messages, implies can_send_media_messages.
     */
    @Nullable
    @JsonProperty("can_add_web_page_previews")
    private Boolean canAddWebPagePreviews;

    /**
     * True, if the user is allowed to change the chat title, photo and other settings. Ignored in public supergroups
     */
    @Nullable
    @JsonProperty("can_change_info")
    private Boolean canChangeInfo;

    /**
     * True, if the user is allowed to invite new users to the chat.
     */
    @JsonProperty("can_invite_users")
    @Nullable
    private Boolean canInviteUsers;

    /**
     * True, if the user is allowed to pin messages. Ignored in public supergroups
     */
    @JsonProperty("can_pin_messages")
    @Nullable
    private Boolean canPinMessages;

    public ChatPermissions() {
    }

    @Nullable
    public Boolean getCanSendMessages() {
        return canSendMessages;
    }

    public void setCanSendMessages(@Nullable Boolean canSendMessages) {
        this.canSendMessages = canSendMessages;
    }

    @Nullable
    public Boolean getCanSendMediaMessages() {
        return canSendMediaMessages;
    }

    public void setCanSendMediaMessages(@Nullable Boolean canSendMediaMessages) {
        this.canSendMediaMessages = canSendMediaMessages;
    }

    @Nullable
    public Boolean getCanSendPolls() {
        return canSendPolls;
    }

    public void setCanSendPolls(@Nullable Boolean canSendPolls) {
        this.canSendPolls = canSendPolls;
    }

    @Nullable
    public Boolean getCanSendOtherMessages() {
        return canSendOtherMessages;
    }

    public void setCanSendOtherMessages(@Nullable Boolean canSendOtherMessages) {
        this.canSendOtherMessages = canSendOtherMessages;
    }

    @Nullable
    public Boolean getCanAddWebPagePreviews() {
        return canAddWebPagePreviews;
    }

    public void setCanAddWebPagePreviews(@Nullable Boolean canAddWebPagePreviews) {
        this.canAddWebPagePreviews = canAddWebPagePreviews;
    }

    @Nullable
    public Boolean getCanChangeInfo() {
        return canChangeInfo;
    }

    public void setCanChangeInfo(@Nullable Boolean canChangeInfo) {
        this.canChangeInfo = canChangeInfo;
    }

    @Nullable
    public Boolean getCanInviteUsers() {
        return canInviteUsers;
    }

    public void setCanInviteUsers(@Nullable Boolean canInviteUsers) {
        this.canInviteUsers = canInviteUsers;
    }

    @Nullable
    public Boolean getCanPinMessages() {
        return canPinMessages;
    }

    public void setCanPinMessages(@Nullable Boolean canPinMessages) {
        this.canPinMessages = canPinMessages;
    }

    @Override
    public String toString() {
        return "ChatPermissions{" +
                "canSendMessages=" + canSendMessages +
                ", canSendMediaMessages=" + canSendMediaMessages +
                ", canSendPolls=" + canSendPolls +
                ", canSendOtherMessages=" + canSendOtherMessages +
                ", canAddWebPagePreviews=" + canAddWebPagePreviews +
                ", canChangeInfo=" + canChangeInfo +
                ", canInviteUsers=" + canInviteUsers +
                ", canPinMessages=" + canPinMessages +
                '}';
    }
}
