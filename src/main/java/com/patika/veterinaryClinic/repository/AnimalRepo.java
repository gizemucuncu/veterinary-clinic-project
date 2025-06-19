package com.patika.veterinaryClinic.repository;

import com.patika.veterinaryClinic.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {
    List<Animal> findByNameContainingIgnoreCase(String name);
}
