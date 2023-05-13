package me.km127pl.elementalarsenal.listeners;

import com.google.common.collect.Multimap;
import me.km127pl.elementalarsenal.ElementalArsenal;
import me.km127pl.elementalarsenal.items.ItemBase;
import me.km127pl.elementalarsenal.items.ItemManager;
import me.km127pl.elementalarsenal.ui.DamageIndicator;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Collection;

public class ItemDamage implements Listener {
	@EventHandler
	public void onPlayerDamageEvent(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) return;
		Player damager = ((Player) event.getDamager()).getPlayer();
		assert damager != null;
		if (!ItemManager.hasId(damager.getActiveItem())) return;

		ItemBase item = ItemManager.items.get(ItemManager.getIdFrom(damager.getActiveItem()));

		double damage = event.getDamage();
		double baseDamage = 0.0;

		Multimap<Attribute, AttributeModifier> modifiers = item.getItem().getItemMeta().getAttributeModifiers(EquipmentSlot.HAND);
		Collection<AttributeModifier> modifierCollection = modifiers.get(Attribute.GENERIC_ATTACK_DAMAGE);

		for (AttributeModifier modifier : modifierCollection) {
			baseDamage += modifier.getAmount();
		}

		damage -= baseDamage;

		double finalDamage = event.getDamage() - damage;
		finalDamage += item.getDamage();
		if (event.isCritical()) {
			finalDamage *= 1.2;
		}

		event.setDamage(finalDamage);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void postDamageHandler(EntityDamageEvent event) {
		if (ElementalArsenal.configuration.getBoolean("settings.damage-indicators")) {
			DamageIndicator indicator = new DamageIndicator((LivingEntity) event.getEntity(), (int) event.getDamage());
			indicator.show(event.getEntity().getLocation());
		}
		if (ElementalArsenal.configuration.getBoolean("settings.blood-particles")) {
			// create 'blood' particles
			event.getEntity().getWorld().spawnParticle(Particle.BLOCK_CRACK, event.getEntity().getLocation(), 10, 0.5, 0.5, 0.5, Material.REDSTONE_BLOCK.createBlockData());
		}
	}
}
