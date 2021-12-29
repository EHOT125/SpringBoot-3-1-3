package com.springboot313.repository;

import com.springboot313.entities.Role;
import com.springboot313.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("from Role")
    Set<Role> getAll();
}
