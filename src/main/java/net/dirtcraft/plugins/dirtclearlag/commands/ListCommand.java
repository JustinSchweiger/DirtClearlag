package net.dirtcraft.plugins.dirtclearlag.commands;

import net.dirtcraft.plugins.dirtclearlag.data.ItemManager;
import net.dirtcraft.plugins.dirtclearlag.data.WhitelistedItem;
import net.dirtcraft.plugins.dirtclearlag.database.DatabaseOperations;
import net.dirtcraft.plugins.dirtclearlag.database.callbacks.GetItemsWithExtraCallback;
import net.dirtcraft.plugins.dirtclearlag.database.callbacks.HandleItemCallback;
import net.dirtcraft.plugins.dirtclearlag.utils.Permissions;
import net.dirtcraft.plugins.dirtclearlag.utils.Strings;
import net.dirtcraft.plugins.dirtclearlag.utils.Utilities;
import net.dirtcraft.plugins.dirtclearlag.utils.gradient.GradientHandler;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ListCommand {
	public static boolean run(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Strings.NO_CONSOLE);
			return false;
		}

		if (!(sender.hasPermission(Permissions.LIST))) {
			sender.sendMessage(Strings.NO_PERMISSION);
			return true;
		}

		int page = 1;
		if (args.length == 2) {
			if (Utilities.isInteger(args[1])) {
				page = Integer.parseInt(args[1]);
			} else {
				sender.sendMessage(Strings.INVALID_ARGUMENTS_USAGE + ChatColor.RED + "/dcl list [page]");
				return true;
			}
		}

		if (ItemManager.getWhitelistedItems().size() == 0) {
			sender.sendMessage(Strings.NO_WHITELISTED_ITEMS);
			return true;
		}

		showList(sender, page);

		return true;
	}

	private static void showList(final CommandSender sender, final int page) {
		DatabaseOperations.getWhitelistedItemsWithExtra(items -> sendListToPlayer(sender, page, items));
	}

	private static void sendListToPlayer(CommandSender sender, int page, List<WhitelistedItem> whitelistedItems) {
		int listEntries = Utilities.config.general.listEntries;

		int maxPages = (int) Math.ceil((double) whitelistedItems.size() / (double) listEntries);
		if (page > maxPages) {
			sender.sendMessage(ChatColor.RED + "Index must be smaller or equal to: " + ChatColor.AQUA + maxPages);
			return;
		}

		int start = (page - 1) * listEntries;
		int end = page * listEntries;
		if (end > whitelistedItems.size()) {
			end = whitelistedItems.size();
		}

		boolean removePermission = sender.hasPermission(Permissions.REMOVE);

		sender.sendMessage(Strings.BAR_TOP);
		sender.sendMessage("");
		sender.sendMessage(ChatColor.GRAY + "Items" + ChatColor.GREEN + " whitelisted " + ChatColor.GRAY + "from clearlag:");

		for (int i = start; i < end; i++) {
			BaseComponent[] removeComponent = new ComponentBuilder("")
					.append(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "\u2715" + ChatColor.DARK_GRAY + "]")
					.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dcl remove " + whitelistedItems.get(i).getKey()))
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(Strings.CLICK_TO_REMOVE))).create();

			BaseComponent[] whitelistedItemPart = new ComponentBuilder("")
					.append(ChatColor.AQUA + whitelistedItems.get(i).getKey().toString())
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(
							ChatColor.GRAY + "Whitelisted By" + ChatColor.DARK_GRAY + ": " + ChatColor.GOLD + whitelistedItems.get(i).getName() + "\n" +
									ChatColor.GRAY + "Date Added" + ChatColor.DARK_GRAY + ": " + ChatColor.GOLD + whitelistedItems.get(i).getDateAdded().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ChatColor.GRAY + " at " + ChatColor.GOLD + whitelistedItems.get(i).getDateAdded().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
					))).create();

			BaseComponent[] entry = null;

			if (removePermission) {
				entry = new ComponentBuilder("")
						.append(removeComponent)
						.append(ChatColor.GRAY + " - ")
						.event((HoverEvent) null)
						.event((ClickEvent) null)
						.append(whitelistedItemPart)
						.create();
			} else {
				entry = whitelistedItemPart;
			}

			sender.spigot().sendMessage(entry);
		}

		TextComponent bottomBar = new TextComponent(TextComponent.fromLegacyText(GradientHandler.hsvGradient("---------------------", new java.awt.Color(251, 121, 0), new java.awt.Color(247, 0, 0), GradientHandler::linear, net.md_5.bungee.api.ChatColor.STRIKETHROUGH)));
		TextComponent pagePrev;
		if (page == 1) {
			pagePrev = new TextComponent(ChatColor.GRAY + "  \u25C0 ");
			pagePrev.setBold(true);
			pagePrev.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.RED + "You are already on the first page!")));
		} else {
			pagePrev = new TextComponent(ChatColor.GREEN + "  \u25C0 ");
			pagePrev.setBold(true);
			pagePrev.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GREEN + "Previous page")));
			pagePrev.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dcl list " + (page - 1)));
		}
		bottomBar.addExtra(pagePrev);
		TextComponent pageNext;
		if (page == maxPages) {
			pageNext = new TextComponent(ChatColor.GRAY + " \u25B6  ");
			pagePrev.setBold(true);
			pageNext.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.RED + "You are already on the last page!")));
		} else {
			pageNext = new TextComponent(ChatColor.GREEN + " \u25B6  ");
			pagePrev.setBold(true);
			pageNext.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GREEN + "Next page")));
			pageNext.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dcl list " + (page + 1)));
		}
		bottomBar.addExtra(pageNext);
		bottomBar.addExtra(new TextComponent(TextComponent.fromLegacyText(GradientHandler.hsvGradient("---------------------", new java.awt.Color(247, 0, 0), new java.awt.Color(251, 121, 0), GradientHandler::linear, net.md_5.bungee.api.ChatColor.STRIKETHROUGH))));
		sender.sendMessage("");
		sender.spigot().sendMessage(bottomBar);
	}
}
