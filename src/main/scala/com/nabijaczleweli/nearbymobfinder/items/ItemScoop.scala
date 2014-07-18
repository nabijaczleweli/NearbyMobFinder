package com.nabijaczleweli.nearbymobfinder.items

import net.minecraft.item.{Item, ItemStack, ItemBucket}
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import com.nabijaczleweli.nearbymobfinder.creativetabs.CreativeTabNearbyMobFinder
import net.minecraft.util.{MovingObjectPosition, IIcon}
import net.minecraft.client.renderer.texture.IIconRegister
import com.vikestep.nearbymobfinder.reference.{Container, Reference}
import cpw.mods.fml.relauncher.{SideOnly, Side}
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import com.nabijaczleweli.nearbymobfinder.handlers.ScoopHandler
import com.nabijaczleweli.nearbymobfinder.render.ItemRendererScoop
import com.vikestep.nearbymobfinder.proxy.ClientProxy

class ItemScoop(val contains: Block) extends ItemBucket(contains) {
	var icon: IIcon = null

	setUnlocalizedName(s"scoop${if(contains == Blocks.air) "Empty" else contains.getUnlocalizedName substring 4}")
	setCreativeTab(CreativeTabNearbyMobFinder)
	setMaxStackSize(1)
	if(contains != Blocks.air)
	setContainerItem(Container.scoopEmpty)  // This requires empty scoop to be created before any others!

	ClientProxy.scoopRender enqueue this

	@SideOnly(Side.CLIENT)
	override def registerIcons(ir: IIconRegister) {
		icon = ir registerIcon Reference.MOD_ID + (if(contains == Blocks.air) ":emptyscoop" else ":fullscoop")
	}

	@SideOnly(Side.CLIENT)
	override def getIcon(stack: ItemStack, pass: Int) =
		icon

	override def getItemStackDisplayName(is: ItemStack) =
		if(contains == Blocks.air)
			"Empty scoop"
		else {
			val bi = Item getItemFromBlock contains
			s"Scoop with ${bi getItemStackDisplayName new ItemStack(bi)}"
		}

	override def onItemRightClick(is: ItemStack, world: World, player: EntityPlayer): ItemStack = {
		val mop = getMovingObjectPositionFromPlayer(world, player, contains == Blocks.air)
		if(mop == null)
			return is
		if(player.capabilities.isCreativeMode)
			is
		else
			mop.typeOfHit match {
				case MovingObjectPosition.MovingObjectType.MISS | MovingObjectPosition.MovingObjectType.ENTITY =>
					is
				case MovingObjectPosition.MovingObjectType.BLOCK =>
					if(contains == Blocks.air) {
						val resultItemStack = ScoopHandler.fillScoop(world, mop)
						if(resultItemStack == null)
							is
						else
							resultItemStack
					} else {
						val resultItemStack = ScoopHandler.emptyScoop(world, mop, is)
						if(resultItemStack == null)
							is
						else
							resultItemStack
					}
			}
	}

	override def getMaxDamage =
		0
}