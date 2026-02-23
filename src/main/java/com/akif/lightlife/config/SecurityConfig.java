package com.akif.lightlife.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/index.html",
                    "/user-dashboard.html",
                    "/dietitian-dashboard.html",
                    "/css/**", "/js/**", "/images/**",
                    "/api/v1/kullanicilar/kayit",
                    "/api/v1/kullanicilar/giris",
                    "/api/v1/diyetisyen/kayit",
                    "/api/v1/diyetisyen/giris"
                ).permitAll()
                .anyRequest().permitAll()
            )
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
