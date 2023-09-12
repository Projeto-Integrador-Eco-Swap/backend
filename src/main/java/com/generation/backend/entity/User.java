package com.generation.backend.entity;

import com.generation.backend.annotation.Phone;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "users")
@Table(name = "tb_Users",
        schema = "db_ecoswap",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "email",
                        name = "unique_email"
                )
        })
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "users_sequence"
    )
    @Column(name = "id",
            nullable = false,
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
    private String email;

    @Column(name = "password",
            nullable = false,
            columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "phone",
            columnDefinition = "VARCHAR(255)")
    @Phone
    private String phone;

    @Column(name = "birth_date",
            nullable = false,
            columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(name = "picture",
            columnDefinition = "VARCHAR(5000)")
    private String picture;
}