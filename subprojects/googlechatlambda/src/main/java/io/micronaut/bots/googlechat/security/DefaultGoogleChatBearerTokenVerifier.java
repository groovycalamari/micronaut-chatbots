package io.micronaut.bots.googlechat.security;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.bots.googlechat.core.GoogleChatBot;
import io.micronaut.http.HttpHeaderValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class DefaultGoogleChatBearerTokenVerifier implements GoogleChatBearerTokenVerifier {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultGoogleChatBearerTokenVerifier.class);
    static String CHAT_ISSUER = "chat@system.gserviceaccount.com";
    protected final Collection<GoogleChatBot> googleChatBots;

    public DefaultGoogleChatBearerTokenVerifier(Collection<GoogleChatBot> googleChatBots) {
        this.googleChatBots = googleChatBots;
    }

    @Override
    public void verify(@NonNull @NotBlank String token) throws UnauthorizedGoogleChatToken {
        String bearerToken = token;

        try {
            String prefix = HttpHeaderValues.AUTHORIZATION_PREFIX_BEARER + " ";
            if (bearerToken.startsWith(prefix)) {
                LOG.info("Removing {} prefix from token", prefix);
                bearerToken = bearerToken.substring(prefix.length());
            }

            JWT jwt = JWTParser.parse(bearerToken);
            JWTClaimsSet jwtClaimsSet = jwt.getJWTClaimsSet();
            String issuer = jwtClaimsSet.getIssuer();
            if (issuer == null) {
                LOG.warn("issuer is null");
                throw new UnauthorizedGoogleChatToken("issuer is null");
            }
            if (!issuer.equals(CHAT_ISSUER)) {
                throw new UnauthorizedGoogleChatToken("issuer does not match" + CHAT_ISSUER);
            }
            List<String> audiencies = jwtClaimsSet.getAudience();
            if (audiencies == null) {
                LOG.warn("audience is null");
                throw new UnauthorizedGoogleChatToken("audiencies is null");
            }
            if (googleChatBots.stream().noneMatch(googleChatBot -> audiencies.contains(googleChatBot.getProjectId()))) {
                throw new UnauthorizedGoogleChatToken("no bot project id " + googleChatBots.stream().map(GoogleChatBot::getProjectId).collect(Collectors.joining(",")) + " matches audiencies " + audiencies.stream().collect(Collectors.joining(",")));
            }
        } catch (ParseException e) {
            LOG.warn("parsing header {} throws ParseException {}", bearerToken, e.getMessage());
            throw new UnauthorizedGoogleChatToken("audiencies is null");
        }
    }
}
