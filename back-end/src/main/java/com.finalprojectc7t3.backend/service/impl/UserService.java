package com.finalprojectc7t3.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalprojectc7t3.backend.dto.UserAppDTO;
import com.finalprojectc7t3.backend.entity.UserApp;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.repository.IUserRepository;
import com.finalprojectc7t3.backend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService, UserDetailsService {

    private final IUserRepository repository;
    private final ObjectMapper mapper;

    @Override
    public UserAppDTO create(UserAppDTO userAppDTO) throws DontFindException {
        Optional<UserAppDTO> uDto = Optional.ofNullable(findByUserName(userAppDTO.getUserName()));
        uDto.ifPresent(u -> userAppDTO.setIdUser(u.getIdUser()));
        return mapper.convertValue(repository.save(mapper.convertValue(userAppDTO, UserApp.class)), UserAppDTO.class);
    }

    @Override
    public UserAppDTO update(UserAppDTO userAppDTO) throws DontFindException {
        return create(userAppDTO);
    }

    @Override
    public List<UserAppDTO> findAll() {
        return repository.findAll().stream().map(u -> mapper.convertValue(u, UserAppDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserAppDTO findById(Integer idUser) throws DontFindException {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public UserAppDTO findByUserName(String userName) {
        return mapper.convertValue(repository.findByUserName(userName), UserAppDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp usuario = repository.findByPerson_Mail(username);
        User userDetail = null;
        if(usuario != null){
            userDetail =
                    new User(
                            usuario.getUsername(),
                            usuario.getPassword(),
                            usuario.isEnabled(),
                            true,
                            true,
                            true,
                            usuario.getAuthorities());
        }
        return userDetail;
    }
}
