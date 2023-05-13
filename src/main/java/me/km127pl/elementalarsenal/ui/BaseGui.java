package me.km127pl.elementalarsenal.ui;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BaseGui implements Listener {
	public Inventory gui;
	public int lastSlot = 0;

	public BaseGui(Component title, int rows) {
		gui = Bukkit.createInventory(null, rows * 9, title);
	}

	public void init() {
		// this should be overridden
		// set an item in the middle to a dirt block saying
		// "this gui is not implemented yet"

		setItem(1, new Icon(Material.DIRT, 1, Component.text("This gui is not implemented yet")));
	}

	public void openGui(Player player) {
		player.openInventory(gui);
	}

	public void closeGui(Player player) {
		player.closeInventory();
	}

	public void setItem(int slot, ItemStack item) {
		gui.setItem(slot, item);
	}

	public void setItem(int slot, Icon icon) {
		gui.setItem(slot, icon.getItemStack());
	}

	public void addItem(ItemStack item) {
		gui.setItem(lastSlot, item);
		if (lastSlot == 16 || lastSlot == 25 || lastSlot == 34) {
			lastSlot += 3;
		} else {
			lastSlot++;
		}
	}

	public void fillWith(ItemStack item) {
		for (int i = 0; i < gui.getSize(); i++) {
			gui.setItem(i, item);
		}
	}

	public void fillWith(Icon icon) {
		fillWith(icon.getItemStack());
	}

	public void fillBorder(ItemStack item) {
		for (int i = 0; i < gui.getSize(); i++) {
			if (i < 9 || i > gui.getSize() - 9 || i % 9 == 0 || i % 9 == 8) {
				gui.setItem(i, item);
			}
		}
	}

	public void fillBorder(Icon icon) {
		fillBorder(icon.getItemStack());
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getCurrentItem() == null || !(event.getCurrentItem().hasItemMeta())) return;
		if (Component.text(" ").equals(event.getCurrentItem().getItemMeta().displayName())) {
			event.setCancelled(true);
		}
	}
}
