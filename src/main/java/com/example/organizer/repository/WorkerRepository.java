package com.example.organizer.repository;

import com.example.organizer.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkerRepository extends JpaRepository<Worker, UUID> {

    Worker findByFirstNameAndSecondName(String fName, String sName);
//Worker
}
