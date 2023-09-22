package com.thanhle.AirlinesApp.repository;

import com.thanhle.AirlinesApp.domain.Airlines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlinesRepository extends JpaRepository<Airlines, Long> {
    // Additional custom query methods can be defined here if needed
}
