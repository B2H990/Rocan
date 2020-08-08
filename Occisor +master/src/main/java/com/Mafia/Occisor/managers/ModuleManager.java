package com.Mafia.Occisor.managers;

import com.Mafia.Occisor.modules.*;
import com.Mafia.Occisor.modules.Module;

import java.util.ArrayList;

public class ModuleManager {
    public ArrayList<Module> moduleList = new ArrayList<Module>();

    public ModuleManager() {
        moduleList.add(new Sprint());
        moduleList.add(new Fullbright());
        moduleList.add(new AutoClicker());
        moduleList.add(new NoFOV());
        moduleList.add(new QuickEat());
        moduleList.add(new AutoCrystal());
        moduleList.add(new Mafia.Occisor.modules.movement.InDaHole());
        moduleList.add(new Speed());

        moduleList.add(new Panic());
        moduleList.add(new Rainbow());
    }

    public static ArrayList<Module> getModules(){
        return moduleList;
    }

    public static ArrayList<Module> getModuleByCategory(Module.Category category) {
        ArrayList<Module> moduleByCategory = new ArrayList<>();
        for (Module module : moduleList) {
            if (module.getCategory() == category){
                moduleByCategory.add(module);
            }
        }
        return moduleByCategory;
    }

    public ArrayList<Module> getEnabledModules() {
        ArrayList<Module> enabledModules = new ArrayList<Module>();
        for (Module module : moduleList) {
            if (module.getState()) {
                enabledModules.add(module);
            }
        }

        return enabledModules;
    }

    public Module getModule(String name) {
        Module the_module = null;
        for (Module module : moduleList) {
            if (module.name.equalsIgnoreCase(name)) {
                the_module = module;
                break;
            }
        }

        return the_module;
    }
}
