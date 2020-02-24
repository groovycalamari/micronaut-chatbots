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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This object contains basic information about an invoice.
 * @see <a href="https://core.telegram.org/bots/api#invoice">Invoice</a>
 */
@Introspected
public class Invoice {
    /**
     * Product name
     */
    @Nonnull
    @NotBlank
    private String title;

    /**
     * Product description
     */
    @Nonnull
    @NotBlank
    private String description;

    /**
     * Unique bot deep-linking parameter that can be used to generate this invoice
     */
    @JsonProperty("start_parameter")
    @Nonnull
    @NotBlank
    private String startParameter;

    /**
     * Three-letter ISO 4217 currency code
     */
    @Nonnull
    @NotBlank
    private String currency;

    /**
     * Total price in the smallest units of the currency (integer, not float/double). For example, for a price of US$ 1.45 pass amount = 145. See the exp parameter in currencies.json, it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    @JsonProperty("total_amount")
    @Nonnull
    @NotNull
    private Integer totalAmount;

    public Invoice() {
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull String title) {
        this.title = title;
    }

    @Nonnull
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nonnull String description) {
        this.description = description;
    }

    @Nonnull
    public String getStartParameter() {
        return startParameter;
    }

    public void setStartParameter(@Nonnull String startParameter) {
        this.startParameter = startParameter;
    }

    @Nonnull
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(@Nonnull String currency) {
        this.currency = currency;
    }

    @Nonnull
    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(@Nonnull Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startParameter='" + startParameter + '\'' +
                ", currency='" + currency + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
