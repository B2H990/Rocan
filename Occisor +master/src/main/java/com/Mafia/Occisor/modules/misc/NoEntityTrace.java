package Mafia.Occisor.modules.misc;

import Mafia.Occisor.modules.Module;
import Mafia.Occisor.setting.annotation.Setting;
import Mafia.Occisor.setting.annotation.Settings;

public class NoEntityTrace extends Module {
    public Setting<NoEntityTrace.TraceMode> mode;
    public static NoEntityTrace INSTANCE;

    public NoEntityTrace() {
        this.mode = this.register(Settings.e("Mode", NoEntityTrace.TraceMode.DYNAMIC));
        INSTANCE = this;
    }

    public static boolean shouldBlock() {
        if (mc.field_71441_e == null) {
            return false;
        } else {
            return INSTANCE.isEnabled() && (INSTANCE.mode.getValue() == NoEntityTrace.TraceMode.STATIC || mc.field_71442_b.field_78778_j);
        }
    }

    public static enum TraceMode {
        STATIC,
        DYNAMIC;
    }
}
