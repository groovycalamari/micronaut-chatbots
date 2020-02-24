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

@Introspected
public class MaskPosition {

    /**
     * The part of the face relative to which the mask should be placed. One of “forehead”, “eyes”, “mouth”, or “chin”.
     */
    @Nonnull
    @NotBlank
    private String point;

    /**
     * Shift by X-axis measured in widths of the mask scaled to the face size, from left to right. For example, choosing -1.0 will place mask just to the left of the default mask position.
     */
    @Nonnull
    @NotNull
    @JsonProperty("x_shift")
    private Float xShift;

    /**
     * Shift by Y-axis measured in heights of the mask scaled to the face size, from top to bottom. For example, 1.0 will place the mask just below the default mask position.
     */
    @Nonnull
    @NotNull
    @JsonProperty("y_shift")
    private Float yShift;

    /**
     * Mask scaling coefficient. For example, 2.0 means double size.
     */
    @Nonnull
    @NotNull
    private Float scale;

    public MaskPosition() {
    }

    @Nonnull
    public String getPoint() {
        return point;
    }

    public void setPoint(@Nonnull String point) {
        this.point = point;
    }

    @Nonnull
    public Float getxShift() {
        return xShift;
    }

    public void setxShift(@Nonnull Float xShift) {
        this.xShift = xShift;
    }

    @Nonnull
    public Float getyShift() {
        return yShift;
    }

    public void setyShift(@Nonnull Float yShift) {
        this.yShift = yShift;
    }

    @Nonnull
    public Float getScale() {
        return scale;
    }

    public void setScale(@Nonnull Float scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "MaskPosition{" +
                "point='" + point + '\'' +
                ", xShift=" + xShift +
                ", yShift=" + yShift +
                ", scale=" + scale +
                '}';
    }
}
