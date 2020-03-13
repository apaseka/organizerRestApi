package com.example.organizer.viewmodel;

import com.example.organizer.model.Project;


public class ProjectResponse {

    private Project project;
    private String msg;

    public ProjectResponse(Project project, String msg) {
        this.project = project;
        this.msg = msg;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
