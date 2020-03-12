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
 * This object represents a phone contact.
 * @see <a href="https://core.telegram.org/bots/api#contact">This object represents a phone contact.</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contact {

    /**
     * Contact's phone number.
     */
    @JsonProperty("phone_number")
    @NonNull
    @NotBlank
    private String phoneNumber;

    /**
     * Contact's first name
     */
    @NonNull
    @NotBlank
    @JsonProperty("first_name")
    private String firstName;
    /**
     * Contact's last name.
     */
    @Nullable
    @JsonProperty("last_name")
    private String lastName;

    /**
     * Contact's user identifier in Telegram
     */
    @Nullable
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * Additional data about the contact in the form of a vCard
     */
    @Nullable
    private String vcard;

    public Contact() {
    }

    @NonNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@Nullable String lastName) {
        this.lastName = lastName;
    }

    @Nullable
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(@Nullable Integer userId) {
        this.userId = userId;
    }

    @Nullable
    public String getVcard() {
        return vcard;
    }

    public void setVcard(@Nullable String vcard) {
        this.vcard = vcard;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userId=" + userId +
                ", vcard='" + vcard + '\'' +
                '}';
    }
}
