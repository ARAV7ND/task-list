package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.commands.interfaces.Command;
import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.utils.DateUtils;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TodayCommand implements Command {
    final Map<String, List<Task>> tasks;
    final PrintWriter out;

    public TodayCommand(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void executeCommand() {
        Date today = new Date();
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                if (task.getDeadline() != null && DateUtils.formatDate(task.getDeadline()).equals(DateUtils.formatDate(today))) {
                    out.printf("    [%c] %d: %s (due today)%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                } else {
                    out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                }
            }
            out.println();
        }
    }
}
