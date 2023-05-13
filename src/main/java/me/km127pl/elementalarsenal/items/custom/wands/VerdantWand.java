package me.km127pl.elementalarsenal.items.custom.wands;

import me.km127pl.elementalarsenal.ElementalArsenal;
import me.km127pl.elementalarsenal.items.ItemRarity;
import me.km127pl.elementalarsenal.items.ItemType;
import me.km127pl.elementalarsenal.items.abilities.AbilityType;
import me.km127pl.elementalarsenal.items.abilities.ItemAbility;
import me.km127pl.elementalarsenal.items.types.ItemWand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class VerdantWand extends ItemWand {
	public static String ID = "VERDANT_WAND";

	public VerdantWand() {
		super(Material.GREEN_CANDLE, "Verdant Wand", ItemRarity.UNCOMMON, ItemType.WAND, ID);
	}

	@Override
	public ArrayList<ItemAbility> getAbilities() {
		ArrayList<ItemAbility> abilities = new ArrayList<>();
		abilities.add(new ItemAbility(
				AbilityType.RIGHT_CLICK,
				"Bloom",
				"A magical burst of energy from the wand\ncauses plants and flowers in the targeted area to rapidly grow,\nincluding saplings, crops, and even vines and\nmoss on nearby surfaces.",
				"0"
		));

		return abilities;
	}

	@Override
	public double getDamage() {
		return 0.0; // it's a wand, bruv
	}

	@Override
	public double getCooldown() {
		return 1.5f;
	}


	@Override
	public void shootProjectilePrimary(Player player) {
		// Get the player's location and direction
		Location location = player.getEyeLocation();
		Vector direction = location.getDirection();

		// Define the particles to use
		Particle particle = Particle.VILLAGER_HAPPY;
		// Loop through the particles and place blocks\
		Random random = new Random();
		AtomicInteger counter = new AtomicInteger(0);
		for (int i = 0; i < 20; i++) {
			location.add(direction);

			// Place a block if it's a valid location
			if (!(location.getBlock().getType().isAir() || location.getBlock().getType() == Material.WATER)) {

				getBlocksInRadius(location, 6).forEach((block) -> {
					counter.getAndIncrement();
					Bukkit.getScheduler().scheduleSyncDelayedTask(ElementalArsenal.getPlugin(), () -> {
						if (random.nextFloat() > 0.7) {
							block.applyBoneMeal(BlockFace.DOWN);
							block.getState().update(true);
						}
					}, 5);
				});
				break; // we have hit a block
			}

			// Spawn the particles
			location.getWorld().spawnParticle(particle, location, 1);
		}

	}

	public List<Block> getBlocksInRadius(Location center, int radius) {
		List<Block> blocks = new ArrayList<Block>();

		for (int x = center.getBlockX() - radius; x <= center.getBlockX() + radius; x++) {
			for (int y = center.getBlockY() - radius; y <= center.getBlockY() + radius; y++) {
				for (int z = center.getBlockZ() - radius; z <= center.getBlockZ() + radius; z++) {
					Block block = center.getWorld().getBlockAt(x, y, z);

					// Check if the block is within the radius
					if (block.getLocation().distanceSquared(center) <= radius * radius) {
						blocks.add(block);
					}
				}
			}
		}

		return blocks;
	}


}
