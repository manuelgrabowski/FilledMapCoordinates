package net.manu_faktur.mapcoordinates.util;

import net.fabricmc.loader.api.FabricLoader;
import net.manu_faktur.mapcoordinates.FilledMapCoordinates;
import org.simpleyaml.configuration.comments.format.YamlCommentFormat;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModConfig {

    public static void registerConfigs() {
        Path path = (FabricLoader.getInstance().getConfigDir());
        Path path2 = path.resolve(Paths.get("FilledMapCoordinates"));
        final YamlFile config = new YamlFile((path2.resolve( "config.yml" ).toFile()).getAbsolutePath());
        try {
            if (!config.exists()) {
                config.createNewFile();
                FilledMapCoordinates.LOGGER.info("[FilledMapCoordinates]: New file has been created: {}", (path2.resolve("config.yml").toFile()).getPath());
            } else {
                FilledMapCoordinates.LOGGER.info("[FilledMapCoordinates]:{} already exists, loading configurations...", (path2.resolve("config.yml").toFile()).getPath());
            }
            config.loadWithComments();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        config.addDefault("enabled", true);

        config.setCommentFormat(YamlCommentFormat.PRETTY);
        config.setComment("enabled", "If enabled, the mod stores coordinate data on the map item when creating a new filled map");

        try {
            config.save();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean getBooleanValue(String key){
        Path path = (FabricLoader.getInstance().getConfigDir());
        Path path2 = path.resolve(Paths.get("FilledMapCoordinates"));
        final YamlFile config = new YamlFile((path2.resolve( "config.yml" ).toFile()).getAbsolutePath());
        try {
            config.loadWithComments();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        return config.getBoolean(key);
    }

    public static String getStringValue(String key){
        Path path = (FabricLoader.getInstance().getConfigDir());
        Path path2 = path.resolve(Paths.get("FilledMapCoordinates"));
        final YamlFile config = new YamlFile((path2.resolve( "config.yml" ).toFile()).getAbsolutePath());
        try {
            config.loadWithComments();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        return config.getString(key);
    }

    public static void setValue(String key, Object newValue){
        Path path = (FabricLoader.getInstance().getConfigDir());
        Path path2 = path.resolve(Paths.get("FilledMapCoordinates"));
        final YamlFile config = new YamlFile((path2.resolve( "config.yml" ).toFile()).getAbsolutePath());

        try {
            config.loadWithComments();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        config.set(key, newValue);

        try {
            config.save();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
