package com.example.xphr.configuration;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Configuration.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfiguration {

    private static final String[] WHITE_LIST = {
            "/actuator",
            "/swagger-ui/**",
            "/v3/api-docs/**"};

    /**
     * Cors Configurer.
     *
     * @return WebMvcConfigurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }

    /**
     * Security Filter Chain.
     *
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(headerAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(manager ->
                        manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    /**
     * User Details Service.
     *
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("ronaldo")
                        .password(getPasswordEncoder().encode("password"))
                        .roles("EMPLOYEE")
                        .build(),
                User.withUsername("messi")
                        .password(getPasswordEncoder().encode("password"))
                        .roles("EMPLOYEE")
                        .build(),
                User.withUsername("neymar")
                        .password(getPasswordEncoder().encode("password"))
                        .roles("EMPLOYEE")
                        .build(),
                User.withUsername("admin")
                        .password(getPasswordEncoder().encode("password"))
                        .roles("ADMIN")
                        .build()
        );
    }

    @Bean
    public HeaderAuthFilter headerAuthFilter() {
        return new HeaderAuthFilter(userDetailsService());
    }

    /**
     * Authentication Manager.
     *
     * @param config AuthenticationConfiguration
     * @return AuthenticationManager
     * @throws Exception Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Password Encoder.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
