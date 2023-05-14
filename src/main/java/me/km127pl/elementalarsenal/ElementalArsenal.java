package me.km127pl.elementalarsenal;

import co.aikar.commands.PaperCommandManager;
import me.km127pl.elementalarsenal.commands.ItemCommand;
import me.km127pl.elementalarsenal.items.ItemManager;
import me.km127pl.elementalarsenal.items.custom.armor.*;
import me.km127pl.elementalarsenal.items.custom.bows.*;
import me.km127pl.elementalarsenal.items.custom.swords.*;
import me.km127pl.elementalarsenal.items.custom.wands.*;
import me.km127pl.elementalarsenal.listeners.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElementalArsenal extends JavaPlugin {

	public static NamespacedKey key = new NamespacedKey("itemsmanager", "item_id");
	public static FileConfiguration configuration;
	public static ItemStack menuFiller;
	PluginManager pm;

	public static ElementalArsenal getPlugin() {
		return ElementalArsenal.getPlugin(ElementalArsenal.class);
	}

	@Override
	public void onEnable() {
		pm = this.getServer().getPluginManager();
		this.saveDefaultConfig();
		configuration = this.getConfig();

		PaperCommandManager manager = new PaperCommandManager(this);
		manager.registerCommand(new ItemCommand());

		ItemManager.registerItem(AOTE.ID, new AOTE());
		ItemManager.registerItem(AOTV.ID, new AOTV());
		ItemManager.registerItem(ExplosiveBow.ID, new ExplosiveBow());
		ItemManager.registerItem(InkWand.ID, new InkWand());
		ItemManager.registerItem(VerdantWand.ID, new VerdantWand());
		ItemManager.registerItem(RadianceWand.ID, new RadianceWand());
		ItemManager.registerItem(RedstoneWand.ID, new RedstoneWand());
		ItemManager.registerItem(DebugWand.ID, new DebugWand());
		ItemManager.registerItem(InfernoWand.ID, new InfernoWand());

		ItemManager.registerItem(RocketBoots.ID, new RocketBoots());

		pm.registerEvents(new ItemAction(), this);
		pm.registerEvents(new ItemDamage(), this);
		pm.registerEvents(new ItemArrowHit(), this);
		pm.registerEvents(new ItemBlock(), this);
		pm.registerEvents(new ArmorEvents(), this);

		menuFiller = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta fillerMeta = menuFiller.getItemMeta();
		fillerMeta.displayName(Component.text(" "));
		menuFiller.setItemMeta(fillerMeta);

		manager.getCommandCompletions().registerAsyncCompletion("items", c -> ItemManager.items.keySet());
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
