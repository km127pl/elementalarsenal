package me.km127pl.elementalarsenal.listeners;

import me.km127pl.elementalarsenal.items.ItemManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemAction implements Listener {
	@EventHandler
	public void onItemClick(PlayerInteractEvent event) {
		if (!event.hasItem() || event.getItem() == null) return;
		String id = ItemManager.getIdFrom(event.getItem());
		if (id == null) return;
		if (!ItemManager.items.containsKey(id)) return;


		if (event.getAction().isLeftClick()) {
			ItemManager.items.get(id).onLeftClick(event);
		} else if (event.getAction().isRightClick()) {
			ItemManager.items.get(id).onRightClick(event);
		}

		event.setCancelled(true);
	}
}
