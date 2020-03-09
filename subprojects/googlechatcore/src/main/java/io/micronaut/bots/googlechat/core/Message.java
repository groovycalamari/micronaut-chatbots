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
package io.micronaut.bots.googlechat.core;

import io.micronaut.core.annotation.Introspected;

import java.util.List;
import java.util.stream.Collectors;

@Introspected
public class Message {

    private String name;
    private User sender;
    private String createTime;
    private String text;
    private Thread thread;
    private List<Annotation> annotations;
    private String argumentText;

    public Message() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public String getArgumentText() {
        return argumentText;
    }

    public void setArgumentText(String argumentText) {
        this.argumentText = argumentText;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", sender=" + (sender != null ? sender.toString() : null) +
                ", createTime='" + createTime + '\'' +
                ", text='" + text + '\'' +
                ", thread=" + (thread != null ? thread.toString() : null)  +
                ", annotations=" + (annotations != null ? annotations.stream().map(Annotation::toString).collect(Collectors.joining(",")) : null)  +
                ", argumentText='" + argumentText + '\'' +
                '}';
    }
}
