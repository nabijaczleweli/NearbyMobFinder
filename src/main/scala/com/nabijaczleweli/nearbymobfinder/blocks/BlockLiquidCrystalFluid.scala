package com.nabijaczleweli.nearbymobfinder.blocks

import net.minecraftforge.fluids.BlockFluidClassic
import com.vikestep.nearbymobfinder.reference.{Reference, Container}
import net.minecraft.block.material.{MapColor, MaterialLiquid}
import com.nabijaczleweli.nearbymobfinder.creativetabs.CreativeTabNearbyMobFinder
import net.minecraft.util.IIcon
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.world.{World, IBlockAccess}

class BlockLiquidCrystalFluid private(`_ _ _ _ _ _ _ _ _`: () => Unit) extends BlockFluidClassic(Container.liquidCrystalF, new MaterialLiquid(MapColor.waterColor)) {
	def this() {
		this(() => {})
		setCreativeTab(CreativeTabNearbyMobFinder)
	}

	override def getIcon(side: Int, meta: Int) =
		if(side == 0 || side == 1)
			BlockLiquidCrystalFluid.stillIcon
		else
			BlockLiquidCrystalFluid.flowingIcon

	override def registerBlockIcons(register : IIconRegister) {
		BlockLiquidCrystalFluid.stillIcon = register registerIcon Reference.MOD_ID.toLowerCase + ":liquidcrystal_still"
		BlockLiquidCrystalFluid.flowingIcon = register registerIcon Reference.MOD_ID.toLowerCase + ":liquidcrystal_flow"
	}

	override def canDisplace(world: IBlockAccess, x: Int, y: Int, z: Int) =
		if(world.getBlock(x, y, z).getMaterial.isLiquid)
			false
		else
			super.canDisplace(world, x, y, z)

	override def displaceIfPossible(world: World, x: Int, y: Int, z: Int) =
		if(world.getBlock(x, y, z).getMaterial.isLiquid)
			false
		else
			super.displaceIfPossible(world, x, y, z)

	override def getUnlocalizedName =
		"tile.liquidcrystal"
}

private object BlockLiquidCrystalFluid {
	@SideOnly(Side.CLIENT)
	var stillIcon: IIcon = null
	@SideOnly(Side.CLIENT)
	var flowingIcon: IIcon = null
}
