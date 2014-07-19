package com.vikestep.nearbymobfinder.reference;

import com.nabijaczleweli.nearbymobfinder.blocks.BlockLiquidCrystalFluid;
import com.nabijaczleweli.nearbymobfinder.items.ItemEntityMobScanner;
import com.nabijaczleweli.nearbymobfinder.items.ItemPCB;
import com.nabijaczleweli.nearbymobfinder.items.ItemScoop;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.Fluid;

public class Container {
	public static ItemEntityMobScanner mobScanner;
	public static ItemPCB              pcb;
	public static ItemScoop            scoopEmpty;
	public static ItemScoop            scoopLiquidCrystal;

	public static Fluid liquidCrystalF;

	public static BlockLiquidCrystalFluid liquidCrystalB;


	public static void createFluids() {
		liquidCrystalF = new Fluid("liquidcrystal").setLuminosity(13).setViscosity(Integer.MAX_VALUE);
	}

	public static void createItemsAndBlocks() {
		mobScanner = new ItemEntityMobScanner();
		pcb = new ItemPCB();
		scoopEmpty = new ItemScoop(Blocks.air);

		liquidCrystalB = new BlockLiquidCrystalFluid();

		scoopLiquidCrystal = new ItemScoop(Container.liquidCrystalB);
	}
}
