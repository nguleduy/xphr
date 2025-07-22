package com.example.xphr.service;

import com.example.xphr.dto.ReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

/**
 * Report Service.
 */
public interface ReportService {

    Page<ReportDTO> getReportData(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable);

    Page<ReportDTO> getReportDataForUser(
            String username,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable);
}
