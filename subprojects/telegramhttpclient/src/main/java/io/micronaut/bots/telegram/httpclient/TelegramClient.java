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
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

import edu.umd.cs.findbugs.annotations.NonNull;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Client("https://api.telegram.org")
public interface TelegramClient extends TelegramApi {

    @Override
    @Post("/bot{token}/getMe")
    Single<User> getMe(@PathVariable @NonNull @NotBlank String token);

    @Override
    @Post("/bot{token}/sendMessage")
    Single<MessageSent> sendMessage(@PathVariable @NonNull @NotBlank String token,
                                    @Body @NonNull @NotNull @Valid SendMessage sendMessage);

    @Override
    @Post("/bot{token}/forwardMessage")
    Single<MessageSent> forwardMessage(@PathVariable @NonNull @NotBlank String token,
                                       @Body @NonNull @NotNull @Valid ForwardMessage forwardMessage);

    @Override
    @Post("/bot{token}/sendPhoto")
    Single<MessageSent> sendPhoto(@PathVariable @NonNull @NotBlank String token,
                                  @Body @NonNull @NotNull @Valid SendPhoto sendPhoto);

    @Override
    @Post("/bot{token}/sendAudio")
    Single<MessageSent> sendAudio(@PathVariable @NonNull @NotBlank String token,
                                  @Body @NonNull @NotNull @Valid SendAudio sendAudio);

    @Override
    @Post("/bot{token}/sendDocument")
    Single<MessageSent> sendDocument(@PathVariable @NonNull @NotBlank String token,
                                     @Body @NonNull @NotNull @Valid SendDocument sendDocument);

    @Override
    @Post("/bot{token}/sendVideo")
    Single<MessageSent> sendVideo(@PathVariable @NonNull @NotBlank String token,
                                  @Body @NonNull @NotNull @Valid SendVideo sendVideo);

    @Override
    @Post("/bot{token}/sendVoice")
    Single<MessageSent> sendVoice(@PathVariable @NonNull @NotBlank String token,
                                  @Body @NonNull @NotNull @Valid SendVoice sendVoice);

    @Override
    @Post("/bot{token}/sendVenue")
    Single<MessageSent> sendVenue(@PathVariable @NonNull @NotBlank String token,
                                  @Body @NonNull @NotNull @Valid SendVenue sendMessage);

    @Override
    @Post("/bot{token}/sendContact")
    Single<MessageSent> sendContact(@PathVariable @NonNull @NotBlank String token,
                                    @Body @NonNull @NotNull @Valid SendContact sendContact);


    @Override
    @Post("/bot{token}/sendPoll")
    Single<MessageSent> sendPoll(@PathVariable @NonNull @NotBlank String token,
                                 @Body @NonNull @NotNull @Valid SendPoll sendPoll);

    @Override
    @Get("/bot{token}/getUserProfilePhotos")
    Single<UserProfilePhotos> getUserProfilePhotos(@PathVariable @NonNull @NotBlank String token,
                                                   @NonNull @NotNull @Valid GetUserProfilePhotos getUserProfilePhotos);


    @Override
    @Post("/bot{token}/answerCallbackQuery")
    Single<HttpResponse> answerCallbackQuery(@PathVariable @NonNull @NotBlank String token,
                                             @Body @NonNull @NotNull @Valid AnswerCallbackQuery answerCallbackQuery);
}
