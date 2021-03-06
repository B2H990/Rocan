package com.Mafia.Occisor.client;

import com.Mafia.Occisor.managers.ModuleManager;
import com.Mafia.Occisor.managers.SettingsManager;
import com.Mafia.Occisor.managers.UIManager;
import com.Mafia.Occisor.modules.Module;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.Display;

public class HephaestusClient {
    public final String CLIENT_NAME = "Occisor";
    public final double CLIENT_VERSION = 1.0;

    public ModuleManager moduleManager;
    public UIManager uiManager;


    //classes
    public SettingsManager settingsManager;

    public HephaestusClient() {
        moduleManager = new ModuleManager();
        uiManager = new UIManager();


        Display.setTitle(CLIENT_NAME + " " + "Client" + " " + CLIENT_VERSION);
    }

    @SubscribeEvent
    public void onGui(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            uiManager.Draw();
            for (int i = 0; i < moduleManager.getEnabledModules().size(); i++) {
                moduleManager.getEnabledModules().get(i).onGui(i);
            }
        }
    }



    @SubscribeEvent
    public void onUpdate(TickEvent.PlayerTickEvent event) {
        if (event.type == TickEvent.Type.PLAYER) {
            for (Module module : moduleManager.getEnabledModules()) {
                module.onUpdate();
            }
        }
    }

    @SubscribeEvent
    public void onRender(RenderHandEvent event) {
        for (Module module : moduleManager.getEnabledModules()) {
            module.onRender();
        }
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        for (Module module : moduleManager.moduleList) {
            module.onKey();
        }
    }

    public void init(FMLInitializationEvent event){
        settingsManager = new SettingsManager();
    }

    public void preinit(FMLPreInitializationEvent event){

    }

    public void postinit(FMLPostInitializationEvent event){

    }
}
