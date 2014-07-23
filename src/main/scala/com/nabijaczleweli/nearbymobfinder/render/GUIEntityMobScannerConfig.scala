package com.nabijaczleweli.nearbymobfinder.render

import net.minecraft.client.gui.{GuiTextField, GuiScreen}
import net.minecraft.util.ResourceLocation
import com.vikestep.nearbymobfinder.reference.Reference
import org.lwjgl.opengl.GL11._
import net.minecraft.client.Minecraft
import org.lwjgl.input.Keyboard

object GUIEntityMobScannerConfig extends GuiScreen {
	final val ID = 1

	private final val resourceGUI = new ResourceLocation(Reference.MOD_ID.toLowerCase, "textures/gui/entityMobScanner.png")
	private final val textureWidth  = 248
	private final val textureHeight = 166
	private final lazy val textureStartX = (this.width - textureWidth) / 2
	private final lazy val textureStartY = (this.height - textureHeight) / 2

	var rangeHorizontal = -0D
	var rangeVertical   = -0D

	private final var editBoxRangeHorizontal = null.asInstanceOf[GuiTextField]
	private final var editBoxRangeVertical   = null.asInstanceOf[GuiTextField]

	override def initGui() {
		super.initGui()
		Keyboard enableRepeatEvents true
		val tag = Minecraft.getMinecraft.thePlayer.getCurrentEquippedItem.getTagCompound
		rangeHorizontal = tag getDouble "rangeHorizontal"
		rangeVertical   = tag getDouble "rangeVertical"

		editBoxRangeHorizontal = new GuiTextField(fontRendererObj, textureStartX + 10, textureStartY + 10, textureWidth - 20, 20)
		editBoxRangeHorizontal setFocused true
		editBoxRangeHorizontal setText rangeHorizontal.toString
		editBoxRangeVertical = new GuiTextField(fontRendererObj, textureStartX + 10, textureStartY + 30, textureWidth - 20, 20)
		editBoxRangeVertical setText rangeVertical.toString
	}

	override def drawScreen(mouseX: Int, mouseY: Int, f: Float) {
		drawDefaultBackground()

		glColor4f(1.0F, 1.0F, 1.0F, 1.0F)
		mc.renderEngine bindTexture resourceGUI
		drawTexturedModalRect(textureStartX, textureStartY, 0, 0, textureWidth, textureHeight)

		editBoxRangeHorizontal.drawTextBox()
		editBoxRangeVertical  .drawTextBox()
		super.drawScreen(mouseX, mouseY, f)
	}

	override def updateScreen() {
		editBoxRangeHorizontal.updateCursorCounter()
		editBoxRangeVertical  .updateCursorCounter()
		super.updateScreen()
	}

	override def onGuiClosed() {
		Keyboard enableRepeatEvents false
		try {
			rangeHorizontal = editBoxRangeHorizontal.getText.toDouble
		} catch {
			case _: Throwable =>
		}
		try {
			rangeVertical = editBoxRangeVertical.getText.toDouble
		} catch {
			case _: Throwable =>
		}

		rangeHorizontal = rangeHorizontal max 0D
		rangeVertical   = rangeVertical max 0D
		val tag = Minecraft.getMinecraft.thePlayer.getCurrentEquippedItem.getTagCompound
		tag.setDouble("rangeHorizontal", rangeHorizontal)
		tag.setDouble("rangeVertical", rangeVertical)
		Minecraft.getMinecraft.thePlayer.getCurrentEquippedItem setTagCompound tag
	}

	override def keyTyped(character: Char, modifiers: Int) {
		super.keyTyped(character, modifiers)
		editBoxRangeHorizontal.textboxKeyTyped(character, modifiers)
		editBoxRangeVertical  .textboxKeyTyped(character, modifiers)
	}

	override def mouseClicked(mouseX: Int, mouseY: Int, key: Int) {
		super.mouseClicked(mouseX, mouseY, key)
		editBoxRangeHorizontal.mouseClicked(mouseX, mouseY, key)
		editBoxRangeVertical  .mouseClicked(mouseX, mouseY, key)
	}
}
