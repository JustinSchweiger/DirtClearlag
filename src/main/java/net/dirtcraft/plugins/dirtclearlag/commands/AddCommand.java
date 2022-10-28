package net.dirtcraft.plugins.dirtclearlag.commands;

import net.dirtcraft.plugins.dirtclearlag.data.ItemManager;
import net.dirtcraft.plugins.dirtclearlag.database.DatabaseOperations;
import net.dirtcraft.plugins.dirtclearlag.database.callbacks.HandleItemCallback;
import net.dirtcraft.plugins.dirtclearlag.utils.Permissions;
import net.dirtcraft.plugins.dirtclearlag.utils.Strings;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddCommand {
	public static boolean run(CommandSender sender, String[] args) {
		if (!sender.hasPermission(Permissions.ADD)) {
			sender.sendMessage(Strings.NO_PERMISSION);
			return true;
		}

		if (args.length < 2 && !(sender instanceof Player)) {
			sender.sendMessage(Strings.INVALID_ARGUMENTS_CONSOLE);
			return true;
		}

		int argsLen = args.length;

		NamespacedKey namespacedKey;
		if (argsLen == 3) {
			namespacedKey = NamespacedKey.fromString(args[1]);
		} else {
			namespacedKey = ((Player) sender).getInventory().getItemInMainHand().getType().getKey();
		}

		if (namespacedKey == null) {
			sender.sendMessage(Strings.INVALID_ITEM.replace("{ITEM}", args[1]));
			return true;
		}

		DatabaseOperations.addItem(namespacedKey, sender.getName(), new HandleItemCallback() {
			@Override
			public void onSuccess() {
				sender.sendMessage(Strings.ITEM_WHITELISTED.replace("{ITEM}", namespacedKey.toString()));
				ItemManager.addWhitelistedItem(namespacedKey);
			}

			@Override
			public void onFailure() {
				sender.sendMessage(Strings.ITEM_ALREADY_WHITELISTED.replace("{ITEM}", namespacedKey.toString()));
			}
		});

		return true;
	}
}
