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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This object contains basic information about a successful payment.
 * @see <a href="https://core.telegram.org/bots/api#successfulpayment">SuccessfulPayment</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessfulPayment {
    /**
     * Three-letter ISO 4217 currency code.
     */
    @NonNull
    @NotBlank
    private String currency;

    /**
     * Total price in the smallest units of the currency (integer, not float/double). For example, for a price of US$ 1.45 pass amount = 145. See the exp parameter in currencies.json, it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    @NonNull
    @NotNull
    @JsonProperty("total_amount")
    private Integer	totalAmount;

    /**
     * Bot specified invoice payload
     */
    @NonNull
    @NotBlank
    @JsonProperty("invoice_payload")
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
    @JsonProperty("order_info")
    @Valid
    private OrderInfo orderInfo;

    /**
     * Telegram payment identifier
     */
    @NonNull
    @NotBlank
    @JsonProperty("telegram_payment_charge_id")
    private String telegramPaymentChargeId;

    /**
     * Provider payment identifier
     */
    @NonNull
    @NotBlank
    @JsonProperty("provider_payment_charge_id")
    private String providerPaymentChargeId;

    @Override
    public String toString() {
        return "SuccessfulPayment{" +
                "currency='" + currency + '\'' +
                ", totalAmount=" + totalAmount +
                ", invoicePayload='" + invoicePayload + '\'' +
                ", shippingOptionId='" + shippingOptionId + '\'' +
                ", orderInfo=" + (orderInfo != null ? orderInfo.toString() : "") +
                ", telegramPaymentChargeId='" + telegramPaymentChargeId + '\'' +
                ", providerPaymentChargeId='" + providerPaymentChargeId + '\'' +
                '}';
    }
}
