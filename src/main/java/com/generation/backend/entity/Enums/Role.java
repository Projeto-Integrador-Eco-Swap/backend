package com.generation.backend.entity.Enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "tb_roles")
public enum Role {
    USER,
    ADMIN
}
