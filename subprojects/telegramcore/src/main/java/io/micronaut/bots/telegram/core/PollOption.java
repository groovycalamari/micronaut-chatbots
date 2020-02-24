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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This object contains information about one answer option in a poll.
 * @see <a href="https://core.telegram.org/bots/api#polloption">Poll Option</a>
 */
@Introspected
public class PollOption {
    /**
     * Option text, 1-100 characters.
     */
    @Nonnull
    @NotBlank
    private String text;

    /**
     * Number of users that voted for this option
     */
    @JsonProperty("voter_count")
    @Nonnull
    @NotNull
    private Integer voterCount;

    public PollOption() {
    }

    @Nonnull
    public String getText() {
        return text;
    }

    public void setText(@Nonnull String text) {
        this.text = text;
    }

    @Nonnull
    public Integer getVoterCount() {
        return voterCount;
    }

    public void setVoterCount(@Nonnull Integer voterCount) {
        this.voterCount = voterCount;
    }


    @Override
    public String toString() {
        return "PollOption{" +
                "text='" + text + '\'' +
                ", voterCount=" + voterCount +
                '}';
    }
}
