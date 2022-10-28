package net.dirtcraft.plugins.dirtclearlag;

import net.dirtcraft.plugins.dirtclearlag.data.ItemManager;
import net.dirtcraft.plugins.dirtclearlag.database.Database;
import net.dirtcraft.plugins.dirtclearlag.utils.Utilities;
import org.bukkit.plugin.java.JavaPlugin;

public final class DirtClearlag extends JavaPlugin {

	private static DirtClearlag plugin;

	public static DirtClearlag getPlugin() {
		return plugin;
	}

	@Override
	public void onEnable() {
		plugin = this;
		Utilities.loadConfig();
		Database.initialiseDatabase();
		ItemManager.initManager();
		Utilities.registerCommands();
		Utilities.registerListeners();
	}

	@Override
	public void onDisable() {
		Database.closeDatabase();
	}
}
