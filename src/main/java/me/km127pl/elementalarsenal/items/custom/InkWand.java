package me.km127pl.elementalarsenal.items.custom;

import me.km127pl.elementalarsenal.items.ItemRarity;
import me.km127pl.elementalarsenal.items.ItemType;
import me.km127pl.elementalarsenal.items.abilities.AbilityType;
import me.km127pl.elementalarsenal.items.abilities.ItemAbility;
import me.km127pl.elementalarsenal.items.types.ItemWand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class InkWand extends ItemWand {
	public static String ID = "INK_WAND";

	public InkWand() {
		super(Material.WITHER_ROSE, "Ink Wand", ItemRarity.EPIC, ItemType.WAND, ID);
	}

	@Override
	public ArrayList<ItemAbility> getAbilities() {
		ArrayList<ItemAbility> abilities = new ArrayList<>();
		abilities.add(new ItemAbility(
				AbilityType.RIGHT_CLICK,
				"Ink Ray",
				"Shoot an ink ray in front of you\ndealing " + this.getDamage() + " damage and\ngiving blindness!",
				"0"
		));

		return abilities;
	}

	@Override
	public double getDamage() {
		return 7.0;
	}

	@Override
	public double getCooldown() {
		return 0.5f;
	}


	@Override
	public void shootProjectilePrimary(Player player) {
		Location location = player.getEyeLocation();
		Vector direction = location.getDirection();
		World world = location.getWorld();
		int hits = 0;

		for (int i = 0; i < 12; i++) {
			location.add(direction);

			// check hits
			for (Entity entity : location.getChunk().getEntities()) {
				if (entity instanceof LivingEntity && entity.getLocation().distanceSquared(location) < 3) {
					if (entity == player) continue;
					((LivingEntity) entity).damage(this.getDamage(), player);
					((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 3, true, false));
					world.spawnParticle(Particle.SQUID_INK, entity.getLocation(), 12, 0, 0, 0, 0.1);
					hits++;
				}
			}
//			location.getWorld().spawnParticle(Particle.SPELL_MOB, location, 1, 0, 0, 0, 1);
			world.spawnParticle(Particle.ASH, location, 12, 0, 0, 0, 0.1);
			if (hits > 3) break;
		}
	}

}
