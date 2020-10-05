package com.learning.backend.repositories;

import com.learning.backend.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("SELECT s FROM Subject s WHERE s.name LIKE %?1%")
    List<Subject> search(String query);
}
