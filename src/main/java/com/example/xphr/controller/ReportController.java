package com.example.xphr.controller;

import com.example.xphr.dto.ReportDTO;
import com.example.xphr.service.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

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
    public String showReport(
        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
        Model model,
        Authentication authentication,
        Pageable pageable) {

        if (startDate == null || endDate == null) {
            startDate = LocalDateTime.now().minusMonths(1);
            endDate = LocalDateTime.now();
        }

        Page<ReportDTO> reportPage;
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            reportPage = reportService.getReportData(startDate, endDate, pageable);
        } else {
            reportPage = reportService.getReportDataForUser(
                    authentication.getName(), startDate, endDate, pageable);
        }

        model.addAttribute("reportPage", reportPage);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        log.info("Report Data: {}", reportPage);
        return "work_hours_report";
    }
}
