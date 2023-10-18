package com.finalprojectc7t3.backend.entity;


import javax.persistence.*;

import com.finalprojectc7t3.backend.security.token.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")

public class UserApp implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idUser")
    private Integer idUser;
    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "idPerson")
    private Integer idPerson;

    @Column(name = "isEnabled")
    private boolean isEnabled;

    @Column(name = "idRol")
    private Integer idRol;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Transient
    private String name;
    @Transient
    private String lastName;
    @Transient
    private String email;


    @OneToOne
    @JoinColumn(name = "idPerson", insertable = false, updatable = false)
    private Person person;

    @OneToOne
    @JoinColumn(name = "idRol", referencedColumnName = "idRol",insertable = false, updatable = false)
    private Rol rol;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (rol != null) {
            return Collections.singletonList(new SimpleGrantedAuthority(rol.getCode()));
        }
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getName() {
        return person != null ? person.getName() : "";
    }

    public String getLastName() {
        return person != null ? person.getLastName() : "";
    }

    public String getEmail() {
        return person != null ? person.getMail() : "";
    }
}
