package com.vikestep.nearbymobfinder;

import com.nabijaczleweli.nearbymobfinder.blocks.BlockLiquidCrystalFluid;
import com.nabijaczleweli.nearbymobfinder.items.ItemEntityMobScanner;
import com.nabijaczleweli.nearbymobfinder.items.ItemPCB;
import com.vikestep.nearbymobfinder.proxy.IProxy;
import com.vikestep.nearbymobfinder.reference.Container;
import com.vikestep.nearbymobfinder.reference.Reference;
import com.vikestep.nearbymobfinder.reference.Settings;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import org.lwjgl.input.Keyboard;

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
		Container.liquidCrystalF = new Fluid("liquidcrystal").setLuminosity(13).setViscosity(Integer.MAX_VALUE);

		proxy.registerFluids();

		Container.mobScanner = new ItemEntityMobScanner();
		Container.pcb = new ItemPCB();

		Container.liquidCrystalB = new BlockLiquidCrystalFluid();

		proxy.registerItemsAndBlocks();

		Settings.config = new Configuration(event.getSuggestedConfigurationFile());
		Settings.showMobsAtAnyTimeOfDay = Settings.config.get("Client-side", "showMobsAllTime", false).getBoolean(false);
		if(Settings.config.hasChanged())
			Settings.config.save();

		proxy.registerEventHandlers();

		Settings.checkMobs = new KeyBinding("Check mobs preventing sleeping", Keyboard.KEY_M, "key.categories.misc");

		proxy.registerKeyBindings();
	}

	@SuppressWarnings("unused")
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
	}
}
