package com.Mafia.Occisor.Gui;

import com.Mafia.Occisor.Gui.Components.Frame;
import com.Mafia.Occisor.Gui.Components.ModuleButton;
import com.Mafia.Occisor.managers.ModuleManager;
import com.Mafia.Occisor.modules.Module;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;

public class Gui extends GuiScreen {

    public static ArrayList<Frame> frames;
    public static ArrayList<ModuleButton> moduleButtons;

    public Gui(){

        int FrameX = 10;
        int FrameY = 10;
        int FrameWidth = 50;
        int FrameHeight = 50;



        for (Module.Category category : Module.Category.values()){
            Gui.frames.add(new Frame(FrameX, FrameY, FrameWidth, FrameHeight, category));
        }
    }




}
