package me.km127pl.elementalarsenal.items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class ItemManager {
	public static HashMap<String, ItemBase> items = new HashMap<>();

	/**
	 * Registers an item
	 *
	 * @param id   the id of the item
	 * @param item the item to register
	 * @return true if the item was registered, false otherwise
	 */
	public static boolean registerItem(String id, ItemBase item) {
		if (items.containsKey(id)) return false;
		items.put(id, item);
		return true;
	}

	/**
	 * Gets an id from an item
	 *
	 * @param item the item to get the id from
	 * @return the id of the item, null if the item does not have an id
	 */
	public static String getIdFrom(ItemStack item) {
		if (item.getItemMeta() == null) return null;
		PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
		NamespacedKey key = new NamespacedKey("hitem", "item_id");

		return container.get(key, PersistentDataType.STRING);
	}

	public static void unregisterAll() {
		items.clear();
	}

	/**
	 * Checks if the item has an id
	 *
	 * @param item the item to check
	 * @return true if the item has an id, false otherwise
	 */
	public static boolean hasId(ItemStack item) {
		return getIdFrom(item) != null;
	}
}
