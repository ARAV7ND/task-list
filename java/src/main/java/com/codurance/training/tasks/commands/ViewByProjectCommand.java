package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.commands.interfaces.Command;
import com.codurance.training.tasks.model.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ViewByProjectCommand implements Command {
    private Map<String, List<Task>> tasks;
    private PrintWriter out;

    public ViewByProjectCommand(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void executeCommand() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %s: %s, deadline: %s, createdDate:%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getDeadline(), task.getCreatedAt());
            }
            out.println();
        }
    }
}
