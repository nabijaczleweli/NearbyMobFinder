package com.nabijaczleweli.nearbymobfinder.render

import net.minecraft.client.gui.{GuiButton, GuiScreen}
import net.minecraft.util.{AxisAlignedBB, ResourceLocation}
import com.vikestep.nearbymobfinder.reference.Reference
import org.lwjgl.opengl.GL11._
import cpw.mods.fml.client.config.GuiButtonExt
import net.minecraft.entity.monster.EntityMob
import net.minecraft.client.Minecraft
import java.util
import scala.collection.JavaConversions._
import com.nabijaczleweli.nearbymobfinder.handlers.TickHandler

object GUIEntityMobScanner extends GuiScreen {
	final val ID = 0

	private final val resourceGUI = new ResourceLocation(Reference.MOD_ID.toLowerCase, "textures/gui/entityMobScanner.png")
	private final val textureWidth  = 248
	private final val textureHeight = 166
	private final lazy val textureStartX = (this.width - textureWidth) / 2
	private final lazy val textureStartY = (this.height - textureHeight) / 2

	private final var horizontalPixelsPerBlock = -0D
	private final var verticalPixelsPerBlock   = -0D
	private final var rangeHorizontal = -0D
	private final var rangeVertical   = -0D
	private final var mobs: List[EntityMob] = Nil


	def prepAndRead() {
		val tag = Minecraft.getMinecraft.thePlayer.getCurrentEquippedItem.getTagCompound
		rangeHorizontal = tag getDouble "rangeHorizontal"
		rangeVertical   = tag getDouble "rangeVertical"

		horizontalPixelsPerBlock = (textureWidth  / 2) / rangeHorizontal
		verticalPixelsPerBlock   = (textureHeight / 2) / rangeVertical
	}

	override def drawScreen(mouseX: Int, mouseY: Int, f: Float) {
		prepAndRead()
		drawDefaultBackground()
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F)

		mc.renderEngine bindTexture resourceGUI
		drawTexturedModalRect(textureStartX, textureStartY, 0, 0, textureWidth, textureHeight)

		super.drawScreen(mouseX, mouseY, f)
	}

	override def updateScreen() {
		buttonList.clear()

		val thePlayer = Minecraft.getMinecraft.thePlayer
		val list = thePlayer.worldObj.getEntitiesWithinAABB(classOf[EntityMob], AxisAlignedBB.getBoundingBox(thePlayer.posX - rangeHorizontal, thePlayer.posY - rangeVertical, thePlayer.posZ - rangeHorizontal, thePlayer.posX + rangeHorizontal, thePlayer.posY + rangeVertical, thePlayer.posZ + rangeHorizontal))
		var id = 0
		for(mob <- list.asInstanceOf[util.List[EntityMob]]) {
			buttonList.asInstanceOf[util.List[GuiButton]] add new GuiButtonExt({id += 1; id - 1},
			                                                                   (textureStartX + (textureWidth / 2) + ((mob.posX - thePlayer.posX) * horizontalPixelsPerBlock) - (5D / 2D)).toInt,
			                                                                   (textureStartY + (textureHeight / 2) + ((mob.posZ - thePlayer.posZ) * verticalPixelsPerBlock) - (5D / 2D)).toInt, 5, 5, "")
			mobs :+= mob
		}
	}

	override def doesGuiPauseGame() =
		false

	override def mouseClicked(mouseX: Int, mouseY: Int, button: Int) {
		if(button == 0)
			for(button <- buttonList.asInstanceOf[util.List[GuiButton]] if button.mousePressed(Minecraft.getMinecraft, mouseX, mouseY)) {
				TickHandler.playerAttemptingToSleep = Minecraft.getMinecraft.thePlayer
				TickHandler.nearbyMobList :+= mobs(button.id)
			}
	}
}
