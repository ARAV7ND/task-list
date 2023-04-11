package com.codurance.training.tasks;

import com.codurance.training.tasks.commands.commandFactory.CommandFactoryImpl;
import com.codurance.training.tasks.commands.commandFactory.interfaces.CommandFactory;
import com.codurance.training.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class AppRunner implements Runnable{

    private static final String QUIT = "quit";
    private final BufferedReader in;
    private final PrintWriter out;
    private CommandFactory commandFactory;

    public AppRunner(BufferedReader in, PrintWriter out, Map<String, List<Task>> tasks) {
        this.in = in;
        this.out = out;
        commandFactory = new CommandFactoryImpl(tasks,out);
    }

    @Override
    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equalsIgnoreCase(QUIT)) {
                break;
            }
            commandFactory.createCommand(command).executeCommand();
        }
    }
}
