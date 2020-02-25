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
import javax.validation.constraints.NotBlank;

/**
 * This object represents a shipping address.
 * @see <a href="https://core.telegram.org/bots/api#shippingaddress">Shipping Address</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingAddress {

    /**
     * ISO 3166-1 alpha-2 country code
     */
    @JsonProperty("country_code")
    @Nonnull
    @NotBlank
    private String countryCode;

    /**
     * State, if applicable
     */
    @Nonnull
    @NotBlank
    private String state;

    /**
     * City
     */
    @Nonnull
    @NotBlank
    private String city;

    /**
     * First line for the address.
     */
    @Nonnull
    @NotBlank
    @JsonProperty("street_line1")
    private String streetLine1;

    /**
     * Second line for the address
     */
    @Nonnull
    @NotBlank
    @JsonProperty("street_line2")
    private String streetLine2;

    /**
     * Address post codes.
     */
    @Nonnull
    @NotBlank
    @JsonProperty("post_code")
    private String postCode;

    public ShippingAddress() {
    }

    @Nonnull
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(@Nonnull String countryCode) {
        this.countryCode = countryCode;
    }

    @Nonnull
    public String getState() {
        return state;
    }

    public void setState(@Nonnull String state) {
        this.state = state;
    }

    @Nonnull
    public String getCity() {
        return city;
    }

    public void setCity(@Nonnull String city) {
        this.city = city;
    }

    @Nonnull
    public String getStreetLine1() {
        return streetLine1;
    }

    public void setStreetLine1(@Nonnull String streetLine1) {
        this.streetLine1 = streetLine1;
    }

    @Nonnull
    public String getStreetLine2() {
        return streetLine2;
    }

    public void setStreetLine2(@Nonnull String streetLine2) {
        this.streetLine2 = streetLine2;
    }

    @Nonnull
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(@Nonnull String postCode) {
        this.postCode = postCode;
    }


    @Override
    public String toString() {
        return "ShippingAddress{" +
                "countryCode='" + countryCode + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", streetLine1='" + streetLine1 + '\'' +
                ", streetLine2='" + streetLine2 + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
