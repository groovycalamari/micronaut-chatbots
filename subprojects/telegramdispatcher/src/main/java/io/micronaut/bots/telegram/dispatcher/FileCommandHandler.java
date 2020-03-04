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
package io.micronaut.bots.telegram.dispatcher;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.bots.telegram.core.Send;
import io.micronaut.bots.telegram.core.SendMessage;
import io.micronaut.bots.telegram.core.Update;
import io.micronaut.bots.telegram.httpclient.TelegramBot;
import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import io.micronaut.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;

public class FileCommandHandler implements CommandHandler {
    private static final Logger LOG = LoggerFactory.getLogger(FileCommandHandler.class);

    protected final ParseMode parseMode;
    protected final String path;

    public FileCommandHandler(ParseMode parseMode, String path) {
        this.parseMode = parseMode;
        this.path = path;
    }

    @Override
    public <T extends Send> Optional<T> handle(@NonNull TelegramBot telegramBot, @NonNull Update update) {
        Optional<String> textOpt = replyUpdate(telegramBot, update);
        if (!textOpt.isPresent()) {
            return Optional.empty();
        }
        Integer chatId = CommandHandler.parseChatId(update);
        if (chatId == null) {
            return Optional.empty();
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode(parseMode.toString());
        sendMessage.setText(textOpt.get());
        return (Optional) Optional.of(sendMessage);
    }

    protected Optional<String> replyUpdate(@NonNull TelegramBot telegramBot, @NonNull Update update) {
        ClassPathResourceLoader loader = new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
        Optional<URL> resource = loader.getResource(path);
        if (resource.isPresent()) {
            URL u = resource.get();
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(u.openStream()));
            } catch (IOException e) {
                LOG.error("IOException {}", e.getMessage());
                return Optional.empty();
            }

            String text = "";
            String inputLine;
            try {
                while ((inputLine = in.readLine()) != null) {
                    text += inputLine;
                    text += "\n";
                }
            } catch (IOException e) {
                LOG.error("IOException {}", e.getMessage());
                return Optional.empty();
            }

            try {
                in.close();
            } catch (IOException e) {
                LOG.error("IOException {}", e.getMessage());
                return Optional.empty();
            }
            if (StringUtils.isEmpty(text)) {
                return Optional.empty();
            }
            return Optional.of(text);
        } else {
            LOG.warn("resource not found");
        }
        return Optional.empty();
    }
}
