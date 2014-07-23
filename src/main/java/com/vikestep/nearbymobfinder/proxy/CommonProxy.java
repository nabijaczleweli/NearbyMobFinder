package com.vikestep.nearbymobfinder.proxy;

import com.nabijaczleweli.nearbymobfinder.handlers.TickHandler;
import com.nabijaczleweli.nearbymobfinder.items.ItemPCB;
import com.nabijaczleweli.nearbymobfinder.items.ItemPlastic;
import com.nabijaczleweli.nearbymobfinder.items.ItemScoop;
import com.vikestep.nearbymobfinder.handlers.CraftingEventsHandler;
import com.vikestep.nearbymobfinder.handlers.PlayerBedEventHandler;
import com.vikestep.nearbymobfinder.reference.Container;
import com.vikestep.nearbymobfinder.reference.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CommonProxy implements IProxy {
	@Override
	public void registerEventHandlers() {
		FMLCommonHandler.instance().bus().register(new TickHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerBedEventHandler());
		FMLCommonHandler.instance().bus().register(new CraftingEventsHandler());
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
		defaultRegisterItem(Container.plastic);

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

	@Override
	public final void registerOreDict() {
		// Monomer, Polymer, Plastic
		ItemStack is = new ItemStack(Container.plastic);
		for(int i = ItemPlastic.monomerDamage(); i < ItemPlastic.polymerDamage(); ++i){
			is.setItemDamage(i);
			OreDictionary.registerOre(ItemPlastic.oreDictName(i), is);
		}
	}

	@Override
	public void registerRecipes() {
		GameRegistry.addSmelting(Container.scoopLiquidCrystal, new ItemStack(Container.plastic), 5);

		ItemStack LCD = new ItemStack(Container.pcb, 1, ItemPCB.LCDDamage());
		ItemStack emptyPCB = new ItemStack(Container.pcb, 1, ItemPCB.emptyPCBDamage());
		ItemStack plastic = new ItemStack(Container.plastic, 1, ItemScoop.plasticDamage());

		GameRegistry.addRecipe(new ShapelessOreRecipe(plastic, "materialPolymer", "materialPolymer", "materialPolymer", "materialPolymer"));

		GameRegistry.addRecipe(new ShapedOreRecipe(LCD, "PPP", "PLP", "PGP", 'P', "materialPlastic", 'L', new ItemStack(Container.scoopLiquidCrystal), 'G', "nuggetGold"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Container.pcb, 1, ItemPCB.PCBLCDDamage()), " L ", "GPG", 'P', new ItemStack(Container.pcb, 1, ItemPCB.emptyPCBDamage()), 'L', LCD, 'G', "nuggetGold"));
		GameRegistry.addRecipe(new ShapedOreRecipe(emptyPCB, " G ", "PNP", " Gp", 'P', plastic, 'G', "paneGlass", 'N', "nuggetGold", 'p', Blocks.piston));
		GameRegistry.addRecipe(new ShapedOreRecipe(emptyPCB, " G ", "PNP", " Gp", 'P', plastic, 'G', "paneGlass", 'N', "nuggetGold", 'p', Blocks.sticky_piston));
	}
}
