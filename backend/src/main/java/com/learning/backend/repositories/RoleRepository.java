package com.learning.backend.repositories;

import java.util.Optional;

import com.learning.backend.models.ERole;
import com.learning.backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}