package com.Mafia.Occisor.modules;

import com.Mafia.Occisor.managers.GUIManager;
import com.Mafia.Occisor.modules.api.Module;
import org.lwjgl.input.Keyboard;

public class ClickGUI extends Module {
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
