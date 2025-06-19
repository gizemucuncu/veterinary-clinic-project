package com.patika.veterinaryClinic.repository;

import com.patika.veterinaryClinic.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("""
            SELECT COUNT(v) > 0 FROM Vaccine v
            WHERE v.name = :name AND v.code = :code AND v.animal.id = :animalId
            AND v.protectionStartDate <= :endDate
            AND v.protectionFinishDate >= :startDate
            """)
    boolean existsConflictingVaccine(
            @Param("name") String name,
            @Param("code") String code,
            @Param("animalId") Long animalId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
