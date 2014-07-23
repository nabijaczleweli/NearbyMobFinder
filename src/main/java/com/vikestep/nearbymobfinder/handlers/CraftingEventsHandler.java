package com.vikestep.nearbymobfinder.handlers;

import com.nabijaczleweli.nearbymobfinder.items.ItemPCB;
import com.nabijaczleweli.nearbymobfinder.items.ItemScoop;
import com.vikestep.nearbymobfinder.reference.Container;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class CraftingEventsHandler {
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onPlayerCraftWithScoopEvent(PlayerEvent.ItemCraftedEvent event) {
		for(int i = 0; i < event.craftMatrix.getSizeInventory(); ++i) {
			ItemStack is = event.craftMatrix.getStackInSlot(i);
			if(is == null)
				continue;
			if(is.getItem() instanceof ItemScoop) {
				is.func_150996_a(is.getItem().getContainerItem());
				++is.stackSize;
				event.craftMatrix.setInventorySlotContents(i, is);
			}
		}
	}

	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onPlayerCraftEmptyPCBEvent(PlayerEvent.ItemCraftedEvent event) {
		if(!(event.crafting.getItem().getUnlocalizedName().equals(Container.pcb.getUnlocalizedName()) && event.crafting.getItemDamage() == ItemPCB.emptyPCBDamage()))
			return;
		for(int i = 0; i < event.craftMatrix.getSizeInventory(); ++i) {
			ItemStack is = event.craftMatrix.getStackInSlot(i);
			if(is == null || !(is.getItem() instanceof ItemBlock))
				continue;
			if(Block.getBlockFromItem(is.getItem()) instanceof BlockPistonBase) {
				++is.stackSize;
				event.craftMatrix.setInventorySlotContents(i, is);
			}
		}
	}
}
