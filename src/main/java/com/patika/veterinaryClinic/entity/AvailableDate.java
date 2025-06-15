package com.patika.veterinaryClinic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="available_dates", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"doctor_id", "available_date"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "available_date", nullable = false)
    private LocalDate availableDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
}
