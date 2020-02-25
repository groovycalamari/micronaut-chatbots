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
import io.micronaut.core.annotation.Introspected;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;

@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendContact extends Send  {

    /**
     * Contact's phone number.
     */
    @JsonProperty("phone_number")
    @NotBlank
    @Nonnull
    private String phoneNumber;

    /**
     * Contact's first name.
     */
    @JsonProperty("first_name")
    @NotBlank
    @Nonnull
    private String firstName;

    /**
     * Contact's last name.
     */
    @JsonProperty("last_name")
    @Nullable
    private String lastName;

    /**
     * Additional data about the contact in the form of a vCard, 0-2048 bytes
     */
    @Nullable
    private String vcard;

    public SendContact() {
        super("sendContact");
    }

    @Nonnull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Nonnull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Nonnull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Nonnull String firstName) {
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
    public String getVcard() {
        return vcard;
    }

    public void setVcard(@Nullable String vcard) {
        this.vcard = vcard;
    }
}
