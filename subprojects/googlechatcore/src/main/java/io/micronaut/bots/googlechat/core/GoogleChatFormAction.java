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

import io.micronaut.core.annotation.Introspected;

import java.util.List;
import java.util.stream.Collectors;

@Introspected
public class GoogleChatFormAction {
    /**
     * Apps Script function to invoke when the containing element is clicked/activated.
     */
    private String actionMethodName;

    /**
     * List of action parameters.
     */
    List<GoogleChatActionParameter> parameters;

    public GoogleChatFormAction() {
    }

    public GoogleChatFormAction(String actionMethodName) {
        this.actionMethodName = actionMethodName;
    }

    public String getActionMethodName() {
        return actionMethodName;
    }

    public void setActionMethodName(String actionMethodName) {
        this.actionMethodName = actionMethodName;
    }

    public List<GoogleChatActionParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<GoogleChatActionParameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "GoogleChatFormAction{" +
                "actionMethodName='" + actionMethodName + '\'' +
                ", parameters=" + (parameters != null ? parameters.stream().map(GoogleChatActionParameter::toString).collect(Collectors.joining(",")) : null)  +
                '}';
    }
}
