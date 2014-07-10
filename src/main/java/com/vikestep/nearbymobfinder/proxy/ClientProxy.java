package com.vikestep.nearbymobfinder.proxy;

import com.vikestep.nearbymobfinder.handlers.PlayerBedEventHandler;
import com.vikestep.nearbymobfinder.reference.Settings;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {
	@Override
	public void registerKeyBindings() {
		super.registerKeyBindings();
		FMLCommonHandler.instance().bus().register(new PlayerBedEventHandler());
		ClientRegistry.registerKeyBinding(Settings.checkMobs);
	}
}
