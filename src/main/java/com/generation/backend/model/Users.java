package com.generation.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "users")
@Table(name = "tb_Users",
        schema = "db_ecoswap",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email", name = "unique_email")
        })
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false,
            unique = true,
            columnDefinition = "BIGINT UNSIGNED")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name",
            nullable = false,
            columnDefinition = "VARCHAR(255)")
    private String name;

    @Email
    @Column(name = "email",
            nullable = false,
            unique = true,
            columnDefinition = "VARCHAR(255)")
    private String user;

    @Column(name = "password",
            nullable = false,
            columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "picture",
            nullable = false,
            columnDefinition = "VARCHAR(5000)")
    private String picture;
}