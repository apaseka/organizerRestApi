package com.example.organizer.controller;

import com.example.organizer.exception.DataException;
import com.example.organizer.model.Worker;
import com.example.organizer.service.WorkerService;
import com.example.organizer.viewmodel.WorkerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/worker")
@CrossOrigin
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public List<Worker> getAllWorkers() {
        return workerService.findAll();
    }

    @PostMapping
    public WorkerResponse createUpdateWorker(@RequestBody Worker worker,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        return workerService.createUpdateWorker(worker);
    }

    @DeleteMapping("/{id}")
    public String deleteWorker(@PathVariable UUID id) {
            workerService.removeWorker(id);
        return "Specialist removed";
    }
}
