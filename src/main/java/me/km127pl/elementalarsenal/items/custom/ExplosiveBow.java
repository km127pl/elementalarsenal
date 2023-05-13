package me.km127pl.elementalarsenal.items.custom;

import me.km127pl.elementalarsenal.items.ItemBase;
import me.km127pl.elementalarsenal.items.ItemRarity;
import me.km127pl.elementalarsenal.items.ItemType;
import me.km127pl.elementalarsenal.items.abilities.AbilityType;
import me.km127pl.elementalarsenal.items.abilities.ItemAbility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.ArrayList;
import java.util.Objects;

public class ExplosiveBow extends ItemBase {
	public static String ID = "EXPLOSIVE_BOW";

	public ExplosiveBow() {
		super(Material.BOW, "Explosive Bow", ItemRarity.LEGENDARY, ItemType.BOW, ID);
	}

	@Override
	public ArrayList<ItemAbility> getAbilities() {
		ArrayList<ItemAbility> abilities = new ArrayList<>();
		abilities.add(new ItemAbility(
				AbilityType.PASSIVE,
				"Explosion",
				"Creates an explosion on impact!\nEvery Monster caught in this explosion\ntakes the full damage of the bow.",
				"0"
		));

		return abilities;
	}

	@Override
	public double getDamage() {
		return 5.0;
	}

	@Override
	public void onArrowHit(ProjectileHitEvent event, Player shooter) {
		Location hit = Objects.requireNonNull(event.getHitBlock()).getLocation();
		hit.createExplosion(4.0f, false, false);
		event.getEntity().remove();
	}
}
