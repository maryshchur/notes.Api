package com.example.notesapi.security.filter;

import com.example.notesapi.security.TokenManagementService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final TokenManagementService tokenManagementService;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = tokenManagementService.resolveAccessToken(httpServletRequest);

        if (accessToken != null) {
            try {
                if (tokenManagementService.validateToken(accessToken)) {
                    Authentication authentication =
                            tokenManagementService.getAuthentication(accessToken);
                    LOGGER.info("User successfully authenticate - {}", authentication.getPrincipal());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e) {
                LOGGER.info("Token has expired: " + accessToken);
            } catch (Exception e) {
                LOGGER.info("JWT Authentication failed due to " + e.getMessage());
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


}
