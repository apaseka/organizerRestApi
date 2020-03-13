package com.example.organizer.service;

import com.example.organizer.model.Worker;
import com.example.organizer.repository.WorkerRepository;
import com.example.organizer.viewmodel.WorkerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public WorkerResponse createWorker(Worker worker) {

        Worker wFromDB = workerRepository.findByFirstNameAndSecondName(worker.getFirstName(), worker.getSecondName());

        if (wFromDB != null && !wFromDB.getId().equals(worker.getId())) {
            return new WorkerResponse(wFromDB, "Such credentials are already exist!");
        }
        Worker wr = workerRepository.save(worker);
        return new WorkerResponse(wr, "Specialist created");
    }

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public WorkerResponse updateWorker(Worker worker) {
        Optional<Worker> wFromDB = workerRepository.findById(worker.getId());
        if (wFromDB.isPresent()) {
            WorkerResponse wr = createWorker(worker);
            if (wr.getMsg().contains("created"))
                wr.setMsg("Specialist info updated");
            return wr;
        }
        return new WorkerResponse(worker, "Wrong worker ID");
    }

    public void removeWorker(UUID uuid) {
        workerRepository.deleteById(uuid);
    }
}
