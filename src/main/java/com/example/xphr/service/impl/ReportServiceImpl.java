package com.example.xphr.service.impl;

import com.example.xphr.dto.ReportDTO;
import com.example.xphr.repository.TimeRecordRepository;
import com.example.xphr.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Report Service Impl.
 */
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    final TimeRecordRepository timeRecordRepository;

    @Override
    public List<ReportDTO> getReportData(LocalDateTime startDate, LocalDateTime endDate) {
        return mapToReportDTOs(timeRecordRepository.getReportData(startDate, endDate));
    }

    @Override
    public List<ReportDTO> getReportDataForUser(
            String username,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        return mapToReportDTOs(timeRecordRepository.getReportData(startDate, endDate)).stream()
                .filter(r -> r.username().equals(username))
                .toList();
    }

    private List<ReportDTO> mapToReportDTOs(List<Object[]> rows) {
        return rows.stream()
                .map(row -> new ReportDTO(
                        (String) row[0],
                        (String) row[1],
                        (String) row[2],
                        ((Number) row[3]).doubleValue()))
                .toList();
    }

}
