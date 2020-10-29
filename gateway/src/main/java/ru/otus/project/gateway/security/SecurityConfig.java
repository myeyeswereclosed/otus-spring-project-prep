package ru.otus.project.gateway.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/webjars/**", "/css/**", "/libs/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            .and()
            .authorizeRequests()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/anonymous*", "/favicon.ico").anonymous()
            .antMatchers("/", "/login**", "/clientLogin**", "/register").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
