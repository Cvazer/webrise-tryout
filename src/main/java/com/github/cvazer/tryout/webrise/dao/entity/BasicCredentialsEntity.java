package com.github.cvazer.tryout.webrise.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "basic_credentials")
@NoArgsConstructor
@AllArgsConstructor
public class BasicCredentialsEntity {
    private String username;
    private String password;

    @Id
    private long userId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

}
