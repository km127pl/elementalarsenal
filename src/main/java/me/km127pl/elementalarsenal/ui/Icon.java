package me.km127pl.elementalarsenal.ui;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Icon {

	private final Material material;
	private final int amount;
	private final Component name;

	/**
	 * Creates an Icon
	 *
	 * @param material the material of the icon
	 * @param amount   the amount of the icon
	 * @param name     the name of the icon
	 */
	public Icon(Material material, int amount, Component name) {
		this.material = material;
		this.amount = amount;
		this.name = name;
	}

	/**
	 * Gets the ItemStack of the icon
	 *
	 * @return the ItemStack of the icon
	 */
	public ItemStack getItemStack() {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.displayName(name);
		item.setItemMeta(meta);

		return item;
	}

	/**
	 * Gets the material of the icon
	 *
	 * @return the material of the icon
	 */
	public Material getMaterial() {
		return material;
	}
}
