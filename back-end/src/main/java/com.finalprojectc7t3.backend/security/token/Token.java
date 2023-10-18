package com.finalprojectc7t3.backend.security.token;


import com.finalprojectc7t3.backend.entity.UserApp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    private Boolean expired;
    private Boolean revoked;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserApp user;
}