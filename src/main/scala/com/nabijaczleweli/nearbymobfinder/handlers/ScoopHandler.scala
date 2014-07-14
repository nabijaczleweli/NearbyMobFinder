package com.nabijaczleweli.nearbymobfinder.handlers

import com.vikestep.nearbymobfinder.reference.Container
import cpw.mods.fml.common.eventhandler.Event
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.MovingObjectPosition
import net.minecraft.world.World
import net.minecraftforge.event.entity.player.FillBucketEvent
import scala.collection.mutable
import net.minecraft.block.Block
import com.nabijaczleweli.nearbymobfinder.items.ItemScoop
import net.minecraftforge.fluids.BlockFluidBase

object ScoopHandler {
	var scoops = mutable.HashMap[Block, Item] (
		Blocks.air -> Container.scoopEmpty,
		Container.liquidCrystalB -> Container.scoopLiquidCrystal
	)

	def fillScoop(world: World, pos: MovingObjectPosition): ItemStack = {
		var posModifX = 0
		var posModifY = 0
		var posModifZ = 0
		var block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ)
		var scoop = scoops get block

		if(scoop.isEmpty)
			pos.sideHit match {
				case -1 =>
					return null
				case 0 =>
					block = world.getBlock(pos.blockX, pos.blockY - 1, pos.blockZ)
					scoop = scoops get block
					if(scoop.isEmpty)
						return null
					else
						posModifY = -1
				case 1 =>
					block = world.getBlock(pos.blockX, pos.blockY + 1, pos.blockZ)
					scoop = scoops get block
					if(scoop.isEmpty)
						return null
					else
						posModifY = 1
				case 2 =>
					block = world.getBlock(pos.blockX + 1, pos.blockY, pos.blockZ)
					scoop = scoops get block
					if(scoop.isEmpty)
						return null
					else
						posModifX = 1
				case 3 =>
					block = world.getBlock(pos.blockX - 1, pos.blockY, pos.blockZ)
					scoop = scoops get block
					if(scoop.isEmpty)
						return null
					else
						posModifX = -1
				case 4 =>
					block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ - 1)
					scoop = scoops get block
					if(scoop.isEmpty)
						return null
					else
						posModifZ = -1
				case 5 =>
					block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ + 1)
					scoop = scoops get block
					if(scoop.isEmpty)
						return null
					else
						posModifZ = 1
			}

		val metadata = world.getBlockMetadata(pos.blockX + posModifX, pos.blockY + posModifY, pos.blockZ + posModifZ)
		if(metadata == 0)
			world.setBlockToAir(pos.blockX + posModifX, pos.blockY + posModifY, pos.blockZ + posModifZ)
		else
			world.setBlockMetadataWithNotify(pos.blockX + posModifX, pos.blockY + posModifY, pos.blockZ + posModifZ, metadata - 1, 1 | 2)
		new ItemStack(scoop.get)
	}

	def emptyScoop(world: World, pos: MovingObjectPosition, scoop: ItemStack): ItemStack = {
		var posModifX = 0
		var posModifY = 0
		var posModifZ = 0
		var block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ)
		val scoopItem = scoop.getItem.asInstanceOf[ItemScoop]
		val scoopContains = scoopItem.contains.asInstanceOf[BlockFluidBase]

		if(block != scoopContains)
			if(block.getMaterial.isLiquid) {
				if(scoopContains.canDisplace(world, pos.blockX, pos.blockY, pos.blockZ)) {
					if(scoopContains.displaceIfPossible(world, pos.blockX, pos.blockY, pos.blockZ))
						return new ItemStack(scoopItem.getContainerItem)
					else
						return null
				} else
					return null
			}
		if(!block.getMaterial.isLiquid) {
			pos.sideHit match {
				case -1 =>
					return null
				case 0 =>
					block = world.getBlock(pos.blockX, pos.blockY - 1, pos.blockZ)
					posModifY = -1
				case 1 =>
					block = world.getBlock(pos.blockX, pos.blockY + 1, pos.blockZ)
						posModifY = 1
				case 2 =>
					block = world.getBlock(pos.blockX + 1, pos.blockY, pos.blockZ)
					posModifX = 1
				case 3 =>
					block = world.getBlock(pos.blockX - 1, pos.blockY, pos.blockZ)
					posModifX = -1
				case 4 =>
					block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ - 1)
						posModifZ = -1
				case 5 =>
					block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ + 1)
					posModifZ = 1
				}
			if(!block.isAir(world, pos.blockX + posModifX, pos.blockY + posModifY, pos.blockZ + posModifZ))
				null
			else {
				world.setBlock(pos.blockX + posModifX, pos.blockY + posModifY, pos.blockZ + posModifZ, scoopContains)
				new ItemStack(scoopItem.getContainerItem)
			}
		} else {
			val metadata = world.getBlockMetadata(pos.blockX + posModifX, pos.blockY + posModifY, pos.blockZ + posModifZ)
			world.setBlockMetadataWithNotify(pos.blockX + posModifX, pos.blockY + posModifY, pos.blockZ + posModifZ, metadata + 1, 1 | 2)
			new ItemStack(scoopItem.getContainerItem)
		}
	}
}
