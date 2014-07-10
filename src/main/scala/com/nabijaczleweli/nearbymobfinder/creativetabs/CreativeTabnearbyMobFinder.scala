package com.nabijaczleweli.nearbymobfinder.creativetabs

import net.minecraft.creativetab.CreativeTabs
import com.vikestep.nearbymobfinder.reference.{Container, Reference}

object CreativeTabNearbyMobFinder extends CreativeTabs(Reference.MOD_NAME) {
	override def getTabIconItem =
		Container.mobScanner

	override def getTranslatedTabLabel =
		getTabLabel
}
