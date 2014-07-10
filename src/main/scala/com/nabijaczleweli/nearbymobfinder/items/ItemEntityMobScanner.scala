package com.nabijaczleweli.nearbymobfinder.items

import net.minecraft.item.Item
import com.nabijaczleweli.nearbymobfinder.creativetabs.CreativeTabNearbyMobFinder
import com.vikestep.nearbymobfinder.reference.Reference

class ItemEntityMobScanner private (`_ _ _ _ _ _ _ _ _`: () => Unit) extends Item {
	def this() {
		this(() => {})
		setUnlocalizedName("entityMobScanner")
		setCreativeTab(CreativeTabNearbyMobFinder)
		setTextureName(Reference.MOD_ID.toLowerCase + ":entitymobscanner")
		setNoRepair()
		setMaxStackSize(1)
		setMaxDamage(1000)
	}
}
