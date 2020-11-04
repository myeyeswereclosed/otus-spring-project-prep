package ru.otus.project.gateway.service.user;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import ru.otus.project.gateway.config.AuthorizationConfig;
import ru.otus.project.gateway.model.security.AccessToken;
import ru.otus.project.gateway.model.security.User;
import ru.otus.project.gateway.service.rest_client.AuthorizationServerRegistrationClient;
import ru.otus.project.gateway.service.rest_client.AuthorizationServerTokenClient;

import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

@Service
@RequiredArgsConstructor
public class UserAuthorizationService implements UserService {
    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String PHONE_KEY = "phone";
    public static final String EMAIL_KEY = "email";
    public static final String ROLE_KEY = "role";

    private static final Logger logger = LoggerFactory.getLogger(UserAuthorizationService.class);

    private final TokenStore tokenStore;
    private final AuthorizationServerTokenClient tokenClient;
    private final AuthorizationServerRegistrationClient registrationClient;
    private final AuthorizationConfig config;

    public Optional<AccessToken> accessToken(String username, String password) throws InvalidCredentials {
        logger.info("Requesting access token for {}", username);

        try {
            return Optional.ofNullable(tokenClient.accessToken("password", username, password, config.getClientId()));
        } catch (HystrixRuntimeException clientError) {
            if (isBadRequest(clientError)) {
                logger.warn("Seems to be invalid password passed for {}", username);

                throw new InvalidCredentials();
            }

            throw clientError;
        }
    }

    // TODO abstraction leak
    private boolean isBadRequest(HystrixRuntimeException error) {
        try {
            return ((FeignException)error.getCause()).status() == HttpStatus.BAD_REQUEST.value();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<User> register(User user) {
        try {
            return Optional.ofNullable(registrationClient.register(user).getBody());
        } catch (Exception e) {
            logger.error("Exception with message: {} occurred. Trace:\r\n: {}", e.getMessage(), getStackTrace(e));
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> authorizedUser(Authentication authentication) {
        return authorizationInfo(authentication).flatMap(this::makeUserFromInfo);
    }

    private Optional<Map<String, Object>> authorizationInfo(Authentication authentication) {
        try {
            var details = (OAuth2AuthenticationDetails) authentication.getDetails();
            var accessToken = tokenStore.readAccessToken(details.getTokenValue());

            logger.info("{} access token is valid", authentication.getPrincipal());

            return Optional.of(accessToken.getAdditionalInformation());
        } catch (Exception e) {
            logger.error("Exception {} occurred. Trace:\r\n {}", e.getMessage(), getStackTrace(e));
        }

        logger.warn("Token information is invalid");

        return Optional.empty();
    }

    private Optional<User> makeUserFromInfo(Map<String, Object> info) {
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
            logger.error("Exception {} occurred. Trace:\r\n{}", e.getMessage(), getStackTrace(e));
        }

        return Optional.empty();
    }
}
