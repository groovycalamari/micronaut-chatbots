package io.micronaut.bots.googlechat.core;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.bots.core.ChatBot;
import io.micronaut.bots.core.ChatBotMessageParser;
import io.micronaut.bots.core.ChatBotMessageReceive;
import io.micronaut.bots.core.ChatBotSpace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.Serializable;
import java.util.Optional;

@Singleton
public class GoogleChatMessageParser implements ChatBotMessageParser {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleChatMessageParser.class);

    @Override
    public Optional<ChatBotSpace> parseChat(@NonNull ChatBotMessageReceive messageReceive) {
        if (!(messageReceive instanceof Event)) {
            LOG.warn("Message not of type Event");
            return Optional.empty();
        }
        Event event = (Event) messageReceive;
        return Optional.of(event.getSpace());
    }

    @Override
    public Optional<String> parseText(@NonNull ChatBotMessageReceive messageReceive) {
        if (!(messageReceive instanceof Event)) {
            LOG.warn("Message not of type Event");
            return Optional.empty();
        }
        Event event = (Event) messageReceive;
        if (event.getMessage() != null) {
            return Optional.ofNullable(event.getMessage().getText());
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> parseTextWithoutBotName(@NonNull ChatBot bot, @NonNull ChatBotMessageReceive messageReceive) {
        if (!(messageReceive instanceof Event)) {
            LOG.warn("Message not of type Event");
            return Optional.empty();
        }
        Event event = (Event) messageReceive;

        Optional<String> textOptional = parseText(event);
        if (textOptional.isPresent()) {
            String text = textOptional.get();
            return Optional.of(text.replaceAll(bot.getAtUsername(), "").trim());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Serializable> parseUserId(ChatBotMessageReceive messageReceive) {
        if (!(messageReceive instanceof Event)) {
            LOG.warn("Message not of type Event");
            return Optional.empty();
        }
        Event event = (Event) messageReceive;
        if (event.getMessage() != null) {
            return Optional.ofNullable(event.getMessage().getSender().getName());
        }
        return Optional.empty();
    }
}
