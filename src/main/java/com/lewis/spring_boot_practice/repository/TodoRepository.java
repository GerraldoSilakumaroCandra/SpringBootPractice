package com.lewis.spring_boot_practice.repository;

import com.lewis.spring_boot_practice.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
