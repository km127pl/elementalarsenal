package me.km127pl.elementalarsenal;

import co.aikar.commands.PaperCommandManager;
import me.km127pl.elementalarsenal.commands.ItemCommand;
import me.km127pl.elementalarsenal.items.ItemManager;
import me.km127pl.elementalarsenal.items.custom.*;
import me.km127pl.elementalarsenal.listeners.ItemAction;
import me.km127pl.elementalarsenal.listeners.ItemArrowHit;
import me.km127pl.elementalarsenal.listeners.ItemBlock;
import me.km127pl.elementalarsenal.listeners.ItemDamage;
import me.km127pl.elementalarsenal.ui.ItemGui;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElementalArsenal extends JavaPlugin {

	PluginManager pm;

	public static ElementalArsenal getPlugin() {
		return ElementalArsenal.getPlugin(ElementalArsenal.class);
	}

	@Override
	public void onEnable() {
		pm = this.getServer().getPluginManager();

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

		pm.registerEvents(new ItemAction(), this);
		pm.registerEvents(new ItemDamage(), this);
		pm.registerEvents(new ItemArrowHit(), this);
		pm.registerEvents(new ItemBlock(), this);
		pm.registerEvents(new ItemGui(), this);

		manager.getCommandCompletions().registerAsyncCompletion("items", c -> ItemManager.items.keySet());
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
