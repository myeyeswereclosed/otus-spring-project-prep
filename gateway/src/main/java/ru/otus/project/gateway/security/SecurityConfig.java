package ru.otus.project.gateway.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/libs/**", "/webjars/**", "/css/**", "/js/**", "/img/**");
    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf()
//            .disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//            .and()
//            .authorizeRequests()
////            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//            .antMatchers("/admin/**").hasRole("ADMIN")
//            .antMatchers("/favicon.ico").anonymous()
//            .antMatchers("/", "/login", "/clientLogin**", "/register", "/rehearsals", "/error").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .loginPage("/login")
//        ;
//    }
}
