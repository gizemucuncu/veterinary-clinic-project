package com.patika.veterinaryClinic.repository;

import com.patika.veterinaryClinic.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long>, JpaSpecificationExecutor<Doctor> {

    // bu mail başka bir doktorda var mı?
    boolean existsByMail(String mail);

    // bu telefon başka bir doktorda var mı?
    boolean existsByPhone(String phone);
}
