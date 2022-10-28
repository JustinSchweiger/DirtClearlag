package net.dirtcraft.plugins.dirtclearlag.commands;

import net.dirtcraft.plugins.dirtclearlag.data.ItemManager;
import net.dirtcraft.plugins.dirtclearlag.utils.Permissions;
import net.dirtcraft.plugins.dirtclearlag.utils.Strings;
import net.dirtcraft.plugins.dirtclearlag.utils.Utilities;
import org.bukkit.command.CommandSender;

public class ReloadCommand {
	public static boolean run(CommandSender sender, String[] args) {
		if (!sender.hasPermission(Permissions.RELOAD)) {
			sender.sendMessage(Strings.NO_PERMISSION);
			return true;
		}

		Utilities.loadConfig();
		ItemManager.restartScheduler();
		sender.sendMessage(Strings.RELOAD);

		return true;
	}
}
