package com.vikestep.nearbymobfinder;

import com.nabijaczleweli.nearbymobfinder.worldgen.WorldGenLiquidCrystal$;
import com.vikestep.nearbymobfinder.proxy.IProxy;
import com.vikestep.nearbymobfinder.reference.Container;
import com.vikestep.nearbymobfinder.reference.Reference;
import com.vikestep.nearbymobfinder.reference.Settings;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class NearbyMobFinder {
	@SuppressWarnings("unused")
	@Mod.Instance(Reference.MOD_ID)
	public static NearbyMobFinder instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_PATH, serverSide = Reference.SERVER_PROXY_PATH)
	public static IProxy proxy;

	@SuppressWarnings("unused")
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Container.createFluids();
		proxy.registerFluids();

		Container.createItemsAndBlocks();
		proxy.registerItemsAndBlocks();

		Settings.loadConfig(event.getSuggestedConfigurationFile(), event.getSide());
		proxy.registerEventHandlers();

		Settings.createKeyBindings();
		proxy.registerKeyBindings();
	}

	@SuppressWarnings("unused")
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator(WorldGenLiquidCrystal$.MODULE$, 1);

		proxy.registerRenderers();
		proxy.registerOreDict();
		proxy.registerRecipes();
	}
}
