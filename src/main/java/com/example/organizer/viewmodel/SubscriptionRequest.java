package com.example.organizer.viewmodel;

import com.example.organizer.model.Project;
import com.example.organizer.model.Worker;

public class SubscriptionRequest {

    private Worker worker;
    private Project project;

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
