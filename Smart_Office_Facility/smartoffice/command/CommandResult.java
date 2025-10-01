package com.example.smartoffice.command;

public class CommandResult {
    private final boolean success;
    private final String message;

    private CommandResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static CommandResult success(String msg) {
        return new CommandResult(true, msg);
    }

    public static CommandResult failure(String msg) {
        return new CommandResult(false, msg);
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}
