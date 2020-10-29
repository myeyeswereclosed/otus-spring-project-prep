package ru.otus.project.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.gateway.dto.RehearsalBaseUserDto;

import java.util.Map;

@SuppressWarnings("deprecation")
@RestController
@RequiredArgsConstructor
public class SecurityController {
    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String PHONE_KEY = "phone";
    public static final String EMAIL_KEY = "email";
    public static final String ROLE_KEY = "role";

    private final TokenStore tokenStore;

    @GetMapping("/checkAccess")
    // TODO check nulls, exceptions, etc
    public RehearsalBaseUserDto checkAccess(Authentication authentication) {
        Map<String, Object> info = getAdditionalInfo(authentication);

        return
            new RehearsalBaseUserDto(
                Long.parseLong(String.valueOf(info.get(ID_KEY))),
                (String) info.get(NAME_KEY),
                (String) info.get(PHONE_KEY),
                (String) info.get(EMAIL_KEY),
                "",
                (String) info.get(ROLE_KEY)
            );
    }

    private Map<String, Object> getAdditionalInfo(Authentication authentication) {
        var details = (OAuth2AuthenticationDetails) authentication.getDetails();
        var accessToken = tokenStore.readAccessToken(details.getTokenValue());

        return accessToken.getAdditionalInformation();
    }
}
