package me.km127pl.elementalarsenal.listeners;

import me.km127pl.elementalarsenal.commands.Messages;
import me.km127pl.elementalarsenal.items.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ItemBlock implements Listener {

	// disable click in the anvil gui
	//TODO: this breaks if a chest is named "Repair & Name"
	// i dont know of a better way to check if the anvil gui is open
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getView().title().equals(Component.text("Repair & Name"))) {
			if (event.getCurrentItem() == null) return;
			if (ItemManager.hasId(event.getCurrentItem())) {
				event.setCancelled(true);
				event.getWhoClicked().sendMessage(Messages.YOU_CANNOT_PUT_ANVIL.getFormattedMessage());
			}
		}
	}
}
