package com.vikestep.nearbymobfinder.proxy;

import com.nabijaczleweli.nearbymobfinder.handlers.TickHandler;
import com.vikestep.nearbymobfinder.handlers.PlayerBedEventHandler;
import com.vikestep.nearbymobfinder.reference.Container;
import com.vikestep.nearbymobfinder.reference.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class CommonProxy implements IProxy {
	@Override
	public void registerEventHandlers() {
		FMLCommonHandler.instance().bus().register(new TickHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerBedEventHandler());
	}

	@Override
	public void registerKeyBindings() {}

	protected static void defaultRegisterItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName(), Reference.MOD_ID);
	}

	@Override
	final public void registerItemsAndBlocks() {
		defaultRegisterItem(Container.mobScanner);
		defaultRegisterItem(Container.pcb);
		defaultRegisterItem(Container.scoopEmpty);
		defaultRegisterItem(Container.scoopLiquidCrystal);

		GameRegistry.registerBlock(Container.liquidCrystalB, Container.liquidCrystalB.getUnlocalizedName());

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(Container.liquidCrystalF.getName(), FluidContainerRegistry.BUCKET_VOLUME / 7), new ItemStack(Container.scoopLiquidCrystal), new ItemStack(Container.scoopEmpty));
	}

	/** Needs to be called before {@link #registerItemsAndBlocks}. */
	@Override
	final public void registerFluids() {
		FluidRegistry.registerFluid(Container.liquidCrystalF);
	}

	@Override
	public void registerRenderers() {}
}
