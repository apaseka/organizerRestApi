package com.example.organizer.repository;

import com.example.organizer.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Modifying
    @Query("update Project p set p.name = :name where p.id = :id")
    int updateName(@Param("name") String name, @Param("id") UUID id);

    Project findByName(String name);
}
