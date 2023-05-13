package me.km127pl.elementalarsenal.items.custom;

import com.google.common.collect.Lists;
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

public class RadianceWand extends ItemWand {
	public static String ID = "RADIANCE_WAND";

	public RadianceWand() {
		super(Material.LIGHT, "Radiance Wand", ItemRarity.MYSTICAL, ItemType.WAND, ID);
	}

	@Override
	public ArrayList<ItemAbility> getAbilities() {
		ArrayList<ItemAbility> abilities = new ArrayList<>();
		abilities.add(new ItemAbility(
				AbilityType.RIGHT_CLICK,
				"Luminous Flare",
				"The Radiance Wand's Luminous Flare\nspell creates a bright burst of light that illuminates dark areas\nand can potentially blind enemies.\nIt's great for exploring dim places and enjoying its warm radiance.", "0"
		));

		abilities.add(new ItemAbility(
				AbilityType.LEFT_CLICK,
				"Douse Light",
				"The Douse Light spell extinguishes\nall light sources in the area,creating darkness. \nIt can counter the Radiance Wand's Luminous Flare spell,\nallowing players to extinguish the light and return to the shadows.",
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
		return 0.5f;
	}

	@Override
	public void shootProjectilePrimary(Player player) {
		Location location = player.getEyeLocation();
		Vector direction = location.getDirection();

		Particle.DustTransition dustTransition = new Particle.DustTransition(Color.fromRGB(235, 232, 52), Color.fromRGB(255, 255, 255), 1.0F);

		for (int i = 0; i < 20; i++) {
			location.add(direction);

			// Place a block if it's a valid location
			if (!(location.getBlock().getType().isAir() || location.getBlock().getType() == Material.WATER)) {

				getBlocksInRadius(location, 1.5).forEach((block) -> {
					if (block.getType() == Material.AIR) {
						block.setType(Material.LIGHT);
						block.getState().update(true);
						List<Location> particles = getHollowCube(location, 0.5);
						particles.forEach((_particle) -> {
							_particle.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, _particle, 1, dustTransition);
						});
					}
				});
				break; // we have hit a block
			}

			// Spawn the particles
			player.spawnParticle(Particle.DUST_COLOR_TRANSITION, location, 50, dustTransition);
		}

	}

	@Override
	public void shootProjectileSecondary(Player player) {
		Location location = player.getEyeLocation();
		Vector direction = location.getDirection();

		Particle.DustTransition dustTransition = new Particle.DustTransition(Color.fromRGB(235, 232, 52), Color.fromRGB(0, 0, 0), 1.0F);
		for (int i = 0; i < 20; i++) {
			location.add(direction);

			// Place a block if it's a valid location
			if (!(location.getBlock().getType().isAir() || location.getBlock().getType() == Material.WATER)) {

				getBlocksInRadius(location, 6).forEach((block) -> {
					if (block.getType() == Material.LIGHT) {
						block.setType(Material.AIR);
						block.getState().update(true);
						List<Location> particles = getHollowCube(location, 0.5);
						particles.forEach((particle) -> {
							particle.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, particle, 1, dustTransition);
						});

					}
				});
				break; // we have hit a block
			}

			// Spawn the particles
			player.spawnParticle(Particle.DUST_COLOR_TRANSITION, location, 50, dustTransition);
		}
	}

	public List<Block> getBlocksInRadius(Location center, double radius) {
		List<Block> blocks = new ArrayList<Block>();

		for (int x = (int) (center.getBlockX() - radius); x <= center.getBlockX() + radius; x++) {
			for (int y = (int) (center.getBlockY() - radius); y <= center.getBlockY() + radius; y++) {
				for (int z = (int) (center.getBlockZ() - radius); z <= center.getBlockZ() + radius; z++) {
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

	public List<Location> getHollowCube(Location loc, double particleDistance) {
		List<Location> result = Lists.newArrayList();
		World world = loc.getWorld();
		double minX = loc.getBlockX();
		double minY = loc.getBlockY();
		double minZ = loc.getBlockZ();
		double maxX = loc.getBlockX() + 1;
		double maxY = loc.getBlockY() + 1;
		double maxZ = loc.getBlockZ() + 1;

		for (double x = minX; x <= maxX; x = Math.round((x + particleDistance) * 1e2) / 1e2) {
			for (double y = minY; y <= maxY; y = Math.round((y + particleDistance) * 1e2) / 1e2) {
				for (double z = minZ; z <= maxZ; z = Math.round((z + particleDistance) * 1e2) / 1e2) {
					int components = 0;
					if (x == minX || x == maxX) components++;
					if (y == minY || y == maxY) components++;
					if (z == minZ || z == maxZ) components++;
					if (components >= 2) {
						result.add(new Location(world, x, y, z));
					}
				}
			}
		}
		return result;
	}


}
