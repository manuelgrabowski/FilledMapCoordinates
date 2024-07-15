package net.manu_faktur.mapcoordinates;

import net.fabricmc.api.ModInitializer;

import net.manu_faktur.mapcoordinates.util.ModCommandsRegister;
import net.manu_faktur.mapcoordinates.util.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilledMapCoordinates implements ModInitializer {
	public static final String MOD_ID = "mapcoordinates";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModConfig.registerConfigs();
		ModCommandsRegister.registerCommands();
	}
}