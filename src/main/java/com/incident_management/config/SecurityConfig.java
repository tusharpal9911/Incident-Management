
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//package com.incident_management.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import static org.springframework.security.config.Customizer.withDefaults;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf
//                .ignoringRequestMatchers("/api/user/open/add", "/api/user/open/login") // Ignore CSRF for specific endpoints
//            )
//            .authorizeHttpRequests(authorizeRequests ->
//                authorizeRequests
//                    .requestMatchers("/api/user/open/login", "/api/user/open/add").permitAll() // Allow open access for specific endpoints
//                    .requestMatchers("/api/user/secured/**").authenticated() // Require authentication for secured endpoints
//                    .anyRequest().denyAll() // Deny all other requests
//            )
//            .formLogin(withDefaults())
//            .httpBasic(withDefaults());
//
//        return http.build();
//    }
//}


