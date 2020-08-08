package com.Mafia.Occisor.Gui.Components;

import com.Mafia.Occisor.managers.ModuleManager;
import com.Mafia.Occisor.modules.Module;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class Frame {

    public int xPos, yPos, width, height;
    public Module.Category title;
    private ArrayList<Module> modulesOfCategory;
    private int y = 1;

    public Frame(int xPos, int yPos, int width, int height, Module.Category title) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.title = title;

        modulesOfCategory = ModuleManager.getModuleByCategory(title);
        for (Module module : modulesOfCategory) {
            y = y + 10;
            new ModuleButton(module, false, module.getState(), false, false, xPos, y, 40, 10, 20, 5, );
        }
    }

    private int xPos2 = xPos + width;
    private int yPos2 = yPos + height;

    public void render(int xPos, int yPos, int width, int height, String title){

    }





}
