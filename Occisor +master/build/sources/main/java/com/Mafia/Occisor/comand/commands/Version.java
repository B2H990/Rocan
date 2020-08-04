package com.Mafia.Occisor.commands;

import com.Mafia.Occisor.Occisor;
import com.Mafia.Occisor.commands.api.Command;

public class Version extends Command {
    public Version() {
        super("version", "gets the version of the current client");
    }

    @Override
    public boolean DoCommand(String[] commandArgs) {
        clientResponse(responseBuilder("You are running " + Occisor.client.CLIENT_NAME + " " + Occisor.client.CLIENT_VERSION));

        return true;
    }
}
