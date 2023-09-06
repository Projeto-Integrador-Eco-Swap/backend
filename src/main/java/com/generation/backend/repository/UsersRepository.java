package com.generation.backend.repository;

import com.generation.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    public List<Users> findAllByNameContainingIgnoreCase(@Param("name") String name);
}
