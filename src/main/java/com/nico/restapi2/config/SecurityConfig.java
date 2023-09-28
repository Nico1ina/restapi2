package com.nico.restapi2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((authorize) -> {
            authorize
                    .requestMatchers("/api/v4/delete/{id}").hasRole("ADMIN")
                    .requestMatchers("/api/v4/customers").hasRole("ADMIN")
                    .requestMatchers("/api/v4/register").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/updateInfo/{id}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/api/v4/{id}").permitAll()
                    .requestMatchers("/api/v4/updateCustomer/{id}").permitAll()
                    .requestMatchers("/api/v4/deleteBooking/{id}").hasRole("ADMIN")
                    .requestMatchers("/api/v4/bookings/{id}").hasRole("ADMIN")
                    .requestMatchers("/api/v4/myBookings/{id}").hasRole("USER")
                    .requestMatchers("/api/v4/bookings").permitAll()
                    .requestMatchers("/api/v4/availability").hasAnyRole("ADMIN", "USER")
                    .anyRequest()
                    .authenticated();
        }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
    UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();

    UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("AdminPassword"))
            .roles("ADMIN")
            .build();
    return new InMemoryUserDetailsManager(user, admin);
    }
}