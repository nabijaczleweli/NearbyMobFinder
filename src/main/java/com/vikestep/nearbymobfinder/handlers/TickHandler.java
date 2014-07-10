package com.vikestep.nearbymobfinder.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import java.util.List;

public class TickHandler {
	public static List<EntityMob> nearbyMobList           = null;
	public static EntityPlayer    playerAttemptingToSleep = null;

	@SubscribeEvent
	public void postChatUpdate(ClientTickEvent event) {
		if(event.phase != TickEvent.Phase.END)
			return;
		if(nearbyMobList != null) {
			for(EntityMob mobFound : nearbyMobList) {
				String CHAT_MESSAGE = mobFound.func_145748_c_().getFormattedText() + ": " + Math.floor(mobFound.posX) + ", " + Math.floor(mobFound.posY) + ", " + Math.floor(mobFound.posZ);
				ChatComponentText component = new ChatComponentText(CHAT_MESSAGE);
				playerAttemptingToSleep.addChatComponentMessage(component);
			}
			nearbyMobList = null;
			playerAttemptingToSleep = null;
		}
	}
}