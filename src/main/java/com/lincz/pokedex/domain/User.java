package com.lincz.pokedex.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class User extends BaseEntity {

    @Builder
    public User(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String username,
                String email, String password, UserRole role, UserStatus status) {
        super(id, version, createdDate, lastModifiedDate);
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private UserRole role = UserRole.USER;

    private UserStatus status = UserStatus.ACTIVE;
}
