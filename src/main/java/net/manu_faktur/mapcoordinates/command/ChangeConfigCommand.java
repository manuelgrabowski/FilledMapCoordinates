package net.manu_faktur.mapcoordinates.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.manu_faktur.mapcoordinates.FilledMapCoordinates;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.minecraft.server.command.CommandManager.*;
import static net.manu_faktur.mapcoordinates.util.ModConfig.*;

public class ChangeConfigCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, RegistrationEnvironment registrationEnvironment){
        Style style = Style.EMPTY.withColor(Formatting.GRAY).withItalic(true);

        dispatcher.register(literal(FilledMapCoordinates.MOD_ID)
                .then(literal("enabled")
                        .executes(context -> {
                            context.getSource().sendFeedback(() -> Text.literal("Mod is currently " + (getBooleanValue("enabled") ? "enabled" : "disabled")).setStyle(style), false);
                            return 1;
                        })
                        .then(argument("value", BoolArgumentType.bool())
                                .executes(context -> {
                                    final boolean value = BoolArgumentType.getBool(context,"value");
                                    setValue("enabled", value);
                                    context.getSource().sendFeedback(() -> Text.literal("Mod is now " + (getBooleanValue("enabled") ? "enabled" : "disabled")).setStyle(style), true);
                                    return 1;
                                })
                        )
                )
        );
    }
}
