package net.hederamc.pentamana.client.api;

import net.hederamc.pentamana.Pentamana;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

public interface ManaBarGui {
    Identifier MANA_CONTAINER_SPRITE = Identifier.fromNamespaceAndPath(Pentamana.MOD_NAMESPACE, "hud/mana/container");
    Identifier MANA_FULL_SPRITE = Identifier.fromNamespaceAndPath(Pentamana.MOD_NAMESPACE, "hud/mana/full");
    Identifier MANA_HALF_SPRITE = Identifier.fromNamespaceAndPath(Pentamana.MOD_NAMESPACE, "hud/mana/half");

    default void extractManaBar(GuiGraphicsExtractor context) {
    }
}
