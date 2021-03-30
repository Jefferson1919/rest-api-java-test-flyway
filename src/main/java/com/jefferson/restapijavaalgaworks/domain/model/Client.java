package com.jefferson.restapijavaalgaworks.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @Column
    @Size(max = 20)
    private String phone;

    public Client(@NotBlank @Size(max = 60) String name, @NotBlank @Email @Size(max = 255) String email, @Size(max = 20) String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
