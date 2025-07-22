package com.example.xphr.controller;

import com.example.xphr.dto.ReportDTO;
import com.example.xphr.service.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.xphr.constant.PathDefinition.REPORT_URL;

/**
 * Report Controller.
 */
@Controller
@RequestMapping(REPORT_URL)
@RequiredArgsConstructor
@Tag(name = "Report", description = "Report APIs")
@Slf4j
public class ReportController {

    final ReportService reportService;

    /**
     * Show Report.
     */
    @GetMapping
    public String showReport(@RequestParam(required = false) @DateTimeFormat(iso
                                     = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                             @RequestParam(required = false) @DateTimeFormat(iso
                                     = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                             Model model,
                             Authentication authentication) {
        if (startDate == null || endDate == null) {
            startDate = LocalDateTime.now().minusMonths(1);
            endDate = LocalDateTime.now();
        }

        List<ReportDTO> reportData;

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            reportData = reportService.getReportData(startDate, endDate);
        } else {
            String username = authentication.getName();
            reportData = reportService.getReportDataForUser(username, startDate, endDate);
        }

        model.addAttribute("reportData", reportData);

        log.info("Report data: {}", reportData);
        return "work_hours_report";
    }
}
