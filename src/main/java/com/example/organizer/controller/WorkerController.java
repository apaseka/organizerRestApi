package com.example.organizer.controller;

import com.example.organizer.model.Worker;
import com.example.organizer.service.WorkerService;
import com.example.organizer.viewmodel.WorkerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public List<Worker> getAllWorkers() {

        return workerService.findAll();
    }

    @PostMapping
    public WorkerResponse createWorker(@RequestBody Worker worker,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        return workerService.createWorker(worker);
    }

    @PostMapping("/update")
    public WorkerResponse changeWorker(@RequestBody Worker worker,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        return workerService.updateWorker(worker);
    }

    @DeleteMapping("/{id}")
    public String deleteWorker(@PathVariable UUID id) {
        try {
            workerService.removeWorker(id);
        } catch (Throwable throwable) {
            return "Specialist can't be removed while is assigned to project!";
        }

        return "Specialist removed";
    }
}
