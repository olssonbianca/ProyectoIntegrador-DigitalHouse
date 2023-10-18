package com.finalprojectc7t3.backend.security.configuracion;

import com.finalprojectc7t3.backend.security.jwt.FiltroJwtPeticion;
import com.finalprojectc7t3.backend.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

    private final UserService usuarioServicio;

    private final FiltroJwtPeticion filtroJwtPeticion;

    private final PasswordEncoder codificar;
    private final LogoutHandler logoutHandler;


    @Autowired
    public ConfiguracionSeguridad(UserService usuarioServicio, FiltroJwtPeticion filtroJwtPeticion, PasswordEncoder codificar, LogoutHandler logoutHandler) {
        this.usuarioServicio = usuarioServicio;
        this.filtroJwtPeticion = filtroJwtPeticion;
        this.codificar = codificar;
        this.logoutHandler = logoutHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioServicio);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers(UrlSecurity.OPEN.urls).permitAll()
//                .antMatchers(UrlSecurity.ADMIN.urls).hasAuthority("ADMIN")
                .and().headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(filtroJwtPeticion, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> SecurityContextHolder.clearContext());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(codificar);
        provider.setUserDetailsService(usuarioServicio);
        return provider;
    }
}
