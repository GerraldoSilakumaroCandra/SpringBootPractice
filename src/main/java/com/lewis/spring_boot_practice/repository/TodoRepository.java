package com.lewis.spring_boot_practice.repository;

import com.lewis.spring_boot_practice.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Page<Todo> findByCompleted(Boolean completed, Pageable pageable);
}
