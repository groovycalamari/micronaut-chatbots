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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * This object represents an answer of a user in a non-anonymous poll.
 * @see <a href="https://core.telegram.org/bots/api#pollanswer">Poll Answer</a>
 */
@Introspected
public class PollAnswer {
    /**
     * Unique poll identifier.
     */
    @NotBlank
    @Nonnull
    @JsonProperty("poll_id")
    private String pollId;

    /**
     * The user, who changed the answer to the poll.
     */
    @Nonnull
    @NotNull
    @Valid
    private User user;

    /**
     * 0-based identifiers of answer options, chosen by the user. May be empty if the user retracted their vote.
     */
    @Nonnull
    @NotNull
    @JsonProperty("option_ids")
    private List<Integer> optionIds;

    public PollAnswer() {
    }


    @Nonnull
    public String getPollId() {
        return pollId;
    }

    public void setPollId(@Nonnull String pollId) {
        this.pollId = pollId;
    }

    @Nonnull
    public User getUser() {
        return user;
    }

    public void setUser(@Nonnull User user) {
        this.user = user;
    }

    @Nonnull
    public List<Integer> getOptionIds() {
        return optionIds;
    }

    public void setOptionIds(@Nonnull List<Integer> optionIds) {
        this.optionIds = optionIds;
    }

    @Override
    public String toString() {
        return "PollAnswer{" +
                "pollId='" + pollId + '\'' +
                ", user=" + (user != null ? user.toString() : "") +
                ", optionIds=" + optionIds +
                '}';
    }
}
