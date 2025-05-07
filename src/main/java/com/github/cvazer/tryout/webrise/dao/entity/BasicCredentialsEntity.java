package com.github.cvazer.tryout.webrise.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@Table(name = "basic_credentials")
@NoArgsConstructor
@AllArgsConstructor
public class BasicCredentialsEntity {
    private String username;
    private String password;

    @Id
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @MapsId
    @ManyToOne(cascade = {PERSIST, REFRESH, MERGE}, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

}
