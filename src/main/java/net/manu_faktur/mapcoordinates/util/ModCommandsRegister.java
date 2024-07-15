package net.manu_faktur.mapcoordinates.util;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.manu_faktur.mapcoordinates.command.ChangeConfigCommand;

public class ModCommandsRegister {
    public static void registerCommands(){
        CommandRegistrationCallback.EVENT.register(ChangeConfigCommand::register);
    }
}
