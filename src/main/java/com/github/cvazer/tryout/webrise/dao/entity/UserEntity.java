package com.github.cvazer.tryout.webrise.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
    private String displayName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "user")
    private Set<SubscriptionEntity> subscriptions;

    public UserEntity(String displayName) {
        this.displayName = displayName;
    }
}
