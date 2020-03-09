package io.micronaut.bots.googlechat.security;

import edu.umd.cs.findbugs.annotations.NonNull;

import javax.validation.constraints.NotBlank;

public interface GoogleChatBearerTokenVerifier {
    void verify(@NonNull @NotBlank String bearerToken) throws UnauthorizedGoogleChatToken;
}
