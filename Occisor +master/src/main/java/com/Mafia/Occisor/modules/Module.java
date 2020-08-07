package com.Mafia.Occisor.modules;

import com.Mafia.Occisor.Occisor;
import com.Mafia.Occisor.helpers.MultiColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class Module {
    public String name;
    public KeyBinding module;
    private boolean state;
    public Category category;

    private MultiColor multiColor;
    private float scaleFactor = 1.2f;
    protected Minecraft minecraft = Minecraft.getMinecraft();

    public Module(String name, Category category,int keyCode) {
        this.name = name;
        this.category = category;





        module = new KeyBinding(name, keyCode, "");
        ClientRegistry.registerKeyBinding(module);


    }

    public void onGui(int offset) {
        multiColor = MultiColor.getRainbow();
        Occisor.client.uiManager.uiPosition.positionText(Occisor.client.uiManager.position_on_screen, getModuleName(), 2, 8, 1.2f);
        Occisor.client.uiManager.uiPosition.GLScale(scaleFactor);
        minecraft.fontRenderer.drawStringWithShadow(getModuleName(), Occisor.client.uiManager.uiPosition.x_position, (Occisor.client.uiManager.uiPosition.y_position + 10) + (offset * 10), multiColor.getRGB());
        Occisor.client.uiManager.uiPosition.GLScale( 1 / scaleFactor);
    }

    public void onToggle(boolean state){}

    public void onUpdate(){}

    public void onRender(){}

    public void onKey(){
        if (module.isKeyDown()) {
            Toggle();
        }
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state){
        this.state = state;
    }

    public void Toggle() {
        setState(!getState());
        onToggle(getState());
    }

    public void setup () {}

    public String getModuleName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }




    public enum Category{
        COMBAT,
        PLAYER,
        MOVEMENT,
        MISC,
        CHAT,
        RENDER,
        GUI;
    }


}
