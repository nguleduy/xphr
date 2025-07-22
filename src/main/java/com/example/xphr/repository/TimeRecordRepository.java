package com.example.xphr.repository;

import com.example.xphr.model.TimeRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TimeRecord Repository.
 */
@Repository
public interface TimeRecordRepository extends JpaRepository<TimeRecord, Long> {

    @Query(value = """
            SELECT 
                e.username AS username,
                e.name AS employee_name, 
                p.name AS project_name, 
                SUM(EXTRACT(EPOCH FROM (tr.time_to - tr.time_from)) / 3600) AS total_hours
            FROM time_record tr
            JOIN employee e ON e.id = tr.employee_id
            JOIN project p ON p.id = tr.project_id
            WHERE tr.time_from BETWEEN :startDate AND :endDate
            GROUP BY e.username, e.name, p.name
            ORDER BY e.username, e.name, p.name
            """, nativeQuery = true)
    Page<Object[]> getReportData(@Param("startDate") LocalDateTime startDate,
                                 @Param("endDate") LocalDateTime endDate,
                                 Pageable pageable);

    @Query(value = """
            SELECT 
                e.username AS username,
                e.name AS employee_name, 
                p.name AS project_name, 
                SUM(EXTRACT(EPOCH FROM (tr.time_to - tr.time_from)) / 3600) AS total_hours
            FROM time_record tr
            JOIN employee e ON e.id = tr.employee_id
            JOIN project p ON p.id = tr.project_id
            WHERE tr.time_from BETWEEN :startDate AND :endDate
                        AND e.username = :username
            GROUP BY e.username, e.name, p.name
            ORDER BY e.username, e.name, p.name
            """, nativeQuery = true)
    Page<Object[]> getReportDataForUser(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("username") String username,
            Pageable pageable);

}