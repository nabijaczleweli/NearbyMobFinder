package com.vikestep.nearbymobfinder.proxy;

import com.nabijaczleweli.nearbymobfinder.handlers.TickHandler;
import com.vikestep.nearbymobfinder.handlers.PlayerBedEventHandler;
import com.vikestep.nearbymobfinder.reference.Container;
import com.vikestep.nearbymobfinder.reference.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy implements IProxy {
	@Override
	public void registerEventHandlers() {
		FMLCommonHandler.instance().bus().register(new TickHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerBedEventHandler());
	}

	@Override
	public void registerKeyBindings() {}

	@Override
	final public void registerItemsAndBlocks() {
		GameRegistry.registerItem(Container.mobScanner, Container.mobScanner.getUnlocalizedName(), Reference.MOD_ID);
	}
}
