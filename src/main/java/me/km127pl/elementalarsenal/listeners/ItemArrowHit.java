package me.km127pl.elementalarsenal.listeners;

import me.km127pl.elementalarsenal.items.ItemManager;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class ItemArrowHit implements Listener {
	@EventHandler
	public void onArrowHit(ProjectileHitEvent event) {
		if (!(event.getEntity() instanceof Arrow arrow)) return;
		if (event.isCancelled()) return;
		if (!(arrow.getShooter() instanceof Player shooter)) return;

		ItemStack inHand = shooter.getActiveItem();
		String id = ItemManager.getIdFrom(inHand);
		if (id == null) return;
		if (!ItemManager.items.containsKey(id)) return;


		ItemManager.items.get(id).onArrowHit(event, shooter);

	}
}
