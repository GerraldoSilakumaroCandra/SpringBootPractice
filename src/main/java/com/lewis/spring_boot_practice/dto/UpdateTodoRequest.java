package com.lewis.spring_boot_practice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTodoRequest {
    private String title;
    private boolean completed;
}
