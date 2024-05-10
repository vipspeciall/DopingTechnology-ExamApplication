package com.vipspeciall.examapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
public class SecurityConfig{


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // CORS yapılandırması
        http.cors(cors -> cors.disable());  // CORS'u devre dışı bırakmak için, veya özelleştirilebilir

        // CSRF yapılandırması
        http.csrf(csrf -> csrf.disable());  // CSRF korumasını devre dışı bırak

        // Yetkilendirme kuralları
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()  // "/api/auth/**" yolu için herkese izin ver
                .anyRequest().authenticated()  // Diğer tüm istekler için kimlik doğrulaması gerekir
        );

        // HTTP Basic yapılandırması
        http.httpBasic(Customizer.withDefaults());  // HTTP Basic ile kimlik doğrulama etkinleştir

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}