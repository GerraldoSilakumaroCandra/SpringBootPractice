package com.lewis.spring_boot_practice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@NoArgsConstructor
public class UpdateTodoRequest {
    private String title;
    private boolean completed;
}
