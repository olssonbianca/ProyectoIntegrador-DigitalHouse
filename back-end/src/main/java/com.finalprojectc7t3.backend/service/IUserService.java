package com.finalprojectc7t3.backend.service;

import com.finalprojectc7t3.backend.dto.UserAppDTO;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import java.util.List;

public interface IUserService {
    UserAppDTO create(UserAppDTO userAppDTO) throws DontFindException;
    UserAppDTO update(UserAppDTO userAppDTO) throws DontFindException;
    List<UserAppDTO> findAll();
    UserAppDTO findById(Integer idUser) throws DontFindException;
    void delete(int id);

    UserAppDTO findByUserName(String userName);
}
