package com.codurance.training.tasks.model;

import java.util.Date;

public final class Task {
    private final String id;
    private final String description;
    private boolean done;
    private Date deadline;

    private final Date createdAt;

    public Task(String id, String description, boolean done, Date createdAt) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.createdAt = createdAt;
    }


    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


}
