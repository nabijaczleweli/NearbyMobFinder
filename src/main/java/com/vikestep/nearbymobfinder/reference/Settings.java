package com.vikestep.nearbymobfinder.reference;

import com.nabijaczleweli.nearbymobfinder.worldgen.WorldGenLiquidCrystal;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.config.Configuration;
import org.lwjgl.input.Keyboard;
import java.io.File;

public class Settings {
	public static Configuration config;

	public static boolean    showMobsAtAnyTimeOfDay;
	public static boolean    showMobsAtBed;
	public static KeyBinding checkMobs;

	public static void loadConfig(File configFile, Side side) {
		config = new Configuration(configFile);
		if(side == Side.CLIENT) {
			showMobsAtAnyTimeOfDay = config.get("Client-side", "showMobsAllTime", false).getBoolean();
			showMobsAtBed = config.get("Client-side", "showMobsAtBed", false).getBoolean();
		}
		WorldGenLiquidCrystal.setBaseGenerationLevel(config.get("World generation", "baseGenLiquidCrystalLvl", 30, "Base level of generation; 2..60; default: 30", 2, 60).getInt());
		WorldGenLiquidCrystal.setBigVeinProbability(config.get("World generation", "propGenLiquidCrystalBigVein", 300, String.format("Probability of generating a big vein (1/x); 0..%d; default: 300", Integer.MAX_VALUE), 0, Integer.MAX_VALUE).getInt());
		WorldGenLiquidCrystal.setOffLevelMax(config.get("World generation", "deviationGenLiquidCrystalMax", 3, "Maximal deviation of level of generation; 0..50; default: 3", 0, 50).getInt());
		WorldGenLiquidCrystal.setTreshold(config.get("World generation", "chunkGenLiquidCrystalTreshold", 300, "Amount of chunks of which generation will happen (1/x); 1..500; default: 300").getInt());
		if(config.hasChanged())
			config.save();
	}

	public static void createKeyBindings() {
		Settings.checkMobs = new KeyBinding("Check mobs preventing sleeping", Keyboard.KEY_M, "key.categories.misc");
	}
}
