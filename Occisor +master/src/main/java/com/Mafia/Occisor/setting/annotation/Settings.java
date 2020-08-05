package com.Mafia.Occisor.setting.annotation;

import com.frontear.hephaestus.modules.api.Module;

import java.util.ArrayList;

public class Settings {

    //setup stuff
    public String name;
    public Module parent;

    //boolean values
    public boolean toggled;

    //int values
    public int intVal;
    public int intMin;
    public int inMax;

    //mode values
    public String mode;
    public ArrayList<String> Modes;


    public Settings(String name, Module parent, boolean toggled){
        this.name = name;
        this.parent = parent;
        this.toggled = toggled;
    }

    public Settings(String name, Module parent, int intVal, int intMin, int intMax){
        this.name = name;
        this.parent = parent;
        this.intVal = intVal;
        this.intMin = intMin;
        this.inMax = intMax;
    }

    public Settings(String name, Module parent, String mode, ArrayList<String> Modes){
        this.name = name;
        this.parent = parent;
        this.mode = mode;
        this.Modes = Modes;
    }
    

}
