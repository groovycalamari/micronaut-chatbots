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

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.bots.core.ChatBot;

import javax.validation.constraints.NotBlank;

public class GoogleChatBot implements ChatBot {
    @NotBlank
    @NonNull
    private String projectId;

    @NotBlank
    @NonNull
    private String atUsername;

    public GoogleChatBot(String projectId, String atUsername) {
        this.projectId = projectId;
        this.atUsername = atUsername;
    }


    @Override
    public String getAtUsername() {
        return atUsername;
    }

    public void setAtUsername(@NonNull String atUsername) {
        this.atUsername = atUsername;
    }

    @NonNull
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(@NonNull String projectId) {
        this.projectId = projectId;
    }
}
