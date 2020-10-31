package ru.otus.project.authorization_server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import ru.otus.project.authorization_server.service.token.CustomTokenEnhancer;

import javax.sql.DataSource;
import java.util.List;

@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authManager;
    private final TokenStore tokenStore;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final DataSource dataSource;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

        tokenEnhancerChain.setTokenEnhancers(List.of(new CustomTokenEnhancer(), jwtAccessTokenConverter));

        endpoints
            .authenticationManager(authManager)
            .tokenEnhancer(tokenEnhancerChain)
            .tokenStore(tokenStore)
            .reuseRefreshTokens(false)
        ;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }
}
