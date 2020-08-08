package Mafia.Occisor.modules.combat;

import java.util.function.Predicate;
import Mafia.Occisor.listener.EventHandler;
import Mafia.Occisor.listener.Listener;
import Mafia.Occisor.events.PacketEvent;
import Mafia.Occisor.modules.Module;
import net.minecraft.init.Items;

//need to make the events

public class FastCrystal extends Module {
    @EventHandler
    private Listener<PacketEvent.Receive> receiveListener = new Listener((event) -> {
        if (mc.field_71439_g != null && (mc.field_71439_g.func_184614_ca().func_77973_b() == Items.field_185158_cP || mc.field_71439_g.func_184592_cb().func_77973_b() == Items.field_185158_cP)) {
            mc.field_71467_ac = 0;
        }

    }, new Predicate[0]);
}
