package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.commands.interfaces.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddProjectCommand implements Command {
    private final String name;
    private final Map<String, List<Task>> tasks;


    public AddProjectCommand(Map<String, List<Task>> tasks, String name) {
        this.tasks = tasks;
        this.name = name;
    }

    @Override
    public void executeCommand() {
        tasks.put(name, new ArrayList<Task>());
    }

}
