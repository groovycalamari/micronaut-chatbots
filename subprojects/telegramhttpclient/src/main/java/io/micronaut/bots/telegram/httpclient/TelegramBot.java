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
package io.micronaut.bots.telegram.httpclient;

import io.micronaut.bots.telegram.core.AnswerCallbackQuery;
import io.micronaut.bots.telegram.core.ForwardMessage;
import io.micronaut.bots.telegram.core.GetUserProfilePhotos;
import io.micronaut.bots.telegram.core.SendAudio;
import io.micronaut.bots.telegram.core.SendContact;
import io.micronaut.bots.telegram.core.SendDocument;
import io.micronaut.bots.telegram.core.SendMessage;
import io.micronaut.bots.telegram.core.SendPhoto;
import io.micronaut.bots.telegram.core.SendPoll;
import io.micronaut.bots.telegram.core.SendVenue;
import io.micronaut.bots.telegram.core.SendVideo;
import io.micronaut.bots.telegram.core.SendVoice;
import io.micronaut.bots.telegram.core.User;
import io.micronaut.bots.telegram.core.UserProfilePhotos;
import io.micronaut.http.HttpResponse;
import io.reactivex.Single;
import edu.umd.cs.findbugs.annotations.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TelegramBot {

    @NotBlank
    @NonNull
    private String token;

    @NotBlank
    @NonNull
    private String atUsername;

    private TelegramApi telegramApi;

    public TelegramBot(String token, String atUsername, TelegramApi telegramApi) {
        this.token = token;
        this.atUsername = atUsername;
        this.telegramApi = telegramApi;
    }

    public Single<User> getMe() {
        return this.telegramApi.getMe(token);
    }

    public Single<MessageSent> sendMessage(@NonNull @NotNull @Valid SendMessage sendMessage) {
        return this.telegramApi.sendMessage(token, sendMessage);
    }

    public Single<MessageSent> forwardMessage(@NonNull @NotNull @Valid ForwardMessage forwardMessage) {
        return this.telegramApi.forwardMessage(token, forwardMessage);
    }

    public Single<MessageSent> sendPhoto(@NonNull @NotNull @Valid SendPhoto sendPhoto){
        return this.telegramApi.sendPhoto(token, sendPhoto);
    }

    public Single<MessageSent> sendAudio(@NonNull @NotNull @Valid SendAudio sendAudio) {
        return this.telegramApi.sendAudio(token, sendAudio);
    }

    public Single<MessageSent> sendDocument(@NonNull @NotNull @Valid SendDocument sendDocument) {
        return this.telegramApi.sendDocument(token, sendDocument);
    }

    public Single<MessageSent> sendVideo(@NonNull @NotNull @Valid SendVideo sendVideo) {
        return this.telegramApi.sendVideo(token, sendVideo);
    }

    public Single<MessageSent> sendAnimation(@NonNull @NotNull @Valid SendVideo sendVideo) {
        return this.telegramApi.sendVideo(token, sendVideo);
    }

    public Single<MessageSent> sendVoice(@NonNull @NotNull @Valid SendVoice sendVoice) {
        return this.telegramApi.sendVoice(token, sendVoice);
    }

    public Single<MessageSent> sendVenue(@NonNull @NotNull @Valid SendVenue sendMessage) {
        return this.telegramApi.sendVenue(token, sendMessage);
    }

    public Single<MessageSent> sendContact(@NonNull @NotNull @Valid SendContact sendContact) {
        return this.telegramApi.sendContact(token, sendContact);
    }

    public Single<MessageSent> sendPoll(@NonNull @NotNull @Valid SendPoll sendPoll) {
        return this.telegramApi.sendPoll(token, sendPoll);
    }

    public Single<HttpResponse> answerCallbackQuery(@NonNull @NotNull @Valid AnswerCallbackQuery answerCallbackQuery) {
        return this.telegramApi.answerCallbackQuery(token, answerCallbackQuery);
    }

    public Single<UserProfilePhotos> getUserProfilePhotos(@NonNull @NotNull @Valid GetUserProfilePhotos getUserProfilePhotos) {
        return this.telegramApi.getUserProfilePhotos(token, getUserProfilePhotos);
    }

    @NonNull
    public String getToken() {
        return this.token;
    }

    @NonNull
    public String getAtUsername() {
        return atUsername;
    }
}
