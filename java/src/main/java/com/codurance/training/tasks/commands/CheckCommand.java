package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.commands.interfaces.Command;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class CheckCommand implements Command {
    private final Map<String, List<Task>> tasks;
    private final String idString;
    private final boolean isCompleted;
    private final PrintWriter out;

    private void setDone(String idString, boolean done) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId().equals(idString)) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", idString);
        out.println();
    }

    public CheckCommand(Map<String, List<Task>> tasks, String id, boolean isCompleted, PrintWriter out) {
        this.tasks = tasks;
        this.idString = id;
        this.isCompleted = isCompleted;
        this.out = out;
    }

    @Override
    public void executeCommand() {
        setDone(idString, isCompleted);
    }
}
