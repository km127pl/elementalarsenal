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

@CommandAlias("ea|elementalarsenal")
public class ItemCommand extends BaseCommand {

	@Default
	@CommandCompletion("give|debug")
	@CommandPermission("elementalarsenal.admin")
	public static void onMainCommand(Player player) {
		// create a new gui with 6 rows and the title "Elemental Arsenal"
		// filled with the item icons
		// when an item is clicked, run the command /ea give <item> <player>

		ItemGui gui = new ItemGui();
		gui.openGui(player);

		player.sendMessage(Messages.NOT_IMPLEMENTED.getFormattedMessage());
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
		player.sendMessage(Messages.ITEM_GIVEN.getFormattedMessage(String.valueOf(item.getItem().getItemMeta().displayName())));
	}

	@Subcommand("debug")
	public static void onDebug(Player player) {
		ItemStack holding = player.getActiveItem();
		ItemMeta meta = holding.getItemMeta();
		if (meta == null) return;

		player.sendMessage(ItemManager.getIdFrom(holding));
	}
}
