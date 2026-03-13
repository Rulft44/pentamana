package net.hederamc.pentamana.client.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.hederamc.pentamana.client.api.ManaBarGui;
import net.hederamc.pentamana.client.config.PentamanaConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin implements ManaBarGui {
    @Inject(
        method = "extractHotbarAndDecorations(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V",
        at = @At("TAIL")
    )
    private void extractManaBar(CallbackInfo ci, @Local GuiGraphicsExtractor context) {
        this.extractManaBar(context);
    }

    @Override
    public void extractManaBar(GuiGraphicsExtractor context) {
        Minecraft client = Minecraft.getInstance();
        float manaCapacity = client.player.getManaCapacity();
        if (manaCapacity <= 0.0f) {
            return;
        }

        float mana = client.player.getMana();

        PentamanaConfig config = PentamanaConfig.HANDLER.instance();
        int maxContainers = config.manabarMaxStars;

        int middleX = context.guiWidth() / 2;
        int containers = Math.min((int)manaCapacity / 2, maxContainers);
        int manaPoints = (int)(mana / manaCapacity * containers * 2);
        int width = containers * 8;
        int x = middleX - (width / 2);
        int y = context.guiHeight() - 69;
        for (int i = 0; i < containers; i++) {
            context.blitSprite(RenderPipelines.GUI_TEXTURED, MANA_CONTAINER_SPRITE, x, y, 9, 9);

            if (manaPoints >= 2) {
                context.blitSprite(RenderPipelines.GUI_TEXTURED, MANA_FULL_SPRITE, x, y, 9, 9);
            } else if (manaPoints >= 1) {
                context.blitSprite(RenderPipelines.GUI_TEXTURED, MANA_HALF_SPRITE, x, y, 9, 9);
            }

            manaPoints -= 2;
            x += 8;
        }
    }
}
