package io.micronaut.bots.googlechat.core;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.bots.core.ChatBotMessageReceive;
import io.micronaut.bots.core.ChatBotMessageSend;
import io.micronaut.bots.core.MessageComposer;
import io.micronaut.bots.core.ParseMode;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Singleton
public class GoogleChatMessageComposer implements MessageComposer {

    @Override
    public <T extends ChatBotMessageSend> Optional<T> compose(@NonNull @NotBlank String text, @NonNull @Valid @NotNull ChatBotMessageReceive messageReceive) {

        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setText(text);
        return (Optional) Optional.of(simpleMessage);
    }

    @Override
    public <T extends ChatBotMessageSend> Optional<T> compose(@NonNull @NotBlank String text, @NonNull ParseMode parseMode, @NonNull @Valid @NotNull ChatBotMessageReceive messageReceive) {
        return compose(text, messageReceive);
    }
}
