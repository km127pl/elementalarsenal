package me.km127pl.elementalarsenal.items.custom.wands;

import me.km127pl.elementalarsenal.items.ItemRarity;
import me.km127pl.elementalarsenal.items.ItemType;
import me.km127pl.elementalarsenal.items.types.ItemWand;
import me.km127pl.elementalarsenal.ui.DamageIndicator;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;

public class DebugWand extends ItemWand {
	public static String ID = "DEBUG_WAND";

	public DebugWand() {
		super(Material.DEBUG_STICK, "Debug Wand", ItemRarity.MYSTICAL, ItemType.WAND, ID);
	}

	@Override
	public double getDamage() {
		return 9999.99;
	}

	@Override
	public void onRightClick(PlayerInteractEvent event) {
		DamageIndicator indicator = new DamageIndicator(event.getPlayer(), 9999);
		indicator.show(event.getPlayer().getLocation());
		event.getPlayer().sendMessage("Damage indicator shown!");
		event.setCancelled(true);

	}
}
