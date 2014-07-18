package com.vikestep.nearbymobfinder.proxy;

import com.nabijaczleweli.nearbymobfinder.items.ItemScoop;
import com.nabijaczleweli.nearbymobfinder.render.ItemRendererScoop$;
import com.vikestep.nearbymobfinder.handlers.PlayerBedEventHandler;
import com.vikestep.nearbymobfinder.reference.Settings;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.client.MinecraftForgeClient;
import scala.collection.mutable.Queue;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {
	public static Queue<ItemScoop> scoopRender = new Queue<ItemScoop>();

	@Override
	public void registerKeyBindings() {
		super.registerKeyBindings();
		FMLCommonHandler.instance().bus().register(new PlayerBedEventHandler());
		ClientRegistry.registerKeyBinding(Settings.checkMobs);
	}

	@Override
	public void registerRenderers() {
		super.registerRenderers();

		while(scoopRender.size() > 0)
			MinecraftForgeClient.registerItemRenderer(scoopRender.dequeue(), ItemRendererScoop$.MODULE$);
	}
}
