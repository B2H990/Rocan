package com.Mafia.Occisor.Gui.Components;

import org.lwjgl.opengl.GL11;

public class Frame {

    public int xPos, yPos, width, height;
    public String title;

    public Frame(int xPos, int yPos, int width, int height, String title) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private int xPos2 = xPos + width;
    private int yPos2 = yPos + height;

    public void render(int xPos, int yPos, int width, int height, String title){

    }

}
