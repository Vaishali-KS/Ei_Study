package com.example.smartoffice.command;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CommandManager {
    private static final Logger LOGGER = Logger.getLogger(CommandManager.class.getName());
    private final List<Command> history = new ArrayList<>();

    public CommandResult execute(Command cmd) {
        CommandResult result = cmd.execute();
        history.add(cmd);
        LOGGER.info("Executed command: " + cmd.getClass().getSimpleName() + ", result: " + result.getMessage());
        return result;
    }

    public List<Command> getHistory() {
        return List.copyOf(history);
    }
}
