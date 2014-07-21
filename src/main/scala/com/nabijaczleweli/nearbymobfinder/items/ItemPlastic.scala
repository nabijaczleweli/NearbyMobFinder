package com.nabijaczleweli.nearbymobfinder.items

import net.minecraft.item.{ItemStack, Item}
import com.nabijaczleweli.nearbymobfinder.creativetabs.CreativeTabNearbyMobFinder
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.util.{MathHelper, IIcon}
import net.minecraft.client.renderer.texture.IIconRegister
import com.vikestep.nearbymobfinder.reference.Reference
import net.minecraft.creativetab.CreativeTabs
import java.util

class ItemPlastic extends Item {
	setUnlocalizedName("plastic")
	setCreativeTab(CreativeTabNearbyMobFinder)
	setHasSubtypes(true)

	override def getMaxDamage =
		0

	@SideOnly(Side.CLIENT)
	override def getIconFromDamage(idx: Int) =
		ItemPlastic icons MathHelper.clamp_int(idx, 0, ItemPlastic.icons.length - 1)

	@SideOnly(Side.CLIENT)
	override def registerIcons(ir: IIconRegister) {
		for(i <- 0 until ItemPlastic.icons.length)
			ItemPlastic icons i = ir registerIcon Reference.MOD_ID + ":" + ItemPlastic.subIconNames(i)
	}

	override def getItemStackDisplayName(is: ItemStack) =
		ItemPlastic subDisplayName MathHelper.clamp_int(is.getItemDamage, 0, ItemPlastic.subDisplayName.length)

	@SideOnly(Side.CLIENT)
	override def getSubItems(item: Item, tab: CreativeTabs, list: util.List[_]) {
		if(item.isInstanceOf[ItemPlastic])
			for(i <- 0 until ItemPlastic.icons.length)
				list.asInstanceOf[util.List[ItemStack]] add new ItemStack(item, 1, i)
	}
}

private object ItemPlastic {
	val subIconNames = Array[String]("monomer", "polymer", "plastic")
	val subDisplayName = Array[String]("Monomer", "Polymer", "Plastic")
	@SideOnly(Side.CLIENT)
	val icons = new Array[IIcon](subIconNames.length)
}
