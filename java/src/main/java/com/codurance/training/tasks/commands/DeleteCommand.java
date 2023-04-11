package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.commands.interfaces.Command;
import com.codurance.training.tasks.model.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class DeleteCommand implements Command {

    private final Map<String, List<Task>> tasks;
    private final PrintWriter out;

    private final String taskId;

    public DeleteCommand(Map<String, List<Task>> tasks, PrintWriter out, String taskId) {
        this.tasks = tasks;
        this.out = out;
        this.taskId = taskId;
    }

    @Override
    public void executeCommand() {
        for (List<Task> projectTasks : tasks.values()) {
            for (Task task : projectTasks) {
                if (task.getId().equals(taskId)) {
                    projectTasks.remove(task);
                    out.printf("Deleted task with ID %d.", taskId);
                    out.println();
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", taskId);
        out.println();
    }
}
