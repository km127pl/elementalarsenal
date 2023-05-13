package me.km127pl.elementalarsenal.items.types;

import me.km127pl.elementalarsenal.ElementalArsenal;
import me.km127pl.elementalarsenal.items.ItemRarity;
import me.km127pl.elementalarsenal.items.ItemType;
import me.km127pl.elementalarsenal.items.abilities.ItemAbility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ItemBase {
	public static String ID = "BASE_ITEM";

	private final Material material;
	public String id;
	private String name;
	//	private ArrayList<Component> lore;
	private ItemRarity rarity;
	private ItemType type;

	public ItemBase(Material material, String name/*, ArrayList<Component> lore*/, ItemRarity rarity, ItemType type, String id) {
		this.material = material;
		this.name = name;
//		this.lore = lore;
		this.rarity = rarity;
		this.type = type;
		this.id = id;
	}

	public ArrayList<ItemAbility> getAbilities() {
		return new ArrayList<>();
	}

	public double getDamage() {
		return 1.0;
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

		if (this.glint()) {
			meta.addEnchant(Enchantment.OXYGEN, 1, true);
		}

		List<Component> _lore = new ArrayList<>();

		if (getDamage() != 0.0)
			_lore.add((Component.text("Damage: ").color(TextColor.color(113, 113, 113)))
					.append(Component.text("+" + this.getDamage()).color(TextColor.color(255, 62, 51))).decoration(TextDecoration.ITALIC, false));
		_lore.add(Component.text(" "));

		this.getAbilities().forEach((ability) -> {
			_lore.addAll(ability.getAsLore());
			_lore.add(Component.text(" "));
		});

		_lore.add(this.rarity.asComponent());


		meta.lore(_lore);
		item.setItemMeta(meta);
		return item;
	}

	public Material getMaterial() {
		return material;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean glint() {
		return false;
	}

	public ItemRarity getRarity() {
		return rarity;
	}

	public void setRarity(ItemRarity rarity) {
		this.rarity = rarity;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public void onRightClick(PlayerInteractEvent event) {
	}

	public void onLeftClick(PlayerInteractEvent event) {
	}

	public void onArrowHit(ProjectileHitEvent event, Player shooter) {
	}

	public void sendActionbar(Player player, Component message) {
		player.sendActionBar(message);
	}

}
