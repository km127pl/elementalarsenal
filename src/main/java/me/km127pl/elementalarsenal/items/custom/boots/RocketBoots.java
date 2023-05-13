package me.km127pl.elementalarsenal.items.custom.boots;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.km127pl.elementalarsenal.items.ItemManager;
import me.km127pl.elementalarsenal.items.ItemRarity;
import me.km127pl.elementalarsenal.items.abilities.AbilityType;
import me.km127pl.elementalarsenal.items.abilities.ItemAbility;
import me.km127pl.elementalarsenal.items.types.ArmorBoots;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class RocketBoots extends ArmorBoots {
	public static final String ID = "ROCKET_BOOTS";
	public RocketBoots() {
		super(Material.LEATHER_BOOTS, "Rocket Boots", ItemRarity.RARE, ID);
	}

	@Override
	public ArrayList<ItemAbility> getAbilities() {
ArrayList<ItemAbility> abilities = new ArrayList<>();
		abilities.add(new ItemAbility(
				AbilityType.SHIFT_JUMP,
				"Boost Jump",
				"Allows the player to perform a powerful jump\nthat propels them forward, consuming some of\nthe item's durability in the process.",
				"0"
		));

		return abilities;
	}

	@Override
	public void onShiftJump(PlayerJumpEvent event) {
		// propel the player upwards
		Player player = event.getPlayer();
		Vector vector = player.getLocation().getDirection().multiply(1.3);
		player.setVelocity(vector);
		player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 2, 15, true, false));
		ItemStack boots = player.getInventory().getBoots();
		boots.setDurability((short) (boots.getDurability() + 1));
		if (boots.getDurability() > 65) {
			player.getInventory().setBoots(new ItemStack(Material.AIR));
		}

	}
}
