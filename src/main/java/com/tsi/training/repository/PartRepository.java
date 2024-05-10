package com.tsi.training.repository;

import com.tsi.training.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {
    Optional<Part> findByDescription(String description);

    @Query("select u.description from Part u where u.description in ?d")
    List<String> findByDescriptionIn(List<String> descriptions);
}
