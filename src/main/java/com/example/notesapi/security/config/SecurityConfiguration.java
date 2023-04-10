package com.example.notesapi.security.config;

import com.example.notesapi.security.JwtAuthenticationEntryPoint;
import com.example.notesapi.security.TokenManagementService;
import com.example.notesapi.security.UserPrincipalDetailsService;
import com.example.notesapi.security.filter.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)

public class SecurityConfiguration
        extends WebSecurityConfigurerAdapter{

    private final TokenManagementService tokenManagementService;
    private final UserPrincipalDetailsService userPrincipalDetailsService;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserPrincipalDetailsService();
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private static final String[] AUTH_WHITELIST = {
            "/login",
            "/notes",
            "/users",
            "/users/**",
            "/notes/",
//            "/users",
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
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                cors().
                and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/notes/**/like").authenticated()
                .anyRequest().permitAll();
        http
                .addFilterBefore(new JwtAuthorizationFilter(tokenManagementService), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui", "/swagger-ui/index.html#/",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        System.out.println("FILTERRRRRRR");
//
//        http.cors().and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
////                .csrf().disable()
////                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
////                .and()
////                .authorizeHttpRequests().anyRequest().permitAll()
//                .authorizeHttpRequests().requestMatchers(AUTH_WHITELIST).permitAll();
////                .and()
////                .authorizeHttpRequests().anyRequest().authenticated();
////        http
////                .addFilterBefore(new JwtAuthorizationFilter(tokenManagementService), UsernamePasswordAuthenticationFilter.class);
////             http.headers().frameOptions().sameOrigin();
////        http.authenticationProvider(authenticationProvider());
//
//        return http.build();
//    }


}
