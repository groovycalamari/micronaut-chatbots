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

import io.micronaut.bots.core.ChatBotMessageReceive;
import io.micronaut.core.annotation.Introspected;

import javax.annotation.Nullable;

@Introspected
public class Event implements ChatBotMessageReceive {
    /**
     * The type of the event the bot is receiving.
     */
    private String type;
    /**
     * The timestamp (formatted according to RFC 3339) indicating when the event was dispatched
     */
    private String eventTime;

    private Space space;

    private Message message;

    private User user;

    @Nullable
    private GoogleChatFormAction action;

    /**
     * Constructor.
     */
    public Event() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Nullable
    public GoogleChatFormAction getAction() {
        return action;
    }

    public void setAction(@Nullable GoogleChatFormAction action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "MessageReceive{" +
                "type='" + type + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", space=" + (space != null ? space.toString() : null) +
                ", message=" + (message != null ? message.toString() : null) +
                ", user=" + (user != null ? user.toString() : null) +
                ", action=" + (action != null ? action.toString() : null) +
                '}';
    }
}
