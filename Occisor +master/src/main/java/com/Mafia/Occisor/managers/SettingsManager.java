package com.Mafia.Occisor.managers;

import com.Mafia.Occisor.setting.annotation.Settings;
import com.frontear.hephaestus.modules.api.Module;

import java.util.ArrayList;

public class SettingsManager {

    public ArrayList<Settings> settings;

    public ArrayList<Settings> getSettings() {
        return settings;
    }

    public void addSetting(Settings in) {
        settings.add(in);
    }

    public ArrayList<Settings> getSettingsByMod(Module mod){
        ArrayList<Settings> out = new ArrayList<>();
        for(Settings s : getSettings()){
            if(s.getParent().equals(mod)){
                out.add(s);
            }
        }
        if(out.isEmpty()){
            return null;
        }
        return out;
    }
}


