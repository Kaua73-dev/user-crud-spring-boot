package com.zezoeiro.demo.infrastructure.repository;

import com.zezoeiro.demo.infrastructure.entitys.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByCpf(String cpf);

    @Transactional
    void deleteByCpf(String cpf);
}
