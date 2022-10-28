package net.dirtcraft.plugins.dirtclearlag.commands;

import net.dirtcraft.plugins.dirtclearlag.data.ItemManager;
import net.dirtcraft.plugins.dirtclearlag.utils.Permissions;
import net.dirtcraft.plugins.dirtclearlag.utils.Strings;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BaseCommand implements CommandExecutor, TabCompleter {
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (!sender.hasPermission(Permissions.BASE)) {
			sender.sendMessage(Strings.NO_PERMISSION);
			return true;
		}

		if (args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase("help"))) {
			ArrayList<String> listings = getListings(sender);
			sender.sendMessage(Strings.BAR_TOP);
			sender.sendMessage("");
			for (String listing : listings) {
				sender.sendMessage(listing);
			}
			sender.sendMessage("");
			sender.sendMessage(Strings.BAR_BOTTOM);
			return true;
		}

		String arg = args[0].toLowerCase();

		switch (arg) {
			case "list":
				return ListCommand.run(sender, args);
			case "add":
				return AddCommand.run(sender, args);
			case "remove":
				return RemoveCommand.run(sender, args);
			case "reload":
				return ReloadCommand.run(sender, args);
		}

		return true;
	}

	private ArrayList<String> getListings(CommandSender sender) {
		ArrayList<String> listings = new ArrayList<>();

		if (sender.hasPermission(Permissions.LIST)) {
			listings.add(Strings.HELP_LIST);
		}

		if (sender.hasPermission(Permissions.ADD)) {
			listings.add(Strings.HELP_ADD);
		}

		if (sender.hasPermission(Permissions.REMOVE)) {
			listings.add(Strings.HELP_REMOVE);
		}

		if (sender.hasPermission(Permissions.RELOAD)) {
			listings.add(Strings.HELP_RELOAD);
		}

		return listings;
	}

	@Nullable
	@Override
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
		List<String> arguments = new ArrayList<>();

		if (args.length == 1) {
			if (sender.hasPermission(Permissions.LIST)) {
				arguments.add("list");
			}

			if (sender.hasPermission(Permissions.ADD)) {
				arguments.add("add");
			}

			if (sender.hasPermission(Permissions.REMOVE)) {
				arguments.add("remove");
			}

			if (sender.hasPermission(Permissions.RELOAD)) {
				arguments.add("reload");
			}
		} else if (args.length == 2 && args[0].equalsIgnoreCase("add")) {
			arguments.add("[namespace:key|item in your hand]");
		} else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
			for (NamespacedKey key : ItemManager.getWhitelistedItems()) {
				arguments.add(key.toString());
			}
		}

		List<String> tabResults = new ArrayList<>();
		for (String argument : arguments) {
			if (argument.toLowerCase().startsWith(args[args.length - 1].toLowerCase())) {
				tabResults.add(argument);
			}
		}

		return tabResults;
	}
}
