package com.csc340.jpademo.taskmanager;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "tasks")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskId;

    private int goalId;

    @Nonnull
    private String title;

    @Nullable
    private String details;

    @Nonnull
    private String status;

    public Task() {}

    public Task(int taskId, int goalId, String title, String details, String status) {
        this.taskId = taskId;
        this.goalId = goalId;
        this.title = title;
        this.details = details;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull String title) {
        this.title = title;
    }

    @Nullable
    public String getDetails() {
        return details;
    }

    public void setDetails(@Nullable String details) {
        this.details = details;
    }

    @Nonnull
    public String getStatus() {
        return status;
    }

    public void setStatus(@Nonnull String status) {
        this.status = status;
    }

    public void replaceTask(Task newTask) {
        if(newTask.getGoalId() != 0){
            this.goalId = newTask.getGoalId();
        }
        if(!newTask.getTitle().isEmpty()){
            this.title = newTask.getTitle();
        }
        if(newTask.getDetails()!=null){
            this.details = newTask.getDetails();
        }

        if(!newTask.getStatus().isEmpty()){
            this.status = newTask.getStatus();
        }
    }
}
