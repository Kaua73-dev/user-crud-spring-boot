package com.zezoeiro.demo.business;


import com.zezoeiro.demo.infrastructure.entitys.User;
import com.zezoeiro.demo.infrastructure.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void createUser(User user){

        String hashedPassword = BCrypt.hashpw(
          user.getPassword(),
                BCrypt.gensalt(12)
        );

        user.setPassword(hashedPassword);
        repository.saveAndFlush(user);
    }



    // get cpf
    public User getUserByCpf(String cpf){
        return repository.findByCpf(cpf).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User not found"
        ));
    }

    // get all user
    public List<User> getAllUsers(){
        return repository.findAll();
    }


    public void deleteUserByCpf(String cpf){
        repository.deleteByCpf(cpf);
    }


    public void updateUserByCpf(String cpf, User user){
        User userEntity = getUserByCpf(cpf);

        String passwordFinal = userEntity.getPassword();

        if(user.getPassword() != null && !user.getPassword().isBlank()){
            passwordFinal = BCrypt.hashpw(
              user.getPassword(),
                    BCrypt.gensalt(12)
            );
        }


        User userUpdated = User.builder()
                .cpf(cpf)
                .name(user.getName() != null ? user.getName() : userEntity.getName())
                .password(passwordFinal)
                .id(userEntity.getId())
                .build();

                repository.saveAndFlush(userUpdated);
    }


}
























