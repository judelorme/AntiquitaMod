package com.skillafire.antiquita.world;

import com.skillafire.antiquita.Antiquita;
import com.skillafire.antiquita.world.generation.ModOreGeneration;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Antiquita.MOD_ID)
public class ModWorldEvents {
	
	@SubscribeEvent
	public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
		ModOreGeneration.generateOres(event);
		/*ModFlowerGeneration.generateFlowers(event);
		ModTreeGeneration.generateTrees(event);
		
		event.getSpawns().addSpawn(MobCategory.CREATURE, new SpawnerData(EntityInit.ALIEN.get(), 10, 1, 7));*/
	}
}
