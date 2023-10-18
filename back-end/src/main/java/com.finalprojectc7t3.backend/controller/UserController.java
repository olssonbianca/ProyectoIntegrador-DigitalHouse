package com.finalprojectc7t3.backend.controller;


import com.finalprojectc7t3.backend.dto.RegisterRequest;
import com.finalprojectc7t3.backend.excepciones.DontFindException;
import com.finalprojectc7t3.backend.service.IUserService;
import com.finalprojectc7t3.backend.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private  final IUserService userService;
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) throws DontFindException {
        return ResponseEntity.ok(service.register(request));
    }


    @GetMapping
    public ResponseEntity<?> buscarTodos(){
        return ResponseEntity.ok(userService.findAll());
    }

}