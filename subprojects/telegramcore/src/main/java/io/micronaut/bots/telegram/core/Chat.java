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
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.bots.core.ChatBotSpace;
import io.micronaut.core.annotation.Introspected;

import edu.umd.cs.findbugs.annotations.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * This object represents a chat.
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chat implements ChatBotSpace {

    /**
     * Unique identifier for this chat. This number may be greater than 32 bits and some programming languages may have difficulty/silent defects in interpreting it. But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type are safe for storing this identifier.
     */
    @NonNull
    @NotNull
    private Integer id;

    /**
     * Type of chat, can be either “private”, “group”, “supergroup” or “channel”
     */
    @NonNull
    @NotNull
    @Pattern(regexp = "private|group|supergroup|channel")
    private String type;

    /**
     * Title, for supergroups, channels and group chats
     */
    @Nullable
    private String title;

    /**
     * Username, for private chats, supergroups and channels if available
     */
    @Nullable
    private String username;

    /**
     * First name of the other party in a private chat
     */
    @JsonProperty("first_name")
    @Nullable
    private String firstName;

    /**
     * Last name of the other party in a private chat
     */
    @JsonProperty("last_name")
    @Nullable
    private String lastName;

    /**
     * Chat photo. Returned only in getChat.
     */
    @Nullable
    private ChatPhoto photo;

    /**
     * Description, for groups, supergroups and channel chats. Returned only in getChat.
     */
    @Nullable
    private String description;

    /**
     * Chat invite link, for groups, supergroups and channel chats. Each administrator in a chat generates their own invite links, so the bot must first generate the link using exportChatInviteLink. Returned only in getChat.
     */
    @Nullable
    @JsonProperty("invite_link")
    private String inviteLink;

    /**
     * Pinned message, for groups, supergroups and channels. Returned only in getChat.
     */
    @Nullable
    @JsonProperty("pinned_message")
    private Message pinnedMessage;

    /**
     * Default chat member permissions, for groups and supergroups. Returned only in getChat.
     */
    @Nullable
    private ChatPermissions permissions;

    /**
     * For supergroups, the minimum allowed delay between consecutive messages sent by each unpriviledged user. Returned only in getChat.
     */
    @Nullable
    @JsonProperty("slow_mode_delay")
    private Integer slowModeDelay;

    /**
     * For supergroups, name of group sticker set. Returned only in getChat.
     */
    @Nullable
    @JsonProperty("sticker_set_name")
    private String stickerSetName;

    /**
     * True, if the bot can change the group sticker set. Returned only in getChat.
     */
    @Nullable
    @JsonProperty("can_set_sticker_set")
    private Boolean canSetStickerSet;

    public Chat() {
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    public void setUsername(@Nullable String username) {
        this.username = username;
    }

    @Nullable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Nullable String firstName) {
        this.firstName = firstName;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@Nullable String lastName) {
        this.lastName = lastName;
    }

    @Nullable
    public ChatPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(@Nullable ChatPhoto photo) {
        this.photo = photo;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getInviteLink() {
        return inviteLink;
    }

    public void setInviteLink(@Nullable String inviteLink) {
        this.inviteLink = inviteLink;
    }

    @Nullable
    public Message getPinnedMessage() {
        return pinnedMessage;
    }

    public void setPinnedMessage(@Nullable Message pinnedMessage) {
        this.pinnedMessage = pinnedMessage;
    }

    @Nullable
    public ChatPermissions getPermissions() {
        return permissions;
    }

    public void setPermissions(@Nullable ChatPermissions permissions) {
        this.permissions = permissions;
    }

    @Nullable
    public Integer getSlowModeDelay() {
        return slowModeDelay;
    }

    public void setSlowModeDelay(@Nullable Integer slowModeDelay) {
        this.slowModeDelay = slowModeDelay;
    }

    @Nullable
    public String getStickerSetName() {
        return stickerSetName;
    }

    public void setStickerSetName(@Nullable String stickerSetName) {
        this.stickerSetName = stickerSetName;
    }

    @Nullable
    public Boolean getCanSetStickerSet() {
        return canSetStickerSet;
    }

    public void setCanSetStickerSet(@Nullable Boolean canSetStickerSet) {
        this.canSetStickerSet = canSetStickerSet;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", photo=" + (photo != null ? photo.toString() : "") +
                ", description='" + description + '\'' +
                ", inviteLink='" + inviteLink + '\'' +
                ", pinnedMessage=" + (pinnedMessage != null ? pinnedMessage.toString() : "") +
                ", permissions=" + (permissions != null ? permissions.toString() : "") +
                ", slowModeDelay=" + slowModeDelay +
                ", stickerSetName='" + stickerSetName + '\'' +
                ", canSetStickerSet=" + canSetStickerSet +
                '}';
    }
}

