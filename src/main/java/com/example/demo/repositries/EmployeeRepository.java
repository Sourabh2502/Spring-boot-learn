package com.example.demo.repositries;

import com.example.demo.entities.EmployeeEntity;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
