package me.km127pl.elementalarsenal.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import me.km127pl.elementalarsenal.items.ItemBase;
import me.km127pl.elementalarsenal.items.ItemManager;
import me.km127pl.elementalarsenal.ui.ItemGui;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

@CommandAlias("ea|elementalarsenal")
public class ItemCommand extends BaseCommand {

	@Default
	@CommandCompletion("give")
	@CommandPermission("elementalarsenal.admin")
	public static void onMainCommand(Player player) {
		// create a new item gui and show it
		ItemGui gui = new ItemGui();
		gui.openGui(player);
	}

	@Subcommand("give")
	@CommandCompletion("@items @players")
	public static void onGiveCommand(Player player, String itemName, @Optional OnlinePlayer onlinePlayer) {
		if (onlinePlayer == null) {
			// give the item to the player
			onGiveCommand(player, itemName, new OnlinePlayer(player));
			return;
		}

		if (!ItemManager.items.containsKey(itemName)) {
			player.sendMessage(Messages.ITEM_DOES_NOT_EXIST.getFormattedMessage());
			return;
		}

		ItemBase item = ItemManager.items.get(itemName);

		player.getInventory().addItem(item.getItem());
		player.sendMessage(Messages.ITEM_GIVEN.getFormattedMessage(item.getName()));
	}
}
