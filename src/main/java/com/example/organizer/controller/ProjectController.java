package com.example.organizer.controller;

import com.example.organizer.model.Project;
import com.example.organizer.service.ProjectService;
import com.example.organizer.viewmodel.ProjectResponse;
import com.example.organizer.viewmodel.SubscriptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getAll() {
        return projectService.findAll();
    }

    @PostMapping
    public ProjectResponse create(@RequestBody Project project,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        return projectService.createProject(project);
    }

    @PostMapping("/change")
    public ProjectResponse changeName(@RequestBody Project project,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        return projectService.changeName(project);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable UUID id) {

        projectService.remove(id);
        return "Project removed";
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestBody SubscriptionRequest request) {

        projectService.subscribe(request.getWorker(), request.getProject());
        return "Specialists assigned";
    }


    @PostMapping("/unsubscribe")
    public String unsubscribe(@RequestBody SubscriptionRequest request) {

        projectService.unsubscribe(request.getWorker(), request.getProject());
        return "Specialists unassigned";
    }
}
