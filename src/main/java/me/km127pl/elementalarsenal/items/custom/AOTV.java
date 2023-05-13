package me.km127pl.elementalarsenal.items.custom;

import me.km127pl.elementalarsenal.items.ItemBase;
import me.km127pl.elementalarsenal.items.ItemRarity;
import me.km127pl.elementalarsenal.items.ItemType;
import me.km127pl.elementalarsenal.items.abilities.AbilityType;
import me.km127pl.elementalarsenal.items.abilities.ItemAbility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class AOTV extends ItemBase {
	public static String ID = "ASPECT_OF_THE_VOID";

	public AOTV() {
		super(Material.DIAMOND_SHOVEL, "Aspect of The Void", ItemRarity.EPIC, ItemType.SWORD, ID);
	}

	@Override
	public boolean glint() {
		return true;
	}

	@Override
	@SuppressWarnings("Deprecated")
	public void onRightClick(PlayerInteractEvent event) {
//		event.getPlayer().sendMessage("you have right clicked!");
		Player player = event.getPlayer();

		Location location = getLocationInFront(player);
		if (location == null || location.equals(player.getLocation())) {
			sendActionbar(player, Component.text("You can't teleport there!").color(TextColor.color(200, 61, 43)));
			return;
		}
		location.setDirection(player.getLocation().getDirection());
		sendActionbar(player, Component.text("Ability used - ").color(TextColor.color(255, 125, 0))
				.append(Component.text(this.getAbilities().get(0).getName()).color(TextColor.color(255, 208, 11))).decoration(TextDecoration.ITALIC, false));


//		Vibration vibration = new Vibration(player.getLocation(), new Vibration.Destination.BlockDestination(location), 10);
//		player.getWorld().spawnParticle(Particle.VIBRATION, player.getLocation(), 20, vibration);
		double red = 0 / 255D;
		double green = 127 / 255D;
		double blue = 255 / 255D;
		player.spawnParticle(Particle.SPELL_MOB, player.getLocation(), 20, red, green, blue, 1);
		player.teleport(location);
		player.spawnParticle(Particle.SPELL_MOB, player.getLocation(), 20, red, green, blue, 1);
		event.setCancelled(true);

	}

	@Override
	public double getDamage() {
		return 14.0;
	}

	@Override
	public ArrayList<ItemAbility> getAbilities() {
		ArrayList<ItemAbility> abilities = new ArrayList<>();
		abilities.add(new ItemAbility(
				AbilityType.RIGHT_CLICK,
				"Transmission",
				"Teleports you up to 10 blocks away",
				"0"
		));

		return abilities;
	}

	public Location getLocationInFront(Player player) {
		Location location = player.getLocation();
		Location _tempLocation = location.clone();
		Vector direction = location.getDirection().normalize();

		// check for collisions
		int dist = 0;
		for (int i = 2; i < 10; i++) {
			if (!_tempLocation.clone().add(direction.clone().multiply(i)).getBlock().getType().isAir()) break;
			dist++;
		}
		if (dist == 0) return null;
		location.add(direction.multiply(dist)).add(0, 1, 0);
		if (location.getBlockY() > 320) return null;

		return location;
	}


}
