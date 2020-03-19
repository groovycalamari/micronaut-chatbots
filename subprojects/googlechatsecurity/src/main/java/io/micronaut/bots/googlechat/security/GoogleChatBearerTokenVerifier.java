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
package io.micronaut.bots.googlechat.security;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.context.annotation.DefaultImplementation;

import javax.validation.constraints.NotBlank;
import java.util.List;

@DefaultImplementation(DefaultGoogleChatBearerTokenVerifier.class)
public interface GoogleChatBearerTokenVerifier {
    /**
     *
     * @param bearerToken Bearer Token
     * @return Audiencies Claim if exists
     * @throws UnauthorizedGoogleChatToken if the token failed to verify
     */
    @Nullable
    List<String> verify(@NonNull @NotBlank String bearerToken) throws UnauthorizedGoogleChatToken;
}
