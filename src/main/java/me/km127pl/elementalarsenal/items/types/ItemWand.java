package me.km127pl.elementalarsenal.items.types;

import me.km127pl.elementalarsenal.items.ItemBase;
import me.km127pl.elementalarsenal.items.ItemRarity;
import me.km127pl.elementalarsenal.items.ItemType;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemWand extends ItemBase {
	public ItemWand(Material material, String name, ItemRarity rarity, ItemType type, String id) {
		super(material, name, rarity, type, id);
	}

	public double getCooldown() {
		return 1.0f;
	}

	@Override
	public void onRightClick(PlayerInteractEvent event) {
//		shoot(event.getPlayer());
//		sendActionbar(event.getPlayer(), "Ability used - ");
	}
}
