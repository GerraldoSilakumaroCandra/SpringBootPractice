package com.lewis.spring_boot_practice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTodoRequest {
    @NotBlank(message = "Title must not be blank")
    private String title;
    private boolean completed;
}
