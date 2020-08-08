package Mafia.Occisor.modules.HUD;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.time.ZonedDateTime;
import java.util.Random;
import Mafia.Occisor.modules.Module;
import Mafia.Occisor.setting.annotation.Setting;
import Mafia.Occisor.setting.annotation.Settings;

public class Welcomer extends Module {
    private Setting<Float> x = this.register(Settings.f("X", 400.0F));
    private Setting<Float> y = this.register(Settings.f("Y", 0.0F));
    Random r = new Random();
    ZonedDateTime time = ZonedDateTime.now();
    private int counter = 0;

    public void onRender() {
        float xPos = (Float)this.x.getValue();
        String timer = this.time.getHour() <= 11 ? "Good Morning " : (this.time.getHour() <= 18 && this.time.getHour() > 11 ? "Good Afternoon " : (this.time.getHour() <= 23 && this.time.getHour() > 18 ? "Good Evening " : ""));
        mc.field_71466_p.func_175063_a(timer + ChatFormatting.WHITE + Wrapper.getPlayer().getDisplayNameString() + ChatFormatting.RESET + ", Enjoy using " + ChatFormatting.PURPLE + "Occisor", xPos, (Float)this.y.getValue(), 16777215);
    }

    public static int rainbow(int delay) {
        double rainbowState = Math.ceil((double)(System.currentTimeMillis() + (long)delay) / 20.0D);
        rainbowState %= 360.0D;
        return Color.getHSBColor((float)(rainbowState / 360.0D), 0.8F, 0.7F).getRGB();
    }
}
