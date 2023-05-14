package me.km127pl.elementalarsenal.items.types;

import me.km127pl.elementalarsenal.items.ItemRarity;
import me.km127pl.elementalarsenal.items.ItemType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemWand extends ItemBase {
	public ItemWand(Material material, String name, ItemRarity rarity, ItemType type, String id) {
		super(material, name, rarity, type, id);
	}

	public double getCooldown() {
		return 10.0f;
	}

	@Override
	public void onRightClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getCooldown(this.getMaterial()) > 0) return;
		// add a cooldown if the player doesn't have one
		player.setCooldown(this.getMaterial(), (int) (this.getCooldown() * 20));
		this.shootProjectilePrimary(player);
		sendActionbar(event.getPlayer(), Component.text("Ability used - ").color(TextColor.color(255, 125, 0))
				.append(Component.text(this.getAbilities().get(0).getName()).color(TextColor.color(255, 208, 11))).decoration(TextDecoration.ITALIC, false));
	}

	@Override
	public void onLeftClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getCooldown(this.getMaterial()) > 0) return;
		// add a cooldown if the player doesn't have one
		player.setCooldown(this.getMaterial(), (int) (this.getCooldown() * 20));
		this.shootProjectileSecondary(player);
		sendActionbar(event.getPlayer(), Component.text("Ability used - ").color(TextColor.color(255, 125, 0))
				.append(Component.text(this.getAbilities().get(1).getName()).color(TextColor.color(255, 208, 11))).decoration(TextDecoration.ITALIC, false));
	}

	/**
	 * Shoots a primary projectile from the player
	 *
	 * @param player The player to shoot the projectile from
	 * @implNote This method is called when the player right clicks with the item<br>Use this instead of binding to {@link ItemBase#onRightClick(PlayerInteractEvent)}.
	 * @see ItemBase#onRightClick(PlayerInteractEvent)
	 */
	public void shootProjectilePrimary(Player player) {}

	/**
	 * Shoots a secondary projectile from the player
	 *
	 * @param player The player to shoot the projectile from
	 * @implNote This method is called when the player right clicks with the item<br>Use this instead of binding to {@link ItemBase#onRightClick(PlayerInteractEvent)}.
	 * @see ItemBase#onRightClick(PlayerInteractEvent)
	 */
	public void shootProjectileSecondary(Player player) {}
}
