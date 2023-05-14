package me.km127pl.elementalarsenal.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.util.Mask;
import me.km127pl.elementalarsenal.ElementalArsenal;
import me.km127pl.elementalarsenal.items.ItemManager;
import me.km127pl.elementalarsenal.items.types.ItemBase;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.ApiStatus;

import java.util.Objects;

@CommandAlias("ea|elementalarsenal")
public class ItemCommand extends BaseCommand {

	@Default
	@CommandCompletion("give|crafting")
	@CommandPermission("elementalarsenal.admin")
	public static void onMainCommand(Player player) {
		// create a new item menu
		ChestGui gui = new ChestGui(6, "Elemental Arsenal");
		OutlinePane items = new OutlinePane(1, 1, 7, 4);
		OutlinePane border = new OutlinePane(0, 0, 9, 6);

		// add items to the menu
		for (ItemBase item : ItemManager.items.values()) {
			items.addItem(new GuiItem(item.getItem(), event -> {
				player.getInventory().addItem(item.getItem());
				event.setCancelled(true);
			}));
		}

		// outline the gui
		border.applyMask(new Mask("111111111",
				"100000001",
				"100000001",
				"100000001",
				"100000001",
				"111111111"));
		border.setRepeat(true);

		border.addItem(new GuiItem(ElementalArsenal.menuFiller, event -> event.setCancelled(true)));


		// show all items
		gui.addPane(border);
		gui.addPane(items);
		gui.show(player);
	}

	@Subcommand("give")
	@CommandCompletion("@items @players")
	@CommandPermission("elementalarsenal.give")
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

	@Subcommand("crafting")
	@CommandPermission("elementalarsenal.crafting")
	@ApiStatus.Experimental
	@Description("Opens the crafting menu, not yet implemented completely")
	public static void onCraftingCommand(Player player) {
		// create a new crafting menu
		ChestGui gui = new ChestGui(6, "Elemental Crafting");
		OutlinePane background = new OutlinePane(0, 0, 9, 6);
		OutlinePane crafting = new OutlinePane(1, 1, 3, 3);
		OutlinePane result = new OutlinePane(6, 2, 1, 1);

		// set the background
		background.setRepeat(true);
		background.applyMask(new Mask("111111111",
				"100011111",
				"100011011",
				"100011111",
				"111111111",
				"111111111"));
		background.addItem(new GuiItem(ElementalArsenal.menuFiller, event -> event.setCancelled(true)));


		// clear the crafting grid
//		crafting.addItem(new GuiItem(new ItemStack(Material.WHITE_STAINED_GLASS_PANE), event -> event.setCancelled(true)));
		for (int i = 0; i < 9; i++) {
			crafting.addItem(new GuiItem(new ItemStack(Material.WHITE_STAINED_GLASS_PANE)));
		}

		crafting.setOnClick((event) -> {
			int slot = event.getSlot();
			if (Objects.requireNonNull(event.getCurrentItem()).getType() == Material.WHITE_STAINED_GLASS_PANE) {
				event.setCurrentItem(new ItemStack(Material.RED_STAINED_GLASS_PANE));
			} else {
				event.setCurrentItem(new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
			}
		});
		// set the result slot
		ItemStack resultIcon = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemMeta resultMeta = resultIcon.getItemMeta();
		resultMeta.displayName(Component.text("Result").color(TextColor.fromHexString("#FFBBBB")).decoration(TextDecoration.ITALIC, false));
		resultIcon.setItemMeta(resultMeta);
		result.addItem(new GuiItem(resultIcon, event -> {
			event.setCancelled(true);
		}));

		// show the menu
		gui.addPane(background);
		gui.addPane(crafting);
		gui.addPane(result);
		gui.show(player);
	}
}
