package com.nabijaczleweli.nearbymobfinder.items

import net.minecraft.item.Item
import com.nabijaczleweli.nearbymobfinder.creativetabs.CreativeTabNearbyMobFinder
import com.vikestep.nearbymobfinder.reference.Reference

class ItemEntityMobScanner extends Item {
	setUnlocalizedName("entityMobScanner")
	setCreativeTab(CreativeTabNearbyMobFinder)
	setTextureName(Reference.MOD_ID.toLowerCase + ":entitymobscanner")
	setNoRepair()
	setMaxStackSize(1)

	override def getMaxDamage =
		1000
}
