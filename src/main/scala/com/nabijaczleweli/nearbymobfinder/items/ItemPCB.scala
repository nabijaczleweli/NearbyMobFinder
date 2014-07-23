package com.nabijaczleweli.nearbymobfinder.items

import net.minecraft.item.{ItemStack, Item}
import com.nabijaczleweli.nearbymobfinder.creativetabs.CreativeTabNearbyMobFinder
import net.minecraft.util.{MathHelper, IIcon}
import net.minecraft.creativetab.CreativeTabs
import java.util
import cpw.mods.fml.relauncher.{SideOnly, Side}
import net.minecraft.client.renderer.texture.IIconRegister
import com.vikestep.nearbymobfinder.reference.Reference

class ItemPCB extends Item {
	setUnlocalizedName("PCB")
	setCreativeTab(CreativeTabNearbyMobFinder)
	setHasSubtypes(true)

	override def getMaxDamage =
		0

	@SideOnly(Side.CLIENT)
	override def getIconFromDamage(idx: Int) =
		ItemPCB icons MathHelper.clamp_int(idx, 0, ItemPCB.icons.length - 1)

	@SideOnly(Side.CLIENT)
	override def registerIcons(ir: IIconRegister) {
		for(i <- 0 until ItemPCB.icons.length)
			ItemPCB icons i = ir registerIcon Reference.MOD_ID + ":" + String.format(ItemPCB.subIconNames(i), "pcb_")
	}

	override def getItemStackDisplayName(is: ItemStack) =
		ItemPCB subDisplayName MathHelper.clamp_int(is.getItemDamage, 0, 15) format "PCB"

	@SideOnly(Side.CLIENT)
	override def getSubItems(item: Item, tab: CreativeTabs, list: util.List[_]) {
		if(item.isInstanceOf[ItemPCB])
			for(i <- 0 until ItemPCB.icons.length)
				list.asInstanceOf[util.List[ItemStack]] add new ItemStack(item, 1, i)
	}
}

object ItemPCB {
	private val subIconNames = Array[String]("%selements", "%snoelements", "%slcd", "lcd")
	private val subDisplayName = Array[String]("%s with elements", "%s without elements", "%s with LCD", "LCD")
	@SideOnly(Side.CLIENT)
	private val icons = new Array[IIcon](subIconNames.length)

	val fullPCBDamage  = 0
	val emptyPCBDamage = 1
	val PCBLCDDamage   = 2
	val LCDDamage      = 3
}
