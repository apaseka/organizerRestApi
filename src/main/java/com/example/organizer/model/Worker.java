package com.example.organizer.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Worker {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Title title;

    @NotBlank(message = "Can not be empty!")
    private String firstName;

    @NotBlank(message = "Can not be empty!")
    private String secondName;

    private Integer age;

    private Date lastModifiedOn;

    protected Worker() {
        this.id = UUID.randomUUID();
        this.lastModifiedOn = new Date();
    }

    public Worker(Title title, String firstName, String secondName, Integer age) {
        this();
        this.title = title;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    public Worker(String id, Title title, String firstName, String secondName, Integer age) {
        this(title, firstName, secondName, age);
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

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return title == worker.title &&
                Objects.equals(firstName, worker.firstName) &&
                Objects.equals(secondName, worker.secondName) &&
                Objects.equals(age, worker.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, firstName, secondName, age);
    }
}
