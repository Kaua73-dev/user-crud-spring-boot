package com.zezoeiro.demo.controller;


import com.zezoeiro.demo.business.UserService;
import com.zezoeiro.demo.infrastructure.entitys.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<Void> createUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<User> getUserByCpf(@PathVariable String cpf){
        return  ResponseEntity.ok(userService.getUserByCpf(cpf));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteUserByCpf(@PathVariable String cpf){
        userService.deleteUserByCpf(cpf);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Void> updateUserByCpf(@PathVariable String cpf, @RequestBody User user){
        userService.updateUserByCpf(cpf, user);
        return ResponseEntity.ok().build();
    }



}
