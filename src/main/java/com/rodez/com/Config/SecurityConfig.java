package com.rodez.com.Config;

import com.rodez.com.Service.JwtService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Component
    public class JwtAuthFilter extends OncePerRequestFilter{

        @Autowired
        JwtService jwtService;

        @Override
        protected void doFilterInternal(
                HttpServletRequest request,
                HttpServletResponse response,
                FilterChain filterChain
        ) throws ServletException, IOException{

            final String bearerToken = request.getHeader("Authorization");
            if (StringUtils.isBlank(bearerToken) || !bearerToken.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }
            final String token = bearerToken.substring(7);
            if(StringUtils.isBlank(token)){
                filterChain.doFilter(request,response);
                return;
            }
            final String username = jwtService.getUsernameFromToken(token);
            if(StringUtils.isBlank(username)){
                filterChain.doFilter(request,response);
                return;
            }

        }
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.withUsername("rodez")
//                        .password(passwordEncoder().encode("demo"))
//                        .roles("USER")
//                        .build()
//        );
//
//    }
}
