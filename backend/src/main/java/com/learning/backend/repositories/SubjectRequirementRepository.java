package com.learning.backend.repositories;

import com.learning.backend.entities.SubjectRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRequirementRepository  extends JpaRepository<SubjectRequirement, Long> {
    @Modifying(clearAutomatically = true)
    @Query(value = "Delete from SubjectRequirement c WHERE c.id=:id")
    public void deleteById(@Param("id") Long id);
}
