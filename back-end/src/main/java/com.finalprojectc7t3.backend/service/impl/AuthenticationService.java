package com.finalprojectc7t3.backend.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.*;
import com.finalprojectc7t3.backend.entity.UserApp;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.repository.IRolRepository;
import com.finalprojectc7t3.backend.repository.IUserRepository;
import com.finalprojectc7t3.backend.security.jwt.UtilidadJwt;
import com.finalprojectc7t3.backend.security.token.Token;
import com.finalprojectc7t3.backend.security.token.TokenRepository;
import com.finalprojectc7t3.backend.service.IPersonService;
import com.finalprojectc7t3.backend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IUserRepository repository;
    private final IPersonService personService;
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UtilidadJwt jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final IRolRepository rolRepository;

    private final ObjectMapper mapper;

    public UserRespuestaDTO register(RegisterRequest request) throws DontFindException {

        PersonDTO person = PersonDTO.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .mail(request.getEmail())
                .build();
        person = personService.create(person);


        UserAppDTO user = UserAppDTO.builder()
                .idPerson(person.getIdPerson())
                .userName(person.getMail().split("@")[0])
                .password(passwordEncoder.encode(request.getPassword()))
                .idRol(request.getRol() == null ? rolRepository.findByCode("CLIENT").getIdRol() : rolRepository.findByCode(request.getRol()).getIdRol())
                .isEnabled(true)
                .build();

        return mapper.convertValue(userService.create(user), UserRespuestaDTO.class);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        }catch (BadCredentialsException e) {
            throw new Exception("Error al autenticar", e);
        }


         var user = repository.findByPerson_Mail(request.getUserName());
         var jwtToken = jwtService.generateToken(user);
         revokeUserToken(user);
         saveUserToken(user, jwtToken);

         return AuthenticationResponse.builder()
                .token(jwtToken)
                 .user(mapper.convertValue(user, UserAppSimpleDTO.class))
                .build();
    }

    private void saveUserToken(UserApp user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeUserToken(UserApp user){
        var validUserToken = tokenRepository.findAllValidTokensByUser(user.getIdUser());
        if(validUserToken.isEmpty()){
            return;
        }
        validUserToken.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });

        tokenRepository.saveAll(validUserToken);
    }
}
