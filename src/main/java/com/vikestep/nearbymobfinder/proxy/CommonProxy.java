package com.vikestep.nearbymobfinder.proxy;

import com.nabijaczleweli.nearbymobfinder.handlers.TickHandler;
import com.vikestep.nearbymobfinder.handlers.PlayerBedEventHandler;
import com.vikestep.nearbymobfinder.reference.Container;
import com.vikestep.nearbymobfinder.reference.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;

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
		GameRegistry.registerItem(Container.pcb, Container.pcb.getUnlocalizedName(), Reference.MOD_ID);

		GameRegistry.registerBlock(Container.liquidCrystalB, Container.liquidCrystalB.getUnlocalizedName());
	}

	/** Needs to be called before {@link #registerItemsAndBlocks}. */
	@Override
	final public void registerFluids() {
		FluidRegistry.registerFluid(Container.liquidCrystalF);
	}
}
