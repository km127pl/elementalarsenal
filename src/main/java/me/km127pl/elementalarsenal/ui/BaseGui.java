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

	/**
	 * This method is called upon gui creation.
	 */
	public void init() {
		// this should be overridden
		// set an item in the middle to a dirt block saying
		// "this gui is not implemented yet"

		setItem(1, new Icon(Material.DIRT, 1, Component.text("This gui is not implemented yet")));
	}

	/**
	 * Opens the gui for the player
	 * @param player the player to open the gui for
	 */
	public void openGui(Player player) {
		player.openInventory(gui);
	}

	/**
	 * Closes the gui for the player
	 * @param player the player to close the gui for
	 */
	public void closeGui(Player player) {
		player.closeInventory();
	}

	/**
	 * Sets an item in the gui
	 * @param slot the slot to set the item in
	 * @param item the item to set
	 */
	public void setItem(int slot, ItemStack item) {
		gui.setItem(slot, item);
	}

	/**
	 * Sets an item in the gui
	 * @param slot the slot to set the item in
	 * @param icon the icon to set
	 */
	public void setItem(int slot, Icon icon) {
		gui.setItem(slot, icon.getItemStack());
	}

	/**
	 * Adds an item to the gui
	 * @param item the item to add
	 * @implNote This method adds an item to the gui, and automatically increments the lastSlot variable.
	 */
	public void addItem(ItemStack item) {
		gui.setItem(lastSlot, item);
		if (lastSlot == 16 || lastSlot == 25 || lastSlot == 34) {
			lastSlot += 3;
		} else {
			lastSlot++;
		}
	}

	/**
	 * Fills the gui with an item
	 * @param item the item to fill the gui with
	 */
	public void fillWith(ItemStack item) {
		for (int i = 0; i < gui.getSize(); i++) {
			gui.setItem(i, item);
		}
	}

	/**
	 * Fills the gui with an icon
	 * @param icon the icon to fill the gui with
	 */
	public void fillWith(Icon icon) {
		fillWith(icon.getItemStack());
	}

	/**
	 * Fills the border of the gui with an item
	 * @param item the item to fill the border with
	 */
	public void fillBorder(ItemStack item) {
		for (int i = 0; i < gui.getSize(); i++) {
			if (i < 9 || i > gui.getSize() - 9 || i % 9 == 0 || i % 9 == 8) {
				gui.setItem(i, item);
			}
		}
	}

	/**
	 * Fills the border of the gui with an icon
	 * @param icon the icon to fill the border with
	 */
	public void fillBorder(Icon icon) {
		fillBorder(icon.getItemStack());
	}

	/**
	 * Inventory click event handler
	 */
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getCurrentItem() == null || !(event.getCurrentItem().hasItemMeta())) return;
		if (Component.text(" ").equals(event.getCurrentItem().getItemMeta().displayName())) {
			event.setCancelled(true);
		}

		this.onGuiClick(event);
	}

	/**
	 * On gui click event handler
	 * @param event the event
	 * @implNote This method is called when the gui is clicked.
	 * @apiNote This method should be overridden.
	 */
	public void onGuiClick(InventoryClickEvent event) {
		// this should be overridden
		// this is called when the gui is clicked
	}
}
