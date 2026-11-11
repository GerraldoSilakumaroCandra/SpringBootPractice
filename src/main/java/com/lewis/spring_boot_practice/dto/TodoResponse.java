package com.lewis.spring_boot_practice.dto;

public record TodoResponse(
        Long id,
        String title,
        boolean completed
) {
}
