package net.rubfiber.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.rubfiber.tutorialmod.block.ModBlocks;
import net.rubfiber.tutorialmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.rubfiber.tutorialmod.PlaceBlockClass;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		ModItems.registerModItems(); //register item, blocks, etc
		ModBlocks.registerModBlocks();
		PlaceBlockClass.placeBlockAtPlayerLocation();


	}
}

//This logger is used to write text to the console and the log file.
// It is considered best practice to use your mod id as the logger's name.
// That way, it's clear which mod wrote info, warnings, and errors.