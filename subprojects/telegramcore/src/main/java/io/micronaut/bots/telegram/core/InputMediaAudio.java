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
import io.micronaut.core.annotation.Introspected;

import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Represents an audio file to be treated as music to be sent.
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputMediaAudio extends InputMediaDocument {
    private static final String TYPE_AUDIO = "audio";


    /**
     * Duration of the audio in seconds.
     */
    @Nullable
    private Integer duration;

    /**
     * Performer of the audio.
     */
    @Nullable
    private String performer;

    /**
     * Title of the audio
     */
    @Nullable
    private String title;

    public InputMediaAudio() {
        super(TYPE_AUDIO);
    }

    @Nullable
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(@Nullable Integer duration) {
        this.duration = duration;
    }

    @Nullable
    public String getPerformer() {
        return performer;
    }

    public void setPerformer(@Nullable String performer) {
        this.performer = performer;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "InputMediaAudio{" +
                "duration=" + duration +
                ", performer='" + performer + '\'' +
                ", title='" + title + '\'' +
                super.toString() +
                '}';
    }
}
