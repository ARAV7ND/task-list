package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.commands.interfaces.Command;
import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.utils.DateUtils;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ViewByDeadlineCommand implements Command {
    private final Map<String, List<Task>> tasks;
    private final PrintWriter out;

    public ViewByDeadlineCommand(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void executeCommand() {
        Comparator<Task> compareByDate = Comparator.comparing(p -> DateUtils.formatDate(p.getDeadline()));

        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            List<Task> newTasks = project.getValue();
            Collections.sort(newTasks, compareByDate);
            for (Task task : newTasks) {
                out.printf("    [%c] %s: %s, deadline: %s", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getDeadline());
            }
            out.println();
        }
    }
}
