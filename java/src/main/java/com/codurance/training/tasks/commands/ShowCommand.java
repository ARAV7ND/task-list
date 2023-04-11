package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.commands.interfaces.Command;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ShowCommand implements Command {
    private final PrintWriter out;
    private final Map<String, List<Task>> tasks;

    public ShowCommand(PrintWriter out, Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
    }

    @Override
    public void executeCommand() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %s: %s, (created at: %s) ", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(),task.getCreatedAt());
                if (task.getDeadline() != null) {
                    out.printf(" (deadline: %s)", task.getDeadline());
                }
                out.println();
            }
            out.println();
        }
    }
}
