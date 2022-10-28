package net.dirtcraft.plugins.dirtclearlag.utils;

import org.bukkit.ChatColor;

public class Strings {
	// ---------------------------------------------------------- GENERAL ----------------------------------------------------------
	public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.RED + "Dirt" + ChatColor.DARK_PURPLE + "Clearlag" + ChatColor.GRAY + "] ";
	public static final String INTERNAL_PREFIX = ChatColor.GRAY + "[" + ChatColor.RED + "Dirt" + ChatColor.DARK_PURPLE + "Clearlag" + ChatColor.GRAY + "] ";
	public static final String NO_PERMISSION = PREFIX + ChatColor.RED + "You do not have permission to use this command.\n";
	public static final String RELOAD = PREFIX + ChatColor.GREEN + "Config reloaded successfully.";
	public static final String BAR_TOP = Utilities.format("&x&f&b&7&9&0&0&m-&x&f&b&7&0&0&0&m-&x&f&a&6&8&0&0&m-&x&f&a&5&f&0&0&m-&x&f&a&5&6&0&0&m-&x&f&a&4&e&0&0&m-&x&f&9&4&5&0&0&m-&x&f&9&3&d&0&0&m-&x&f&9&3&4&0&0&m-&x&f&8&2&b&0&0&m-&x&f&8&2&3&0&0&m-&x&f&8&1&a&0&0&m-&x&f&8&1&1&0&0&m-&x&f&7&0&9&0&0&m-&x&f&7&0&0&0&0&m-" + ChatColor.GRAY + "[ " + ChatColor.RED + "DirtCraft " + ChatColor.DARK_PURPLE + "Clearlag" + ChatColor.RESET + ChatColor.GRAY + " ]" + "&x&f&7&0&0&0&0&m-&x&f&7&0&9&0&0&m-&x&f&8&1&1&0&0&m-&x&f&8&1&a&0&0&m-&x&f&8&2&3&0&0&m-&x&f&8&2&b&0&0&m-&x&f&9&3&4&0&0&m-&x&f&9&3&d&0&0&m-&x&f&9&4&5&0&0&m-&x&f&a&4&e&0&0&m-&x&f&a&5&6&0&0&m-&x&f&a&5&f&0&0&m-&x&f&a&6&8&0&0&m-&x&f&b&7&0&0&0&m-&x&f&b&7&9&0&0&m-");
	public static final String BAR_BOTTOM = Utilities.format("&x&f&b&7&9&0&0&m-&x&f&b&7&4&0&0&m-&x&f&b&6&e&0&0&m-&x&f&a&6&9&0&0&m-&x&f&a&6&4&0&0&m-&x&f&a&5&f&0&0&m-&x&f&a&5&9&0&0&m-&x&f&a&5&4&0&0&m-&x&f&a&4&f&0&0&m-&x&f&9&4&a&0&0&m-&x&f&9&4&4&0&0&m-&x&f&9&3&f&0&0&m-&x&f&9&3&a&0&0&m-&x&f&9&3&5&0&0&m-&x&f&9&2&f&0&0&m-&x&f&8&2&a&0&0&m-&x&f&8&2&5&0&0&m-&x&f&8&2&0&0&0&m-&x&f&8&1&a&0&0&m-&x&f&8&1&5&0&0&m-&x&f&8&1&0&0&0&m-&x&f&7&0&b&0&0&m-&x&f&7&0&5&0&0&m-&x&f&7&0&0&0&0&m-&x&f&7&0&0&0&0&m-&x&f&7&0&5&0&0&m-&x&f&7&0&b&0&0&m-&x&f&8&1&0&0&0&m-&x&f&8&1&5&0&0&m-&x&f&8&1&a&0&0&m-&x&f&8&2&0&0&0&m-&x&f&8&2&5&0&0&m-&x&f&8&2&a&0&0&m-&x&f&9&2&f&0&0&m-&x&f&9&3&5&0&0&m-&x&f&9&3&a&0&0&m-&x&f&9&3&f&0&0&m-&x&f&9&4&4&0&0&m-&x&f&9&4&a&0&0&m-&x&f&a&4&f&0&0&m-&x&f&a&5&4&0&0&m-&x&f&a&5&9&0&0&m-&x&f&a&5&f&0&0&m-&x&f&a&6&4&0&0&m-&x&f&a&6&9&0&0&m-&x&f&b&6&e&0&0&m-&x&f&b&7&4&0&0&m-&x&f&b&7&9&0&0&m-");
	public static final String HELP_ADD = ChatColor.GOLD + "  /dcl " + ChatColor.YELLOW + "add <item>\n";
	public static final String HELP_LIST = ChatColor.GOLD + "  /dcl " + ChatColor.YELLOW + "list [page]\n";
	public static final String HELP_REMOVE = ChatColor.GOLD + "  /dcl " + ChatColor.YELLOW + "remove <item>\n";
	public static final String HELP_RELOAD = ChatColor.GOLD + "  /dcl " + ChatColor.YELLOW + "reload\n";
	public static final String INVALID_ARGUMENTS_CONSOLE = PREFIX + ChatColor.RED + "Invalid number of arguments for console usage!";
	public static final String INVALID_ITEM = PREFIX + ChatColor.RED + "Invalid item " + ChatColor.DARK_RED + "{ITEM}" + ChatColor.RED + "!";
	public static final String REMINDER = PREFIX + ChatColor.GRAY + "Ground items will be cleared in " + ChatColor.RED + "{TIME}" + ChatColor.GRAY + " seconds!";
	public static final String ITEMS_CLEARED = PREFIX + ChatColor.GRAY + "Cleared " + ChatColor.AQUA + "{AMOUNT}" + ChatColor.GRAY + " items!";
	public static final String BAR_TITLE = ChatColor.GRAY + "Clearing items in " + ChatColor.RED + "{TIME}" + ChatColor.GRAY + " seconds!";
	public static final String BAR_TITLE_COUNTDOWN = ChatColor.GRAY + "Clearing items in " + ChatColor.DARK_RED + "{TIME}" + ChatColor.GRAY + " seconds!";
	public static final String ITEM_WHITELISTED = PREFIX + ChatColor.GRAY + "Whitelisted item " + ChatColor.AQUA + "{ITEM}" + ChatColor.GRAY + ".";
	public static final String ITEM_ALREADY_WHITELISTED = PREFIX + ChatColor.GRAY + "Item " + ChatColor.AQUA + "{ITEM}" + ChatColor.GRAY + " is already whitelisted!";
	public static final String INVALID_ARGUMENTS = PREFIX + ChatColor.RED + "Invalid number of arguments!";
	public static final String ITEM_NOT_WHITELISTED = PREFIX + ChatColor.GRAY + "Item " + ChatColor.AQUA + "{ITEM}" + ChatColor.GRAY + " is not whitelisted!";
	public static final String ITEM_NO_LONGER_WHITELISTED = PREFIX + ChatColor.GRAY + "Item " + ChatColor.AQUA + "{ITEM}" + ChatColor.GRAY + " is no longer whitelisted!";
	public static final String NO_CONSOLE = PREFIX + ChatColor.RED + "This command cannot be used from the console!";
	public static final String INVALID_ARGUMENTS_USAGE = PREFIX + ChatColor.RED + "Invalid number of arguments! Usage: ";
	public static final String NO_WHITELISTED_ITEMS = PREFIX + ChatColor.GRAY + "There are no whitelisted items!";
	public static final String CLICK_TO_REMOVE = ChatColor.GRAY + "Click to remove from the whitelist!";
}
