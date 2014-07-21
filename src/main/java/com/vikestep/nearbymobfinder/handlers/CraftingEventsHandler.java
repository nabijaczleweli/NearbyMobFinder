package com.vikestep.nearbymobfinder.handlers;

import com.nabijaczleweli.nearbymobfinder.items.ItemScoop;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
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
}
