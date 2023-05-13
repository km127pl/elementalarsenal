package me.km127pl.elementalarsenal.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public enum Messages {

	NOT_IMPLEMENTED("This command is not implemented yet", TextColor.fromHexString("#ff3c15")),
	ITEM_DOES_NOT_EXIST("This item does not exist", TextColor.fromHexString("#ff3510")),
	ITEM_GIVEN("You have been given %1", TextColor.fromHexString("#38ff47")),
	ITEM_GIVEN_TO("You have given %1 to %2", TextColor.fromHexString("#38ff47")),
	ITEM_GIVEN_TO_YOU("You have been given %1 by %2", TextColor.fromHexString("#38ff47")),
	YOU_CANNOT_PUT_ANVIL("You cannot put this item in the anvil", TextColor.fromHexString("#ff3510")),
	ITEM_REPAIRED("You have repaired %1", TextColor.fromHexString("#38ff47"));

	final String message;
	final TextColor color;

	Messages(String message, TextColor color) {
		this.message = message;
		this.color = color;
	}

	public String getMessage() {
		return message;
	}

	public TextColor getColor() {
		return color;
	}

	public Component getFormattedMessage() {
		return Component.text(message).color(color);
	}

	public Component getFormattedMessage(String... args) {
		String message = this.message;
		for (int i = 0; i < args.length; i++) {
			message = message.replace("%" + i, args[i]);
		}
		return Component.text(message).color(color);
	}
}
