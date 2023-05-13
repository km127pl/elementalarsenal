package me.km127pl.elementalarsenal.items.custom;

import me.km127pl.elementalarsenal.ElementalArsenal;
import me.km127pl.elementalarsenal.items.ItemBase;
import me.km127pl.elementalarsenal.items.ItemRarity;
import me.km127pl.elementalarsenal.items.ItemType;
import me.km127pl.elementalarsenal.items.abilities.AbilityType;
import me.km127pl.elementalarsenal.items.abilities.ItemAbility;
import me.km127pl.elementalarsenal.items.types.ItemWand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class RedstoneWand extends ItemWand {
	public static final String ID = "REDSTONE_WAND";
	public static ArrayList<Material> redstoneComponents = new ArrayList<>();

	public RedstoneWand() {
		super(Material.REDSTONE_TORCH, "Redstone Disruptor Wand", ItemRarity.SPECIAL, ItemType.WAND, ID);

		redstoneComponents.add(Material.REDSTONE_WIRE);
		redstoneComponents.add(Material.REPEATER);
		redstoneComponents.add(Material.COMPARATOR);
		redstoneComponents.add(Material.OBSERVER);
		redstoneComponents.add(Material.DROPPER);
		redstoneComponents.add(Material.DISPENSER);
		redstoneComponents.add(Material.HOPPER);
		redstoneComponents.add(Material.PISTON);
		redstoneComponents.add(Material.STICKY_PISTON);
		redstoneComponents.add(Material.REDSTONE_TORCH);
		redstoneComponents.add(Material.REDSTONE_LAMP);
	}

	public double getCooldown() {
		return 1.5f;
	}

	@Override
	public ArrayList<ItemAbility> getAbilities() {
		ArrayList<ItemAbility> abilities = new ArrayList<>();
		abilities.add(new ItemAbility(
				AbilityType.RIGHT_CLICK,
				"Disruption",
				"Creates disruptions in the redstone current\nthat it hits.",
				"0"
		));

		return abilities;
	}

	@Override
	public void shootProjectilePrimary(Player player) {
		Location location = player.getEyeLocation();
		Vector direction = location.getDirection();
		World world = location.getWorld();
		ArrayList<Location> beenThere = new ArrayList<>();
		// max distance of 15

		for (int i = 0; i < 30; i++) {
			location.add(direction);

			if (!(location.getBlock().getType().isAir())) {
				// we have hit a block
				getBlocksInRadius(location, 3).forEach(block -> {
					if (beenThere.contains(block.getLocation())) {
						return;
					}
					if (block.getType() == Material.REDSTONE_WIRE) {
						// check if its in the redstone components
						if (redstoneComponents.contains(block.getType())) {

							world.spawnParticle(Particle.REDSTONE, location, 1, 0, 0, 0, 0,
									new Particle.DustOptions(Color.fromRGB(255, 0, 0), 1), true);
							world.playSound(location, Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1, 1);

							// make the block powered and unpower after 1 second
							Location clone = block.getLocation().clone();
							clone.subtract(0, 1, 0);
							Material original = clone.getBlock().getType();
							clone.getBlock().setType(Material.REDSTONE_BLOCK);
							clone.getBlock().getState().update(true);
							beenThere.add(clone);
							// unpower after 1 second
							Bukkit.getScheduler().scheduleSyncDelayedTask(ElementalArsenal.getPlugin(), () -> {
								if (original != Material.REDSTONE_BLOCK) {
									if (clone.getBlock().getType() == Material.REDSTONE_BLOCK) {
										clone.getBlock().setType(original);
									}
								}

								clone.getBlock().getState().update(true);
							}, 20L);

						}
					}

					beenThere.add(block.getLocation());
				});

			}
			world.spawnParticle(Particle.REDSTONE, location, 1, 0, 0, 0, 0,
					new Particle.DustOptions(Color.fromRGB(255, 0, 0), 1), true);
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
