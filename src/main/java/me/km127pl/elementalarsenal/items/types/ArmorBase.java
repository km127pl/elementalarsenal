package me.km127pl.elementalarsenal.items.types;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.km127pl.elementalarsenal.ElementalArsenal;
import me.km127pl.elementalarsenal.items.ItemRarity;
import me.km127pl.elementalarsenal.items.abilities.ItemAbility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ArmorBase {
	public Material material;
	public String name;
	public String id;
	public ItemRarity rarity;
	public boolean glint = false;

	public ArmorBase(Material material, String name, ItemRarity rarity, String id) {
		this.material = material;
		this.name = name;
		this.id = id;
		this.rarity = rarity;
	}

	/**
	 * Called when the player shift jumps
	 * @param event The event that is called when the player shift jumps
	 * @see PlayerJumpEvent
	 * @implNote This method is called when the player shift jumps
	 * @apiNote This method should be overridden
	 */
	public void onShiftJump(PlayerJumpEvent event) {}

	/**
	 * Called when the player jumps
	 * @param event The event that is called when the player jumps
	 * @see PlayerJumpEvent
	 * @implNote This method is called when the player jumps
	 * @apiNote This method should be overridden
	 */
	public void onJump(PlayerJumpEvent event) {};

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material armorMaterial) {
		this.material = armorMaterial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<ItemAbility> getAbilities() {
		return new ArrayList<>();
	}

	public ItemStack getItem() {
		ItemStack item = new ItemStack(this.material);
		ItemMeta meta = item.getItemMeta();

		meta.displayName(Component.text(this.name).color(TextColor.fromHexString(this.rarity.color)).decoration(TextDecoration.ITALIC, false));
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
		meta.getPersistentDataContainer().set(ElementalArsenal.key, PersistentDataType.STRING, this.id);
//		meta.lore(lore);

		if (this.glint) {
			meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		}

		List<Component> _lore = new ArrayList<>();

		this.getAbilities().forEach((ability) -> {
			_lore.addAll(ability.getAsLore());
			_lore.add(Component.text(" "));
		});

		_lore.add(this.rarity.asComponent());

		meta.lore(_lore);
		item.setItemMeta(meta);
		return item;
	}
}
