package me.modmuss50.svw;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config
{

    public static Configuration config;
    public static String CATEGORY_IDS = "IDs";
    public static String CATEGORY_TWEAKS = "TWEAKS";

    public static int dimID = 43;
    public static boolean darkSky = false;
    public static boolean eternalDay = false;
    public static boolean canMobsSpawn = false;

    public static void load(File configFile)
    {
        config = new Configuration(configFile);
        config.load();

        dimID = config.get(CATEGORY_IDS, "dim_id", dimID, "This is the id of the dimension in the mod, this should be unique to Simple Void World").getInt();

        darkSky = config.get(CATEGORY_TWEAKS, "dark_fog", darkSky, "When true the sky and fog color are black. This creates a seamless skybox").getBoolean();
        eternalDay = config.get(CATEGORY_TWEAKS, "time", eternalDay, "-1 = always night; 0 = normal; 1 = always day;").getBoolean();
        canMobsSpawn = config.get(CATEGORY_TWEAKS, "mobs_spawning", canMobsSpawn, "Can mobs spawn in void or not").getBoolean();

        config.save();
    }

}
