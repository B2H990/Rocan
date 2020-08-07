package com.Mafia.Occisor;

import com.Mafia.Occisor.client.HephaestusClient;
import com.Mafia.Occisor.client.HephaestusCommands;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(
        modid = "occisor",
        version = "1.0",
        acceptedMinecraftVersions = "[1.12.2]"
)
public class Occisor
{
    public static HephaestusClient client;
    public static HephaestusCommands commands;

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(client = new HephaestusClient());
        MinecraftForge.EVENT_BUS.register(commands = new HephaestusCommands());
    }
}
