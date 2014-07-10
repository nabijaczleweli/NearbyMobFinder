package com.nabijaczleweli.nearbymobfinder.handlers

import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent
import cpw.mods.fml.common.gameevent.TickEvent
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.monster.EntityMob
import scala.collection.mutable
import net.minecraft.util.ChatComponentText

class TickHandler {
	import TickHandler._

	@SubscribeEvent
	def postChatUpdate(event: ClientTickEvent) {
		if(event.phase != TickEvent.Phase.END)
			return
		while(nearbyMobList.size > 0) {
			val mobFound = nearbyMobList remove 0
			playerAttemptingToSleep addChatComponentMessage new ChatComponentText(s"${mobFound.func_145748_c_.getFormattedText}: ${Math floor mobFound.posX}, ${Math floor mobFound.posY}, ${Math floor mobFound.posZ}")
		}
	}
}

object TickHandler {
	var nearbyMobList = new mutable.ListBuffer[EntityMob]
	var playerAttemptingToSleep: EntityPlayer = null

	def playerAttemptingToSleep(player: EntityPlayer): Unit =
		playerAttemptingToSleep = player
}
