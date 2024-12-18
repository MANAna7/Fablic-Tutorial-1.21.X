package net.mana7na.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.mana7na.tutorialmod.block.ModBlocks;
import net.mana7na.tutorialmod.item.ModItemGroups;
import net.mana7na.tutorialmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//	初期化するメソッド
	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroup();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES,600);
	}
}