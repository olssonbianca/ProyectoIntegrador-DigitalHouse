package com.finalprojectc7t3.backend.security.configuracion;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class EncriptarContrasenia {
    @Bean
    public PasswordEncoder codificar() {
        return new BCryptPasswordEncoder();
    }
}
