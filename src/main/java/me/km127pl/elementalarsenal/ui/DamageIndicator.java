package me.km127pl.elementalarsenal.ui;

import me.km127pl.elementalarsenal.ElementalArsenal;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Objects;

public class DamageIndicator {
	public LivingEntity entity;
	public ArmorStand indicator;
	public int damage;

	/**
	 * Creates a new damage indicator
	 *
	 * @param entity the entity to show the indicator for
	 * @param damage the amount of damage to show
	 */
	public DamageIndicator(LivingEntity entity, int damage) {
		this.entity = entity;
		this.damage = damage;
	}

	/**
	 * Shows the damage indicator at the given location
	 *
	 * @param loc the location to show the indicator at
	 */
	public void show(Location loc) {
		// add a random off set the location
		loc.add(Math.random() * 0.7, 0, Math.random() * 0.7);
		ArmorStand indicator = (ArmorStand) loc.getWorld().spawnEntity(loc.subtract(0, 0.7, 0), EntityType.ARMOR_STAND, CreatureSpawnEvent.SpawnReason.CUSTOM);
		indicator.setVisible(false);
		indicator.setCustomNameVisible(true);
		indicator.setGravity(false);
		indicator.setInvulnerable(true);
		indicator.setSilent(true);

		Component text = Component.text(String.valueOf(damage));

		// colour the text based on the damage % of the entity max health
		if (entity.getAttribute(Attribute.GENERIC_MAX_HEALTH) == null) return;
		double percent = (double) damage / Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();

		if (percent > 0.75) {
			text = text.color(TextColor.color(0xFF0000));
		} else if (percent > 0.5) {
			text = text.color(TextColor.color(0xFFA500));
		} else if (percent > 0.25) {
			text = text.color(TextColor.color(0xFFFF00));
		} else {
			text = text.color(TextColor.color(0x00FF00));
		}

		indicator.customName(text);

		this.indicator = indicator;

		// remove the indicator after 1 second
		Bukkit.getScheduler().scheduleSyncDelayedTask(ElementalArsenal.getPlugin(), indicator::remove, 20);
	}
}
