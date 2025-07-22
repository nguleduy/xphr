package com.example.xphr.service.impl;

import com.example.xphr.dto.ReportDTO;
import com.example.xphr.repository.TimeRecordRepository;
import com.example.xphr.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Report Service Impl.
 */
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    final TimeRecordRepository timeRecordRepository;


    @Override
    public Page<ReportDTO> getReportDataForUser(
            String username,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable) {
        return timeRecordRepository.getReportDataForUser(startDate, endDate, username, pageable)
                .map(this::mapRowToReportDTO);
    }

    @Override
    public Page<ReportDTO> getReportData(LocalDateTime startDate,
                                         LocalDateTime endDate,
                                         Pageable pageable) {
        return timeRecordRepository.getReportData(startDate, endDate, pageable)
                .map(this::mapRowToReportDTO);
    }

    private ReportDTO mapRowToReportDTO(Object[] row) {
        return new ReportDTO(
                (String) row[0], // username
                (String) row[1], // employee name
                (String) row[2], // project name
                ((Number) row[3]).doubleValue() // total hours
        );
    }

}
