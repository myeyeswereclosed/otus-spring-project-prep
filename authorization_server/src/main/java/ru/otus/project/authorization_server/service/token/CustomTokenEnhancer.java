package ru.otus.project.authorization_server.service.token;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import ru.otus.project.authorization_server.model.CustomUser;
import ru.otus.project.authorization_server.model.RehearsalBaseUser;

import java.util.Map;

@SuppressWarnings("deprecation")
public class CustomTokenEnhancer implements TokenEnhancer {
    public static final String NAME_KEY = "name";
    public static final String PHONE_KEY = "phone";
    public static final String EMAIL_KEY = "email";

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        var user = (RehearsalBaseUser) authentication.getPrincipal();

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(
            Map.of(
                NAME_KEY, user.getUsername(),
                PHONE_KEY, user.getPhone(),
                EMAIL_KEY, user.getEmail()
            )
        );

        return accessToken;
    }
}
