package me.km127pl.elementalarsenal.items.custom.wands;

import me.km127pl.elementalarsenal.items.ItemRarity;
import me.km127pl.elementalarsenal.items.ItemType;
import me.km127pl.elementalarsenal.items.abilities.AbilityType;
import me.km127pl.elementalarsenal.items.abilities.ItemAbility;
import me.km127pl.elementalarsenal.items.types.ItemWand;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class InfernoWand extends ItemWand {

	public static final String ID = "INFERNO_WAND";
	public InfernoWand() {
		super(Material.BLAZE_ROD, "Inferno Wand", ItemRarity.RARE, ItemType.WAND, ID);
	}

	@Override
	public double getDamage() {
		return 8;
	}

	@Override
	public double getCooldown() {
		return 0.8f;
	}

	@Override
	public ArrayList<ItemAbility> getAbilities() {
		ArrayList<ItemAbility> abilities = new ArrayList<>();
		abilities.add(new ItemAbility(
				AbilityType.RIGHT_CLICK,
				"Inferno Bust",
				"Releases a burst of flames that ignites\nany mobs or players caught in its radius,\ndealing extra damage over time and,\nsetting the surrounding area on fire.",
				"0"
		));

		return abilities;
	}

	@Override
	public void shootProjectilePrimary(Player player) {

		// shoot a fireball
		player.launchProjectile(org.bukkit.entity.Fireball.class); // no way this is that easy
	}
}
