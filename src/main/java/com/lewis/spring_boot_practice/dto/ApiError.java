package com.lewis.spring_boot_practice.dto;

import java.util.Map;

public record ApiError(
        String code,
        String message,
        Map<String, String> fieldErrors
) {
}
