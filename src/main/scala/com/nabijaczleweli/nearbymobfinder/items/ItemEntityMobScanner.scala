package com.nabijaczleweli.nearbymobfinder.items

import net.minecraft.item.{ItemStack, Item}
import com.nabijaczleweli.nearbymobfinder.creativetabs.CreativeTabNearbyMobFinder
import com.vikestep.nearbymobfinder.reference.Reference
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import com.nabijaczleweli.nearbymobfinder.render.{GUIEntityMobScannerConfig, GUIEntityMobScanner}
import net.minecraft.client.Minecraft
import net.minecraft.nbt.NBTTagCompound

class ItemEntityMobScanner extends Item {
	setUnlocalizedName("entityMobScanner")
	setCreativeTab(CreativeTabNearbyMobFinder)
	setTextureName(Reference.MOD_ID.toLowerCase + ":entitymobscanner")
	setNoRepair()
	setMaxStackSize(1)

	override def getMaxDamage =
		1000

	override def onItemRightClick(is: ItemStack, world: World, player: EntityPlayer) = {
		if(!is.hasTagCompound)
			ItemEntityMobScanner createDefaultNBT is
		if(player.isSneaking) {
			player.openGui(Reference.MOD_ID, GUIEntityMobScannerConfig.ID, world, -1, -1, -1)
		} else
			player.openGui(Reference.MOD_ID, GUIEntityMobScanner.ID, world, -1, -1, -1)
		is
	}

	override def getShareTag =
		true
}

object ItemEntityMobScanner {
	def createDefaultNBT(is: ItemStack) {
		val tag = new NBTTagCompound
		tag.setDouble("rangeHorizontal", 8D)
		tag.setDouble("rangeVertical", 5D)
		is setTagCompound tag
	}
}
