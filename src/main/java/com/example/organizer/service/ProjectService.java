package com.example.organizer.service;

import com.example.organizer.exception.DataException;
import com.example.organizer.model.Project;
import com.example.organizer.model.Worker;
import com.example.organizer.repository.ProjectRepository;
import com.example.organizer.repository.WorkerRepository;
import com.example.organizer.viewmodel.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private WorkerRepository workerRepository;

    public ProjectResponse createProject(Project project) {
        Project pFromDb = projectRepository.findByName(project.getName());
        if (pFromDb != null) {
            throw new DataException("Project name must be unique!");
        }
        Project pr = projectRepository.save(project);
        return new ProjectResponse(pr, "Project created");
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public void remove(UUID uuid) {
        if (projectRepository.checkLinkage(uuid) == null) {
            projectRepository.deleteById(uuid);
        } else {
            throw new DataException("Can't remove with assigned specialists");
        }
    }

    @Transactional
    public String changeName(Project project) {
        try {
            projectRepository.updateName(project.getName(), project.getId());
            return "Project name updated";
        } catch (DataIntegrityViolationException e) {
            throw new DataException("Failed to rename, name not unique!");
        }
    }

    public void subscribe(Worker worker, Project project) {

        Optional<Worker> workerOptional = workerRepository.findById(worker.getId());
        Optional<Project> projectOptional = projectRepository.findById(project.getId());

        Project pr = projectOptional.get();
        Worker wr = workerOptional.get();

        pr.getWorkers().add(wr);

        projectRepository.save(pr);
    }

    public void unsubscribe(Worker worker, Project project) {
        Optional<Worker> workerOptional = workerRepository.findById(worker.getId());
        Optional<Project> projectOptional = projectRepository.findById(project.getId());

        Project pr = projectOptional.get();
        Worker wr = workerOptional.get();

        pr.getWorkers().remove(wr);

        projectRepository.save(pr);
    }
}
