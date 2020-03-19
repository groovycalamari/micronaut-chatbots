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
public class GoogleChatCardSection {
    private String header;
    private List<GoogleChatWidget> widgets;

    public GoogleChatCardSection() {
    }

    public GoogleChatCardSection(String header, List<GoogleChatWidget> widgets) {
        this.header = header;
        this.widgets = widgets;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<GoogleChatWidget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<GoogleChatWidget> widgets) {
        this.widgets = widgets;
    }

    @Override
    public String toString() {
        return "GoogleChatCardSection{" +
                "header='" + header + '\'' +
                ", widgets=" + (widgets != null ? widgets.stream().map(GoogleChatWidget::toString).collect(Collectors.joining(",")) : null) +
                '}';
    }
}
