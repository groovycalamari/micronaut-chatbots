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
public class GoogleChatCard {
    /**
     * The header of the card. A header usually contains a title and an image.
     */
    private GoogleChatCardHeader header;

    /**
     * Sections are separated by a line divider.
     */
    private List<GoogleChatCardSection> sections;

    /**
     * The actions of this card.
     */
    private List<GoogleChatCardAction> cardActions;

    /**
     * Name of the card.
     */
    private String name;

    public GoogleChatCard() {
    }

    public GoogleChatCardHeader getHeader() {
        return header;
    }

    public void setHeader(GoogleChatCardHeader header) {
        this.header = header;
    }

    public List<GoogleChatCardSection> getSections() {
        return sections;
    }

    public void setSections(List<GoogleChatCardSection> sections) {
        this.sections = sections;
    }

    public List<GoogleChatCardAction> getCardActions() {
        return cardActions;
    }

    public void setCardActions(List<GoogleChatCardAction> cardActions) {
        this.cardActions = cardActions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GoogleChatCard{" +
                "header=" + header != null ? header.toString() : null +
                ", sections=" + (sections != null ? sections.stream().map(GoogleChatCardSection::toString).collect(Collectors.joining(",")) : null) +
                ", cardActions=" + (cardActions != null ? cardActions.stream().map(GoogleChatCardAction::toString).collect(Collectors.joining(",")) : null) +
                ", name='" + name + '\'' +
                '}';
    }
}
