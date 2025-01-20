package net.mana7na.tutorialmod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.mana7na.tutorialmod.datagen.ModBlockTagProvider;
import net.mana7na.tutorialmod.datagen.ModItemTagProvider;
import net.mana7na.tutorialmod.datagen.ModLootTableProvider;
import net.mana7na.tutorialmod.datagen.ModRecipeProvider;
import net.mana7na.tutorialmod.datagen.ModModelProvider;

public class TutorialModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);


	}
}
