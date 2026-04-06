package net.hederamc.pentamana.client.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import net.hederamc.fishbonetrehalose.api.Text;
import net.hederamc.pentamana.PentamanaClient;
import net.hederamc.pentamana.config.ManaBarPosition;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Pentamana"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Manabar"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Position"))
                                .description(OptionDescription.createBuilder()
                                        .text(Text.literal("Set your desired position of the manabar."))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Text.literal("X"))
                                        .binding(
                                                PentamanaClient.DEFAULTS.manabarX,
                                                () -> {
                                                    return PentamanaClient.CONFIG.manabarX;
                                                },
                                                newVal -> {
                                                    PentamanaClient.CONFIG.manabarX = newVal;
                                                })
                                        .controller(
                                            opt -> IntegerSliderControllerBuilder.create(opt)
                                                    .range(-278, 278)
                                                    .step(1))
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Text.literal("Y"))
                                        .binding(
                                                PentamanaClient.DEFAULTS.manabarY,
                                                () -> {
                                                    return PentamanaClient.CONFIG.manabarY;
                                                },
                                                newVal -> {
                                                    PentamanaClient.CONFIG.manabarY = newVal;
                                                })
                                        .controller(
                                                opt -> IntegerSliderControllerBuilder.create(opt)
                                                        .range(-330, 57)
                                                        .step(1))
                                        .build())
                                .option(Option.<ManaBarPosition>createBuilder()
                                        .name(Text.literal("Preset"))
                                        .binding(
                                                PentamanaClient.DEFAULTS.position,
                                                () -> {
                                                    return PentamanaClient.CONFIG.position;
                                                },
                                                newVal -> {
                                                    PentamanaClient.CONFIG.position = newVal;
                                                    PentamanaClient.CONFIG.manabarX = newVal.x;
                                                    PentamanaClient.CONFIG.manabarY = newVal.y;
                                                }
                                        )
                                        .controller(opt -> EnumControllerBuilder.create(opt)
                                                .enumClass(ManaBarPosition.class))
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Miscellaneous"))
                                .option(Option.<Integer>createBuilder()
                                        .name(Text.literal("Max Stars"))
                                        .description(OptionDescription.of(Text.literal("Limit the manabar length so it doesn't overflow the screen. Mana displayed will be scaled accordingly.")))
                                        .binding(
                                                PentamanaClient.DEFAULTS.manabarMaxStars,
                                                () -> {
                                                    return PentamanaClient.CONFIG.manabarMaxStars;
                                                },
                                                newVal -> {
                                                    PentamanaClient.CONFIG.manabarMaxStars = newVal;
                                                })
                                        .controller(
                                                opt -> IntegerSliderControllerBuilder.create(opt)
                                                        .range(10, 32)
                                                        .step(1))
                                        .build())
                                .build())
                        .build())
                .build()
                .generateScreen(parentScreen);
    }
}
