package me.km127pl.elementalarsenal.items.abilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemAbility {

	private AbilityType type;
	private String name;
	private String description;
	private String cost;

	public ItemAbility(AbilityType type, String name, String description, String cost) {
		this.type = type;
		this.name = name;
		this.description = description;
		this.cost = cost;
	}

	public ArrayList<Component> getAsLore() {
		ArrayList<Component> list = new ArrayList<>();

		list.add(Component.text("Item Ability: " + this.name + " ").color(TextColor.color(255, 125, 0))
				.append(Component.text(this.type.name).color(TextColor.color(255, 208, 11)).decorate(TextDecoration.BOLD)).decoration(TextDecoration.ITALIC, false));

		Arrays.stream(description.split("\n")).iterator().forEachRemaining((line) -> {
			list.add(Component.text(line).color(TextColor.color(137, 137, 137)).decoration(TextDecoration.ITALIC, false));
		});

		list.add(Component.text("Cost: ").color(TextColor.color(100, 100, 100))
				.append(Component.text(cost).color(TextColor.color(30, 110, 150))).decoration(TextDecoration.ITALIC, false));

		return list;
	}

	public AbilityType getType() {
		return type;
	}

	public void setType(AbilityType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
}
