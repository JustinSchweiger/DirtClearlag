package net.dirtcraft.plugins.dirtclearlag.commands;

import net.dirtcraft.plugins.dirtclearlag.data.ItemManager;
import net.dirtcraft.plugins.dirtclearlag.database.DatabaseOperations;
import net.dirtcraft.plugins.dirtclearlag.database.callbacks.HandleItemCallback;
import net.dirtcraft.plugins.dirtclearlag.utils.Permissions;
import net.dirtcraft.plugins.dirtclearlag.utils.Strings;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;

public class RemoveCommand {
	public static boolean run(CommandSender sender, String[] args) {
		if (!sender.hasPermission(Permissions.REMOVE)) {
			sender.sendMessage(Strings.NO_PERMISSION);
			return true;
		}

		if (args.length != 2) {
			sender.sendMessage(Strings.INVALID_ARGUMENTS);
			return true;
		}

		NamespacedKey namespacedKey = NamespacedKey.fromString(args[1]);

		if (namespacedKey == null) {
			sender.sendMessage(Strings.INVALID_ITEM.replace("{ITEM}", args[2]));
			return true;
		}

		if (!ItemManager.isWhitelisted(namespacedKey)) {
			sender.sendMessage(Strings.ITEM_NOT_WHITELISTED.replace("{ITEM}", args[1]));
			return true;
		}

		DatabaseOperations.removeItem(namespacedKey, new HandleItemCallback() {
			@Override
			public void onSuccess() {
				sender.sendMessage(Strings.ITEM_NO_LONGER_WHITELISTED.replace("{ITEM}", namespacedKey.toString()));
				ItemManager.removeWhitelistedItem(namespacedKey);
			}

			@Override
			public void onFailure() {
				sender.sendMessage(Strings.ITEM_NOT_WHITELISTED.replace("{ITEM}", namespacedKey.toString()));
			}
		});

		return true;
	}
}
