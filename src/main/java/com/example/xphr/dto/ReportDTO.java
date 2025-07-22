package com.example.xphr.dto;

/**
 * Report DTO.
 */
public record ReportDTO(
        String username,
        String employeeName,
        String projectName,
        Double totalHours
) {}
