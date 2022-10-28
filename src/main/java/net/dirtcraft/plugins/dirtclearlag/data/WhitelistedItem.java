package net.dirtcraft.plugins.dirtclearlag.data;

import org.bukkit.NamespacedKey;

import java.time.LocalDateTime;

public class WhitelistedItem {
	private final NamespacedKey key;
	private final String name;
	private final LocalDateTime dateAdded;

	public WhitelistedItem(NamespacedKey key, String name, LocalDateTime dateAdded) {
		this.key = key;
		this.name = name;
		this.dateAdded = dateAdded;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getDateAdded() {
		return dateAdded;
	}

	public NamespacedKey getKey() {
		return key;
	}
}
