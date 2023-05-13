package me.km127pl.elementalarsenal.ui;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Icon {

	private final Material material;
	private final int amount;
	private final Component name;
	private boolean isFiller = false;

	public Icon(Material material, int amount, Component name) {
		this.material = material;
		this.amount = amount;
		this.name = name;
	}

	public ItemStack getItemStack() {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.displayName(name);
		item.setItemMeta(meta);

		return item;
	}

	public Icon filler(boolean filler) {
		isFiller = filler;
		return this;
	}

	public boolean isFiller() {
		return isFiller;
	}

	public Material getMaterial() {
		return material;
	}
}
