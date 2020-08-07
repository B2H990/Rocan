package com.Mafia.Occisor.Gui;

import com.Mafia.Occisor.Gui.Components.Frame;
import com.Mafia.Occisor.Gui.Components.ModuleButton;
import com.Mafia.Occisor.modules.Module;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;

public class Gui extends GuiScreen {

    public static ArrayList<Frame> frames;
    public static ArrayList<ModuleButton> moduleButtons;

    for (Module.Category c : Module.Category.values()) {
        String title = c.name();
        ClickGUI.panels.add(new Panel(title, px, py, pwidth, pheight, false, this) {
            @Override
            public void setup() {
                for (Module m : ModuleManager.getModules()) {
                    if (!m.getCategory().equals(c))continue;
                    this.Elements.add(new ModuleButton(m, this));
                }
            }
        });
        py += pyplus;
    }

}
