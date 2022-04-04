package me.med.springmvc_thymeleaf.Repositories;


import me.med.springmvc_thymeleaf.Entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNameContains(String name, Pageable pageable);
}