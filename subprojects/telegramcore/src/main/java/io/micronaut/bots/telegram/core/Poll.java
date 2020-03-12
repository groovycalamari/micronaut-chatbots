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
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This object contains information about a poll.
 * @see <a href="https://core.telegram.org/bots/api#poll">Poll</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Poll {

    /**
     * Unique poll identifier;
     */
    @NonNull
    @NotBlank
    private String id;

    /**
     * Poll question, 1-255 characters
     */
    @NonNull
    @NotBlank
    private String question;

    /**
     * List of poll options
     */
    @NonNull
    @NotNull
    private List<PollOption> options;

    /**
     * Total number of users that voted in the poll.
     */
    @JsonProperty("total_voter_count")
    @NonNull
    @NotNull
    private Integer totalVoterCount;

    /**
     * True, if the poll is closed.
     */
    @JsonProperty("is_closed")
    @NonNull
    @NotNull
    private Boolean isClosed;

    /**
     * True, if the poll is anonymous
     */
    @JsonProperty("is_anonymous")
    @NonNull
    @NotNull
    private Boolean isAnonymous;

    /**
     * type, currently can be “regular” or “quiz”
     */
    @NonNull
    @NotBlank
    @Pattern(regexp = "regular|quiz")
    private String typePoll;

    /**
     * True, if the poll allows multiple answers
     */
    @JsonProperty("allows_multiple_answers")
    @NonNull
    @NotNull
    private Boolean allowsMultipleAnswers;

    /**
     * Optional. 0-based identifier of the correct answer option. Available only for polls in the quiz mode, which are closed, or was sent (not forwarded) by the bot or to the private chat with the bot.
     */
    @JsonProperty("correct_option_id")
    private Integer correctOptionId;

    public Poll() {
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getQuestion() {
        return question;
    }

    public void setQuestion(@NonNull String question) {
        this.question = question;
    }

    @NonNull
    public List<PollOption> getOptions() {
        return options;
    }

    public void setOptions(@NonNull List<PollOption> options) {
        this.options = options;
    }

    @NonNull
    public Integer getTotalVoterCount() {
        return totalVoterCount;
    }

    public void setTotalVoterCount(@NonNull Integer totalVoterCount) {
        this.totalVoterCount = totalVoterCount;
    }

    @NonNull
    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(@NonNull Boolean closed) {
        isClosed = closed;
    }

    @NonNull
    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(@NonNull Boolean anonymous) {
        isAnonymous = anonymous;
    }

    @NonNull
    public String getTypePoll() {
        return typePoll;
    }

    public void setTypePoll(@NonNull String typePoll) {
        this.typePoll = typePoll;
    }

    @NonNull
    public Boolean getAllowsMultipleAnswers() {
        return allowsMultipleAnswers;
    }

    public void setAllowsMultipleAnswers(@NonNull Boolean allowsMultipleAnswers) {
        this.allowsMultipleAnswers = allowsMultipleAnswers;
    }

    public Integer getCorrectOptionId() {
        return correctOptionId;
    }

    public void setCorrectOptionId(Integer correctOptionId) {
        this.correctOptionId = correctOptionId;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", options=" + (options != null ?  String.join(",",options.stream().map(PollOption::toString).collect(Collectors.toList())) : "") +
                ", totalVoterCount=" + totalVoterCount +
                ", isClosed=" + isClosed +
                ", isAnonymous=" + isAnonymous +
                ", typePoll='" + typePoll + '\'' +
                ", allowsMultipleAnswers=" + allowsMultipleAnswers +
                ", correctOptionId=" + correctOptionId +
                '}';
    }
}
