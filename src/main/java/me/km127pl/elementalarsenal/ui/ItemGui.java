package me.km127pl.elementalarsenal.ui;

import me.km127pl.elementalarsenal.items.ItemManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.List;
import java.util.Objects;

public class ItemGui extends BaseGui {

	public static Icon searchIcon = new Icon(Material.COMPASS, 1, Component.text("Search for an item").decoration(TextDecoration.ITALIC, false).color(TextColor.color(0xFFB840)));
	public ItemGui() {
		super(Component.text("Elemental Arsenal"), 6);

		fillBorder(new Icon(Material.GRAY_STAINED_GLASS_PANE, 1, Component.text(" ")));
		lastSlot = 10;

		init();
		// add a search icon
		setItem(49, searchIcon);
	}

	@Override
	public void init() {
		// add all the items from itemmanager
		ItemManager.items.forEach((name, item) -> {
			addItem(item.getItem());
		});
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		super.onInventoryClick(event);
		if (Objects.equals(event.getCurrentItem(), searchIcon.getItemStack())) {
			//not implemented yet
			event.setCancelled(true);
		}
	}
}
