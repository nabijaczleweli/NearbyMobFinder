package com.vikestep.nearbymobfinder.proxy;

import com.vikestep.nearbymobfinder.handlers.PlayerBedEventHandler;
import com.vikestep.nearbymobfinder.handlers.TickHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy implements IProxy {
	@Override
	public void registerEventHandlers() {
		FMLCommonHandler.instance().bus().register(new TickHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerBedEventHandler());
	}

	@Override
	public void registerKeyBindings() {}
}
