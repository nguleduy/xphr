package com.example.xphr.service;

import com.example.xphr.dto.ReportDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Report Service.
 */
public interface ReportService {

    List<ReportDTO> getReportData(LocalDateTime startDate, LocalDateTime endDate);

    List<ReportDTO> getReportDataForUser(
            String username,
            LocalDateTime startDate,
            LocalDateTime endDate);
}
