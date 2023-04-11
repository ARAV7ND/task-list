package com.codurance.training.tasks.commands.commandFactory;

import com.codurance.training.tasks.commands.commandFactory.interfaces.CommandFactory;
import com.codurance.training.tasks.commands.*;
import com.codurance.training.tasks.commands.interfaces.Command;
import com.codurance.training.tasks.model.Task;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CommandFactoryImpl implements CommandFactory {
    private final Map<String, List<Task>> tasks;

    private final PrintWriter out;

    private static long lastId = 0;

    private long nextId() {
        return ++lastId;
    }

    public CommandFactoryImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public Command createCommand(String commandLine) {
        String[] commandRest = commandLine.split(" ");
        String command = commandRest[0];
        int commandStreamLength = commandRest.length;
        String taskIdByUser = commandStreamLength > 4 ? commandRest[2] : String.valueOf(nextId());
        switch (command) {
            case "show":
                return new ShowCommand(out, tasks);
            case "add":
                switch (commandRest[1]) {
                    case "project":
                        return new AddProjectCommand(tasks, commandRest[2]);
                    case "task":
                        return new AddTaskCommand(taskIdByUser, tasks, out, commandRest[commandStreamLength-2], commandRest[commandStreamLength-1]);
                }
            case "check":
                return new CheckCommand(tasks, commandRest[1], true, out);
            case "uncheck":
                return new CheckCommand(tasks, commandRest[1], false, out);
            case "help":
                return new HelpCommand(out);
            case "delete":
                return new DeleteCommand(tasks, out, commandRest[1]);
            case "deadline":
                return new DeadlineCommand(tasks, out, commandRest[1], commandRest[2]);
            case "today":
                return new TodayCommand(tasks, out);
            case "view":
                switch (commandRest[1]) {
                    case "byDate":
                        return new ViewByDateCommand(tasks, out);
                    case "byDeadline":
                        return new ViewByDeadlineCommand(tasks, out);
                    case "byProject":
                        return new ViewByProjectCommand(tasks, out);
                }
            default:
                out.printf("Please Enter Valid command %s.%n.", command);
                out.println();
                break;
        }
        return new ErrorCommand(out, command);
    }
}