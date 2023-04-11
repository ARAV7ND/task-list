package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.commands.interfaces.Command;
import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.utils.DateUtils;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class DeadlineCommand implements Command {

    private final String deadline;
    private final String taskId;
    private final Map<String, List<Task>> tasks;
    private final PrintWriter out;

    public DeadlineCommand(Map<String, List<Task>> tasks, PrintWriter out, String taskId, String deadline) {
        this.deadline = deadline;
        this.taskId = taskId;
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void executeCommand() {
        for (List<Task> projectTasks : tasks.values()) {
            for (Task task : projectTasks) {
                if (task.getId().equals(taskId)) {
                    try {
                        task.setDeadline(DateUtils.stringToDate(deadline));
                        out.printf("Deadline set for task %s.%n", taskId);
                        out.println();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %s%n", taskId);
        out.println();
    }
}
