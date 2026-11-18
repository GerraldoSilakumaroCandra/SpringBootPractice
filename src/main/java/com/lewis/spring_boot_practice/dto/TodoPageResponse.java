package com.lewis.spring_boot_practice.dto;

import java.util.List;

public record TodoPageResponse(
        List<TodoResponse> items,
        int page,
        int size,
        int totalPages
) {}
