package com.finalprojectc7t3.backend.dto;


import com.finalprojectc7t3.backend.entity.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserAppSimpleDTO {
    private int idUser;
    private String userName;
    private Rol rol;
    private String name;
    private String lastName;
    private String email;
}