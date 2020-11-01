package ru.otus.project.authorization_server.service.token;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import ru.otus.project.authorization_server.domain.RehearsalBaseUser;

import java.util.Map;

@SuppressWarnings("deprecation")
public class CustomTokenEnhancer implements TokenEnhancer {
    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String PHONE_KEY = "phone";
    public static final String EMAIL_KEY = "email";
    public static final String ROLE_KEY = "role";

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        var user = (RehearsalBaseUser) authentication.getPrincipal();

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(
            Map.of(
                ID_KEY, user.getId(),
                NAME_KEY, user.getUsername(),
                PHONE_KEY, user.getPhone(),
                EMAIL_KEY, user.getEmail(),
                ROLE_KEY,
                user.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse("")
            )
        );

        return accessToken;
    }
}
