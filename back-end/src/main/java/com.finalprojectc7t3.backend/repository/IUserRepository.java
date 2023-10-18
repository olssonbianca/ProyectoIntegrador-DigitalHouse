package com.finalprojectc7t3.backend.repository;

import com.finalprojectc7t3.backend.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
 public interface IUserRepository extends JpaRepository<UserApp, Integer> {

    UserApp findByUserName(String user);
    UserApp findByPerson_Mail(String email);
}
