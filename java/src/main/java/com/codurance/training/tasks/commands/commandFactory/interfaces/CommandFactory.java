package com.codurance.training.tasks.commands.commandFactory.interfaces;

import com.codurance.training.tasks.commands.interfaces.Command;

public interface CommandFactory {
    Command createCommand(String commandLineArgs);
}
