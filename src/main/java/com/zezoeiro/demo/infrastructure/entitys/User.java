package com.zezoeiro.demo.infrastructure.entitys;


import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;

}
