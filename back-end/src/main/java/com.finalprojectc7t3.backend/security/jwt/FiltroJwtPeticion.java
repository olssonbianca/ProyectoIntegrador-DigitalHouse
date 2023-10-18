package com.finalprojectc7t3.backend.security.jwt;

import com.finalprojectc7t3.backend.security.token.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FiltroJwtPeticion extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UtilidadJwt utilidadJwt;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String usuario= null;
        String jwt = null;

        if(authorizationHeader != null &&  authorizationHeader.startsWith("Bearer")) {
            jwt = authorizationHeader.substring(7);
            usuario = utilidadJwt.extractUser(jwt);
        }

        if(usuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails  = this.userDetailsService.loadUserByUsername(usuario);
            var isValidToken = tokenRepository.findByToken(jwt)
                    .map(t -> !t.getExpired() && !t.getRevoked())
                    .orElse(false);
            if(utilidadJwt.validateToken(jwt, userDetails) && isValidToken) {
                UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken  = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
