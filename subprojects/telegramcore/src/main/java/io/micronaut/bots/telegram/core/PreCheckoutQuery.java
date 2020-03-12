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
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @see <a href="https://core.telegram.org/bots/api#precheckoutquery">PreChekoutQuery</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PreCheckoutQuery {

    /**
     * Unique query identifier
     */
    @NonNull
    @NotBlank
    private String id;
    /**
     * User who sent the query
     */

    @NonNull
    @NotNull
    @Valid
    private User from;

    /**
     * Three-letter ISO 4217 currency code
     */
    @NonNull
    @NotBlank
    private String currency;

    /**
     * Total price in the smallest units of the currency (integer, not float/double). For example, for a price of US$ 1.45 pass amount = 145. See the exp parameter in currencies.json, it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    @JsonProperty("total_amount")
    @NonNull
    @NotNull
    private Integer totalAmount;

    /**
     * Bot specified invoice payload
     */
    @JsonProperty("invoice_payload")
    @NonNull
    @NotBlank
    private String invoicePayload;
    /**
     * Identifier of the shipping option chosen by the user
     */
    @Nullable
    @JsonProperty("shipping_option_id")
    private String shippingOptionId;
    /**
     * Order info provided by the user
     */
    @Nullable
    @Valid
    @JsonProperty("order_info")
    private OrderInfo orderInfo;

    public PreCheckoutQuery() {
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public User getFrom() {
        return from;
    }

    public void setFrom(@NonNull User from) {
        this.from = from;
    }

    @NonNull
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(@NonNull String currency) {
        this.currency = currency;
    }

    @NonNull
    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(@NonNull Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @NonNull
    public String getInvoicePayload() {
        return invoicePayload;
    }

    public void setInvoicePayload(@NonNull String invoicePayload) {
        this.invoicePayload = invoicePayload;
    }

    public String getShippingOptionId() {
        return shippingOptionId;
    }

    public void setShippingOptionId(String shippingOptionId) {
        this.shippingOptionId = shippingOptionId;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Override
    public String toString() {
        return "PreCheckoutQuery{" +
                "id='" + id + '\'' +
                ", from=" + (from != null ? from.toString() : "") +
                ", currency='" + currency + '\'' +
                ", totalAmount=" + totalAmount +
                ", invoicePayload='" + invoicePayload + '\'' +
                ", shippingOptionId='" + shippingOptionId + '\'' +
                ", orderInfo=" + (orderInfo != null ? orderInfo.toString() : "") +
                '}';
    }
}

