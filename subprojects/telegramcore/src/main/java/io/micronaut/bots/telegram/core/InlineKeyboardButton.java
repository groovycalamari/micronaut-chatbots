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

import edu.umd.cs.findbugs.annotations.Nullable;
import javax.validation.constraints.NotBlank;

/**
 * This object represents one button of an inline keyboard. You must use exactly one of the optional fields.
 * @see <a href="https://core.telegram.org/bots/api#inlinekeyboardbutton">InlineKeyboardButton</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineKeyboardButton {

    /**
     * Label text on the button.
     */
    @NonNull
    @NotBlank
    private String text;

    /**
     * HTTP or tg:// url to be opened when button is pressed
     */
    @Nullable
    private String url;

    /**
     *  An HTTP URL used to automatically authorize the user. Can be used as a replacement for the Telegram Login Widget.
     */
    @Nullable
    @JsonProperty("login_url")
    private LoginUrl loginUrl;

    /**
     * Data to be sent in a callback query to the bot when button is pressed, 1-64 bytes.
     */
    @Nullable
    @JsonProperty("callback_data")
    private String callbackData;

    /**
     * If set, pressing the button will prompt the user to select one of their chats, open that chat and insert the bot‘s username and the specified inline query in the input field. Can be empty, in which case just the bot’s username will be inserted.
     * Note: This offers an easy way for users to start using your bot in inline mode when they are currently in a private chat with it. Especially useful when combined with switch_pm… actions – in this case the user will be automatically returned to the chat they switched from, skipping the chat selection screen.
     */
    @Nullable
    @JsonProperty("switch_inline_query")
    private String switchInlineQuery;

    /**
     * If set, pressing the button will insert the bot‘s username and the specified inline query in the current chat's input field. Can be empty, in which case only the bot’s username will be inserted.
     * This offers a quick way for the user to open your bot in inline mode in the same chat – good for selecting something from multiple options.
     */
    @Nullable
    @JsonProperty("switch_inline_query_current_chat")
    private String switchInlineQueryCurrentChat;

    /**
     * Description of the game that will be launched when the user presses the button.
     *
     * NOTE: This type of button must always be the first button in the first row.
     *
     */
    @Nullable
    @JsonProperty("callback_game")
    private CallbackGame callbackGame;

    /**
     * Specify True, to send a Pay button.
     * NOTE: This type of button must always be the first button in the first row.
     */
    @Nullable
    private Boolean pay;

    public InlineKeyboardButton() {
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Nullable
    public LoginUrl getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(@Nullable LoginUrl loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Nullable
    public String getCallbackData() {
        return callbackData;
    }

    public void setCallbackData(@Nullable String callbackData) {
        this.callbackData = callbackData;
    }

    @Nullable
    public String getSwitchInlineQuery() {
        return switchInlineQuery;
    }

    public void setSwitchInlineQuery(@Nullable String switchInlineQuery) {
        this.switchInlineQuery = switchInlineQuery;
    }

    @Nullable
    public String getSwitchInlineQueryCurrentChat() {
        return switchInlineQueryCurrentChat;
    }

    public void setSwitchInlineQueryCurrentChat(@Nullable String switchInlineQueryCurrentChat) {
        this.switchInlineQueryCurrentChat = switchInlineQueryCurrentChat;
    }

    @Nullable
    public CallbackGame getCallbackGame() {
        return callbackGame;
    }

    public void setCallbackGame(@Nullable CallbackGame callbackGame) {
        this.callbackGame = callbackGame;
    }

    @Nullable
    public Boolean getPay() {
        return pay;
    }

    public void setPay(@Nullable Boolean pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "InlineKeyboardButton{" +
                "text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", loginUrl=" + (loginUrl != null ? loginUrl.toString() : "") +
                ", callbackData='" + callbackData + '\'' +
                ", switchInlineQuery='" + switchInlineQuery + '\'' +
                ", switchInlineQueryCurrentChat='" + switchInlineQueryCurrentChat + '\'' +
                ", callbackGame=" + callbackGame +
                ", pay=" + pay +
                '}';
    }
}
