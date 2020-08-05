package com.Mafia.Occisor.modules;

import com.Mafia.Occisor.managers.GUIManager;
import org.lwjgl.input.Keyboard;

public class ClickGUI extends Mafia.Occisor.modules.api.Module {
    public ClickGUI() {
        super("ClickGui", Keyboard.KEY_K);
    }

    @Override
    public void onGui(int offset) {}

    @Override
    public void onToggle(boolean state) {
        minecraft.displayGuiScreen(new GUIManager());
    }
}
