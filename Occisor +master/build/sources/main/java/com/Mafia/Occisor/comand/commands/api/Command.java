package com.Mafia.Occisor.commands.api;

import com.Mafia.Occisor.Occisor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class Command {
    public String name;
    public String description;

    protected Minecraft minecraft = Minecraft.getMinecraft();

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public boolean DoCommand(String[] commandArgs) { return false; }

    protected void clientResponse(ITextComponent response) {
        minecraft.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(Occisor.commands.commandManager.responsePrefix + response.getFormattedText()));
    }

    protected ITextComponent responseBuilder(String message) {
        return new TextComponentString(message).setStyle(Occisor.commands.commandManager.chatFormat);
    }
}
