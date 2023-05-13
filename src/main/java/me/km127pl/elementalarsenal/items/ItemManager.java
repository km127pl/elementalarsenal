package me.km127pl.elementalarsenal.items;

import me.km127pl.elementalarsenal.ElementalArsenal;
import me.km127pl.elementalarsenal.items.types.ArmorBase;
import me.km127pl.elementalarsenal.items.types.ItemBase;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class ItemManager {
	public static HashMap<String, ItemBase> items = new HashMap<>();
	public static HashMap<String, ArmorBase> armor = new HashMap<>();

	/**
	 * Registers an item
	 *
	 * @param id   the id of the item
	 * @param item the item to register
	 */
	public static void registerItem(String id, ItemBase item) {
		if (items.containsKey(id)) return;
		items.put(id, item);
	}

	/**
	 * Registers an armor piece
	 *
	 * @param id   the id of the item
	 * @param item the armor piece to register
	 */
	public static void registerItem(String id, ArmorBase item) {
		if (armor.containsKey(id)) return;
		armor.put(id, item);
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

		return container.get(ElementalArsenal.key, PersistentDataType.STRING);
	}

	public static void unregisterAll() {
		items.clear();
		armor.clear();
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
