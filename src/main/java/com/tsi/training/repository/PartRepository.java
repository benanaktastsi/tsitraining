package com.tsi.training.repository;

import com.tsi.training.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {
    Optional<Part> findByDescription(String description);
    List<Part> findByDescriptionIn(List<String> descriptions);
}
