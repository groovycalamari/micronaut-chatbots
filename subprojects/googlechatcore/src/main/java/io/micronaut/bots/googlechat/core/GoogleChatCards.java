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
import io.micronaut.bots.core.ChatBotMessageSend;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A card is a UI element that can contain UI widgets such as texts, images.
 * @see ≤a href="https://developers.google.com/hangouts/chat/reference/rest/v1/cards">Cards</a>
 */
@Introspected
public class GoogleChatCards implements ChatBotMessageSend  {

    @NonNull
    @NotNull
    @NotEmpty
    private List<GoogleChatCard> cards;

    public GoogleChatCards() {
    }

    public GoogleChatCards(@NonNull @NotNull @NotEmpty List<GoogleChatCard> cards) {
        this.cards = cards;
    }

    @NonNull
    public List<GoogleChatCard> getCards() {
        return cards;
    }

    public void setCards(@NonNull List<GoogleChatCard> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "GoogleChatCards{" +
                "cards=" + (cards != null ? cards.stream().map(GoogleChatCard::toString).collect(Collectors.joining(",")) : null) +
                '}';
    }
}
