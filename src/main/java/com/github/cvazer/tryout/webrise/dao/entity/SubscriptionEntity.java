package com.github.cvazer.tryout.webrise.dao.entity;

import com.github.cvazer.tryout.webrise.service.subscriptions.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscriptions")
public class SubscriptionEntity {
    private LocalDateTime renewDueDate;

    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private SubscriptionStatus status;

    @EmbeddedId
    private Identity id;

    @MapsId("serviceId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id",  referencedColumnName = "id")
    private ServiceEntity service;

    @MapsId("userId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Embeddable
    public record Identity (

        @Column(name = "user_id", insertable = false, updatable = false)
        Long userId,

        @Column(name = "service_id", insertable = false, updatable = false)
        String serviceId

    ) implements Serializable {}

}
