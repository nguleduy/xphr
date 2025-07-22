package com.example.xphr.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * TimeRecord model.
 */
@Entity
@Table(name = "time_record")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "time_from", nullable = false)
    private LocalDateTime timeFrom;

    @Column(name = "time_to", nullable = false)
    private LocalDateTime timeTo;
}
