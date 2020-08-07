package com.Mafia.Occisor.Gui.Components;

public class ModuleButton {

    public String Module;

    public boolean expanded = false;
    public boolean toggled = false;
    public boolean highlighted = false;
    public boolean FrameHighlighted;

    public int xPos;
    public int yPos;
    public int width;
    public int height;

    public int xPosText;
    public int yPosText;

    public int textColor;
    public int textColorToggled;
    public int textColorHighlighted;
    public int textColorToggledHighlighted;
    public int textColorFrameHighlighted;
    public int textColorToggledFrameHighlighted;

    public int edgeWidth;
    public int edgeColor;
    public int edgeColorToggled;
    public int edgeColorHighlighted;
    public int edgeColorToggledHighlighted;
    public int edgeColorFrameHighlighted;
    public int edgeColorToggledFrameHighlighted;

    public int baseColor;
    public int baseColorToggled;
    public int baseColorHighlighted;
    public int baseColorToggledHighlighted;
    public int baseColorFrameHighlighted;
    public int baseColorToggledFrameHighlighted;



    public ModuleButton(String Module, boolean expanded, boolean toggled, boolean highlighted, boolean FrameHighlighted,int xPos, int yPos, int width, int height, int xPosText, int yPosText, int textColor, int textColorToggled, int textColorHighlighted, int textColorToggledHighlighted, int textColorFrameHighlighted, int textColorToggledFrameHighlighted, int edgeWidth, int edgeColor, int edgeColorToggled, int edgeColorHighlighted, int edgeColorToggledHighlighted, int edgeColorFrameHighlighted, int edgeColorToggledFrameHighlighted,     int baseColor, int baseColorToggled, int baseColorHighlighted, int baseColorToggledHighlighted, int baseColorFrameHighlighted, int baseColorToggledFrameHighlighted) {

        this.Module = Module;

        this.expanded = expanded;
        this.toggled = toggled;
        this.highlighted = highlighted;
        this.FrameHighlighted = FrameHighlighted;

        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.xPosText = xPosText;
        this.yPosText = yPosText;

        this.textColor = textColor;
        this.textColorToggled = textColorToggled;
        this.textColorHighlighted = textColorHighlighted;
        this.textColorToggledHighlighted = textColorToggledHighlighted;
        this.textColorFrameHighlighted = textColorFrameHighlighted;
        this.textColorToggledFrameHighlighted = textColorToggledFrameHighlighted;

        this.edgeWidth = edgeWidth;
        this.edgeColor = edgeColor;
        this.edgeColorToggled = edgeColorToggled;
        this.edgeColorHighlighted = edgeColorHighlighted;
        this.edgeColorToggledHighlighted = edgeColorToggledHighlighted;
        this.edgeColorFrameHighlighted = edgeColorFrameHighlighted;
        this.edgeColorToggledFrameHighlighted = edgeColorToggledFrameHighlighted;

        this.baseColor = baseColor;
        this.baseColorToggled = baseColorToggled;
        this.baseColorHighlighted = baseColorHighlighted;
        this.baseColorToggledHighlighted = baseColorToggledHighlighted;
        this.baseColorFrameHighlighted = baseColorFrameHighlighted;
        this.baseColorToggledFrameHighlighted = baseColorToggledFrameHighlighted;


    }

    private int getFrameXpos(Frame frame){
        return frame.xPos;
    }

    private int getFrameYpos(Frame frame){
        return frame.xPos;
    }

}



