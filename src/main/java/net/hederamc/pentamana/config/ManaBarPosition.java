package net.hederamc.pentamana.config;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

public enum ManaBarPosition implements NameableEnum {
    CENTER(0, 0),
    ABOVE_HUNGER(49, 19);

    public final int x;
    public final int y;

    ManaBarPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal(name());
    }
}
