package com.nabijaczleweli.nearbymobfinder.render

import net.minecraftforge.client.IItemRenderer
import net.minecraftforge.client.IItemRenderer.{ItemRendererHelper, ItemRenderType}
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper._
import net.minecraftforge.client.IItemRenderer.ItemRenderType._
import net.minecraft.item.ItemStack
import net.minecraft.client.renderer.{Tessellator, ItemRenderer}
import com.nabijaczleweli.nearbymobfinder.items.ItemScoop
import org.lwjgl.opengl.GL11._
import net.minecraft.client.renderer.entity.RenderItem

object ItemRendererScoop extends IItemRenderer {
	override def handleRenderType(item: ItemStack, `type`: ItemRenderType) =
		`type` != FIRST_PERSON_MAP

	override def shouldUseRenderHelper(`type`: ItemRenderType, item: ItemStack, helper: ItemRendererHelper) =
		helper != EQUIPPED_BLOCK && helper != BLOCK_3D && helper != INVENTORY_BLOCK

	override def renderItem(`type`: ItemRenderType, item: ItemStack, data: AnyRef*) {
		val icon = item.getItem.asInstanceOf[ItemScoop].icon
		`type` match {
			case INVENTORY =>
				glEnable(GL_ALPHA_TEST)
				RenderItem.getInstance.renderIcon(0, 0, icon, icon.getIconWidth, icon.getIconHeight)
				glDisable(GL_ALPHA_TEST)
			case ENTITY =>
				glTranslated(-.5, 0, 0)
				ItemRenderer.renderItemIn2D(Tessellator.instance, icon.getMaxU, icon.getMinV, icon.getMinU, icon.getMaxV, icon.getIconWidth, icon.getIconHeight, .065f)
				glTranslated(.5, 0, 0)
			case _ =>
				ItemRenderer.renderItemIn2D(Tessellator.instance, icon.getMaxU, icon.getMinV, icon.getMinU, icon.getMaxV, icon.getIconWidth, icon.getIconHeight, .065f)
		}
	}
}
