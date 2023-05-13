package me.km127pl.elementalarsenal.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public enum ItemRarity {
	SPECIAL("#f55142", "Special"),

	MYSTICAL("#da42f5", "Mystical"),
	LEGENDARY("#f58442", "Legendary"),
	EPIC("#9942f5", "Epic"),
	RARE("#7e72ed", "Rare"),
	UNCOMMON("#42f554", "Uncommon"),
	COMMON("#ffffff", "Common"),
	UNKNOWN("#787878", "Unknown");

	public final String color, name;

	ItemRarity(String color, String name) {
		this.color = color;
		this.name = name;
	}

	public Component asComponent() {
		return Component.text(this.name).style(Style.style().decoration(TextDecoration.BOLD, true).color(TextColor.fromHexString(this.color)).decoration(TextDecoration.ITALIC, false).build());
	}
}
