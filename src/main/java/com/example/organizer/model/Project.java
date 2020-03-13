package com.example.organizer.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
public class Project {

    @Id
    private UUID id;

    @NotBlank(message = "Can not be empty!")
    private String name;

    private Date fromDate;
    private Date toDate;
    private Integer workersNumber;

    @ManyToMany
    @JoinTable(
            name = "workers_projects",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "worker_id")}
    )
    private Set<Worker> workers = new HashSet<>();

    protected Project() {
        this.id = UUID.randomUUID();
    }

    public Project(String name, Date fromDate, Date toDate) {
        this();
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Project(String id, String name, Date fromDate, Date toDate) {
        this(name, fromDate, toDate);
        if (id != null) {
            this.id = UUID.fromString(id);
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Integer getWorkersNumber() {
        return workers.size();
    }

    public void setWorkersNumber(Integer workersNumber) {
        this.workersNumber = workersNumber;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name) &&
                Objects.equals(fromDate, project.fromDate) &&
                Objects.equals(toDate, project.toDate) &&
                Objects.equals(workersNumber, project.workersNumber) &&
                Objects.equals(workers, project.workers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fromDate, toDate, workersNumber, workers);
    }
}
