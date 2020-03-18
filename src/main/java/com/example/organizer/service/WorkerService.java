package com.example.organizer.service;

import com.example.organizer.exception.DataException;
import com.example.organizer.model.Worker;
import com.example.organizer.repository.WorkerRepository;
import com.example.organizer.viewmodel.WorkerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public WorkerResponse createUpdateWorker(Worker worker) {
        Worker wFromDB = workerRepository.findByFirstNameAndSecondName(worker.getFirstName(), worker.getSecondName());
        if (wFromDB != null && !wFromDB.getId().equals(worker.getId())) {
            throw new DataException("Such credentials are already exist!");
        }
        Worker wr = workerRepository.save(worker);
        return new WorkerResponse(wr, "Successful");
    }

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public void removeWorker(UUID uuid) {
        workerRepository.deleteById(uuid);
    }
}
