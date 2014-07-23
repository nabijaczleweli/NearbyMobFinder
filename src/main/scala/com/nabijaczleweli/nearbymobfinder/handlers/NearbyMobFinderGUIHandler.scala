package com.nabijaczleweli.nearbymobfinder.handlers

import cpw.mods.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import com.nabijaczleweli.nearbymobfinder.render.{GUIEntityMobScannerConfig, GUIEntityMobScanner}

object NearbyMobFinderGUIHandler extends IGuiHandler {
	override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int) =
		ID match {
			case GUIEntityMobScanner.ID =>
				GUIEntityMobScanner
			case GUIEntityMobScannerConfig.ID =>
				GUIEntityMobScannerConfig
			case _ =>
				null
		}

	override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int) =
		ID match {
			case _ =>
				null
	}
}
