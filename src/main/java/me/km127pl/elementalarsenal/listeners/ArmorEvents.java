package me.km127pl.elementalarsenal.listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.km127pl.elementalarsenal.items.ItemManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArmorEvents implements Listener {
	@EventHandler
	public void onPlayerJumpEvent(PlayerJumpEvent event) {
		// this is a really intensive event, so we should check if the player is wearing any armor first
		if (event.getPlayer().getInventory().getBoots() == null || event.getPlayer().getInventory().getBoots().getItemMeta() == null) return;
		String id = ItemManager.getIdFrom(event.getPlayer().getInventory().getBoots());
		if (id == null) return;
		if (!ItemManager.armor.containsKey(id)) return;


		if (event.getPlayer().isSneaking()) {
			ItemManager.armor.get(id).onShiftJump(event);
		} else {
			ItemManager.armor.get(id).onJump(event);
		}

//		event.setCancelled(true);, it's a jump event, we don't want to cancel it
	}
}
