package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.commands.interfaces.Command;
import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.utils.DateUtils;

import java.io.PrintWriter;
import java.util.*;

public class ViewByDateCommand implements Command {

    private final Map<String, List<Task>> tasks;
    private final PrintWriter out;


    public ViewByDateCommand(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }


    @Override
    public void executeCommand() {
        Comparator<Task> compareByDate = Comparator.comparing(p -> DateUtils.formatDate(p.getCreatedAt()));
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            List<Task> newTasks = project.getValue();
            Collections.sort(newTasks, compareByDate);
            for (Task task : newTasks) {
                out.printf("    [%c] %s: %s, createdDate:%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getCreatedAt());
            }
            out.println();
        }
    }
}
