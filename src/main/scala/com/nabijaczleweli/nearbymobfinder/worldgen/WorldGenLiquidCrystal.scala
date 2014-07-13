package com.nabijaczleweli.nearbymobfinder.worldgen

import cpw.mods.fml.common.IWorldGenerator
import java.util.Random
import net.minecraft.world.World
import net.minecraft.world.chunk.IChunkProvider
import com.vikestep.nearbymobfinder.reference.Container
import scala.beans.BeanProperty

object WorldGenLiquidCrystal extends IWorldGenerator {
	@BeanProperty var treshold = 5
	@BeanProperty var bigVeinProbability = 300
	@BeanProperty var baseGenerationLevel = 30
	@BeanProperty var offLevelMax = 3

	private var generatedChunks = 0L

	override def generate(random: Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkProvider, chunkProvider: IChunkProvider) {
		generatedChunks %= treshold

		if(generatedChunks == 0)
			if(random.nextInt(bigVeinProbability) == 0) {
				val baseY = baseGenerationLevel + (if(random.nextBoolean()) -random.nextInt(offLevelMax) else random nextInt offLevelMax)
				val baseX = chunkX * 16 + random.nextInt(14) + 1
				val baseZ = chunkZ * 16 + random.nextInt(14) + 1
				world.setBlock(baseX, baseY, baseZ, Container.liquidCrystalB, 8, 1 | 2)
				world.setBlock(baseX + 1, baseY, baseZ, Container.liquidCrystalB, 2, 1 | 2)
				world.setBlock(baseX - 1, baseY, baseZ, Container.liquidCrystalB, 2, 1 | 2)
				world.setBlock(baseX, baseY, baseZ + 1, Container.liquidCrystalB, 2, 1 | 2)
				world.setBlock(baseX, baseY, baseZ - 1, Container.liquidCrystalB, 2, 1 | 2)
			} else {
				val yLevel = baseGenerationLevel + (if(random.nextBoolean()) -random.nextInt(offLevelMax) else random nextInt offLevelMax)
				world.setBlock(chunkX * 16 + random.nextInt(16), yLevel, chunkZ * 16 + random.nextInt(16), Container.liquidCrystalB, random.nextInt(4), 1 | 2)
			}

		generatedChunks += 1
	}
}
