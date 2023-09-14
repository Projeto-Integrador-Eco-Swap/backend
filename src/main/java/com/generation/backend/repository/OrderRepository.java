package com.generation.backend.repository;

import com.generation.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {

    Order findByName(@Param("name") String name);

    void deleteByName(String name);
}
