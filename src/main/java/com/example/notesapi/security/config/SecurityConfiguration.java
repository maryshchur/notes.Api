package com.example.notesapi.security.config;

import com.example.notesapi.security.JwtAuthenticationEntryPoint;
import com.example.notesapi.security.TokenManagementService;
import com.example.notesapi.security.filter.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    private final TokenManagementService tokenManagementService;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserPrincipalDetailsService();
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }

    private static final String[] AUTH_WHITELIST = {
            "/login",
            "/notes",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/resources/**",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
           "/swagger-ui/index.html#/",
            "/swagger-ui.html",
            "/webjars/**"
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
//                .authorizeHttpRequests().anyRequest().permitAll()
                .authorizeHttpRequests().requestMatchers(AUTH_WHITELIST).permitAll();
//                .and()
//                .authorizeHttpRequests().anyRequest().authenticated();
//        http
//                .addFilterBefore(new JwtAuthorizationFilter(tokenManagementService), UsernamePasswordAuthenticationFilter.class);
//
//        http.authorizeRequests().antMatchers("/login").permitAll()
//
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .loginPage("/login")
//                .usernameParameter("email")
//                .permitAll()
//                .and()
//                .rememberMe().key("AbcdEfghIjklmNopQrsTuvXyz_0123456789")
//                .and()
//                .logout().permitAll();
//        http
//                .addFilterBefore(new JwtAuthorizationFilter(tokenManagementService), UsernamePasswordAuthenticationFilter.class);
//        http.headers().frameOptions().sameOrigin();
//        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

//    ignore Mvc [pattern='/v2/api-docs'].
//    This is not recommended -- please use permitAll via HttpSecurity#authorizeHttpRequests instead.

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web
////                .ignoring()
//                .authorizeHttpRequests(
//                requests -> requests
//                        .requestMatchers(AUTH_WHITELIST).permitAll()
//        )
//
//                .requestMatchers("/v2/api-docs",
//                "/resources/**",
//                "/configuration/ui",
//                "/swagger-resources/**",
//                "/configuration/security",
//                "/swagger-ui.html",
//                "/webjars/**"
//        );
//    }

}
