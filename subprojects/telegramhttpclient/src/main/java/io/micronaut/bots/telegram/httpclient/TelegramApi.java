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

import edu.umd.cs.findbugs.annotations.NonNull;
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
import io.micronaut.http.annotation.PathVariable;
import io.reactivex.Single;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface TelegramApi {

    Single<User> getMe(@PathVariable @NonNull @NotBlank String token);

    Single<MessageSent> forwardMessage(@PathVariable @NonNull @NotBlank String token,
                                       @Body @NonNull @NotNull @Valid ForwardMessage forwardMessage);

    Single<MessageSent> sendMessage(@PathVariable @NonNull @NotBlank String token,
                                    @Body @NonNull @NotNull @Valid SendMessage sendMessage);

    Single<MessageSent> sendPhoto(@PathVariable @NonNull @NotBlank String token,
                                  @Body @NonNull @NotNull @Valid SendPhoto sendPhoto);

    Single<MessageSent> sendAudio(@PathVariable @NonNull @NotBlank String token,
                                  @Body @NonNull @NotNull @Valid SendAudio sendAudio);

    Single<MessageSent> sendDocument(@PathVariable @NonNull @NotBlank String token,
                                     @Body @NonNull @NotNull @Valid SendDocument sendDocument);

    Single<MessageSent> sendVideo(@PathVariable @NonNull @NotBlank String token,
                                  @Body @NonNull @NotNull @Valid SendVideo sendVideo);

    Single<MessageSent> sendVoice(@PathVariable @NonNull @NotBlank String token,
                                  @Body @NonNull @NotNull @Valid SendVoice sendVoice);

    Single<MessageSent> sendVenue(@PathVariable @NonNull @NotBlank String token,
                                  @Body @NonNull @NotNull @Valid SendVenue sendMessage);

    /**
     * Use this method to send phone contacts.
     * @param token Telegram Bot Token
     * @param sendContact Contact
     * @return The sent Message
     */
    Single<MessageSent> sendContact(@PathVariable @NonNull @NotBlank String token,
                                    @Body @NonNull @NotNull @Valid SendContact sendContact);

    /**
     * Use this method to send a native poll.
     * @param token Telegram Bot Token
     * @param sendPoll poll
     * @return The sent Message
     */
    Single<MessageSent> sendPoll(@PathVariable @NonNull @NotBlank String token,
                                 @Body @NonNull @NotNull @Valid SendPoll sendPoll);

    /**
     * Use this method to send a native poll.
     * @param token Telegram Bot Token
     * @param getUserProfilePhotos Request User Profile Photos
     * @return The sent Message
     */
    Single<UserProfilePhotos> getUserProfilePhotos(@PathVariable @NonNull @NotBlank String token,
                                                   @Body @NonNull @NotNull @Valid GetUserProfilePhotos getUserProfilePhotos);


    Single<HttpResponse> answerCallbackQuery(@PathVariable @NonNull @NotBlank String token,
                                             @Body @NonNull @NotNull @Valid AnswerCallbackQuery answerCallbackQuery);

}
