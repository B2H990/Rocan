package Mafia.Occisor.module;

import Maifa.Occisor.traits.Labelable;

public enum ModuleCategory implements Labelable {
    COMBAT("Combat"),
    OTHER("Other"),
    PLAYER("Player"),
    MOVEMENT("Movement"),
    RENDER("Render");

    String label;

    ModuleCategory(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
