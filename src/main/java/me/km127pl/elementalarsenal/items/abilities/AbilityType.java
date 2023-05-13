package me.km127pl.elementalarsenal.items.abilities;

public enum AbilityType {
	RIGHT_CLICK("RIGHT CLICK"),
	LEFT_CLICK("LEFT CLICK"),
	PASSIVE(""),
	FULL_SET("FULL SET BONUS"),
	SHIFT_RIGHT_CLICK("SHIFT RIGHT CLICK"),
	SHIFT("SHIFT"),
	SHIFT_LEFT_CLICK("SHIFT LEFT CLICK");

	public final String name;

	AbilityType(String name) {
		this.name = name;
	}
}
