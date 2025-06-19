package com.patika.veterinaryClinic.repository;

import com.patika.veterinaryClinic.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    boolean existsByPhone(String phone);

    boolean existsByMail(String mail);

    List<Customer> findByNameContainingIgnoreCase(String name);
}
