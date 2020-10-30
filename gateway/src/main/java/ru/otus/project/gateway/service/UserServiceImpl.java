package ru.otus.project.gateway.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import ru.otus.project.gateway.dto.security.User;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String PHONE_KEY = "phone";
    public static final String EMAIL_KEY = "email";
    public static final String ROLE_KEY = "role";

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final TokenStore tokenStore;

    @Override
    public Optional<User> authenticatedUser(Authentication authentication) {
        return authenticationInfo(authentication).flatMap(this::parseInfo);
    }

    private Optional<Map<String, Object>> authenticationInfo(Authentication authentication) {
        try {
            var details = (OAuth2AuthenticationDetails) authentication.getDetails();
            var accessToken = tokenStore.readAccessToken(details.getTokenValue());

            return Optional.of(accessToken.getAdditionalInformation());
        } catch (Exception e) {
            logger.error("Exception {} occurred. Trace:\r\n{}", e.getMessage(), ExceptionUtils.getStackTrace(e));
        }

        return Optional.empty();
    }

    private Optional<User> parseInfo(Map<String, Object> info) {
        try {
            return
                Optional.of(
                    new User(
                        Long.parseLong(String.valueOf(info.get(ID_KEY))),
                        (String) info.get(NAME_KEY),
                        (String) info.get(PHONE_KEY),
                        (String) info.get(EMAIL_KEY),
                        "",
                        (String) info.get(ROLE_KEY)
                    )
                );
        } catch (Exception e) {
            logger.error("Exception {} occurred. Trace:\r\n{}", e.getMessage(), ExceptionUtils.getStackTrace(e));
        }

        return Optional.empty();
    }
}
