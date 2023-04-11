package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.commands.interfaces.Command;
import com.codurance.training.tasks.utils.DateUtils;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AddTaskCommand implements Command {

    private String taskId;

    private final PrintWriter out;
    private final Map<String, List<Task>> tasks;
    private final String project;
    private final String description;

    public AddTaskCommand(String taskId, Map<String, List<Task>> tasks, PrintWriter out, String project, String description) {
        if (taskId != null && !isValidId(taskId)) {
            out.printf("Entered Id does not match the criteria :", taskId);
        }
        this.taskId = taskId;
        this.out = out;
        this.tasks = tasks;
        this.project = project;
        this.description = description;
    }

    @Override
    public void executeCommand() {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        try {
            projectTasks.add(new Task(taskId, description, false, DateUtils.stringToDate(DateUtils.formatDate(new Date()))));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isValidId(String id) {
        return id.matches("^[a-zA-Z0-9_-]+$");
    }
}
