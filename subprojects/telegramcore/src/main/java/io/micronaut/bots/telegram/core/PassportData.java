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
import io.micronaut.core.annotation.Introspected;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains information about Telegram Passport data shared with the bot by the user.
 * @see <a href="https://core.telegram.org/bots/api#passportdata">PassportData</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PassportData {
    /**
     * Array with information about documents and other Telegram Passport elements that was shared with the bot
     */
    private List<EncryptedPassportElement> data;

    /**
     * Encrypted credentials required to decrypt the data.
     */
    private EncryptedCredentials credentials;

    public PassportData() {
    }

    public List<EncryptedPassportElement> getData() {
        return data;
    }

    public void setData(List<EncryptedPassportElement> data) {
        this.data = data;
    }

    public EncryptedCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(EncryptedCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "PassportData{" +
                "data=" + (data != null ?  String.join(",",data.stream().map(EncryptedPassportElement::toString).collect(Collectors.toList())) : "") +
                ", credentials=" + (credentials !=null ? credentials.toString() : "") +
                '}';
    }
}
