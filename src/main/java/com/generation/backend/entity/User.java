package com.generation.backend.entity;

import com.generation.backend.annotation.Phone;

import com.generation.backend.entity.Enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Table(
        name = "tb_user",
        schema = "db_ecoSwap",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_email",
                        columnNames = "email"
                )
        },
        indexes = {
                @Index(
                        name = "idx_user_email",
                        columnList = "email"
                )
        }
)
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BIGINT UNSIGNED",
            nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(columnDefinition = "VARCHAR(60)",
            nullable = false)
    private String firstName;

    @Column(columnDefinition = "VARCHAR(60)",
            nullable = false)
    private String lastName;

    @Email
    @Schema(example = "email@email.com.br")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "VARCHAR(255)",
            nullable = false)
    private String password;

    @Phone
    @Column(columnDefinition = "VARCHAR(20)")
    private String phone;

    @Column(columnDefinition = "DATE",
            nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(columnDefinition = "VARCHAR(5000)")
    private String picture;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "address_id",
            columnDefinition = "BIGINT UNSIGNED"
    )
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(5) default 'USER'",
            nullable = false)
    private Role role;

    @Override
    public String toString() {
        return "{\n" +
                "\t\"id\": " + id + ",\n" +
                "\t\"name\": \"" + firstName + "\",\n" +
                "\t\"email\": \"" + email + "\",\n" +
                "\t\"password\": \"" + password + "\",\n" +
                "\t\"phone\": \"" + phone + "\",\n" +
                "\t\"birthDate\": \"" + birthDate + "\",\n" +
                "\t\"picture\": \"" + picture + "\"\n" +
                "}";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}