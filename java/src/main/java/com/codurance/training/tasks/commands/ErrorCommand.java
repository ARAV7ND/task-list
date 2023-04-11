package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.commands.interfaces.Command;

import java.io.PrintWriter;

public class ErrorCommand implements Command {
    private final PrintWriter out;
    private final String command;

    public ErrorCommand(PrintWriter out, String command) {
        this.out = out;
        this.command = command;
    }

    @Override
    public void executeCommand() {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
