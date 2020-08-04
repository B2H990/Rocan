package com.Mafia.Occisor.commands;

import com.Mafia.Occisor.Occisor;
import com.Mafia.Occisor.commands.api.Command;

public class Help extends Command {
    public Help() {
        super("help", "lists all possible commands");
    }

    @Override
    public boolean DoCommand(String[] commandArgs) {
        for (Command command : Hephaestus.commands.commandManager.commandList) {
            clientResponse(responseBuilder(command.name + ": " + command.description));
        }

        return true;
    }
}
