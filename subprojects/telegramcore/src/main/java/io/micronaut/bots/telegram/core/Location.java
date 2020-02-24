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

import io.micronaut.core.annotation.Introspected;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

/**
 * This object represents a point on the map.
 * @see <a href="https://core.telegram.org/bots/api#location">Location</a>
 */
@Introspected
public class Location {

    /**
     * Longitude as defined by sender.
     */
    @Nonnull
    @NotNull
    private Float longitude;

    /**
     * Latitude as defined by sender
     */
    @Nonnull
    @NotNull
    private Float latitude;

    public Location() {
    }

    @Nonnull
    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(@Nonnull Float longitude) {
        this.longitude = longitude;
    }

    @Nonnull
    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(@Nonnull Float latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
