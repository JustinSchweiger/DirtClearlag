package net.dirtcraft.plugins.dirtclearlag.utils;

import com.moandjiezana.toml.Toml;
import net.dirtcraft.plugins.dirtclearlag.DirtClearlag;
import net.dirtcraft.plugins.dirtclearlag.commands.BaseCommand;
import net.dirtcraft.plugins.dirtclearlag.config.Config;
import net.dirtcraft.plugins.dirtclearlag.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;

public class Utilities {
	public static Config config;

	public static String format(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static void loadConfig() {
		if (!DirtClearlag.getPlugin().getDataFolder().exists()) {
			DirtClearlag.getPlugin().getDataFolder().mkdirs();
		}
		File file = new File(DirtClearlag.getPlugin().getDataFolder(), "config.toml");
		if (!file.exists()) {
			try {
				Files.copy(DirtClearlag.getPlugin().getResource("config.toml"), file.toPath());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		config = new Toml(new Toml().read(DirtClearlag.getPlugin().getResource("config.toml"))).read(file).to(Config.class);
	}

	public static void registerCommands() {
		DirtClearlag.getPlugin().getCommand("dirtclearlag").setExecutor(new BaseCommand());
		DirtClearlag.getPlugin().getCommand("dirtclearlag").setTabCompleter(new BaseCommand());
	}

	public static void registerListeners() {
		DirtClearlag.getPlugin().getServer().getPluginManager().registerEvents(new PlayerListener(), DirtClearlag.getPlugin());
	}

	public static void log(Level level, String msg) {
		String consoleMessage;
		if (Level.INFO.equals(level)) {
			consoleMessage = Strings.INTERNAL_PREFIX + ChatColor.WHITE + msg;
		} else if (Level.WARNING.equals(level)) {
			consoleMessage = Strings.INTERNAL_PREFIX + ChatColor.YELLOW + msg;
		} else if (Level.SEVERE.equals(level)) {
			consoleMessage = Strings.INTERNAL_PREFIX + ChatColor.RED + msg;
		} else {
			consoleMessage = Strings.INTERNAL_PREFIX + ChatColor.GRAY + msg;
		}

		if (!config.general.coloredDebug) {
			consoleMessage = ChatColor.stripColor(msg);
		}

		DirtClearlag.getPlugin().getServer().getConsoleSender().sendMessage(consoleMessage);
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}

		return true;
	}

	public static void disablePlugin() {
		DirtClearlag.getPlugin().getServer().getPluginManager().disablePlugin(DirtClearlag.getPlugin());
	}

	public static void playClearSound() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (Utilities.config.sounds.playClearSound) {
				String sound = Utilities.config.sounds.clearSound;
				if (sound == null) {
					sound = "minecraft:block.conduit.activate";
				}
				player.playSound(player.getLocation(), sound, 1, 1);
			}
		}
	}

	public static void playCountdownSound() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (Utilities.config.sounds.playCountdownSound) {
				String sound = Utilities.config.sounds.countdownSound;
				if (sound == null) {
					sound = "minecraft:entity.experience_orb.pickup";
				}
				player.playSound(player.getLocation(), sound, 0.6F, 1);
			}
		}
	}

	public static void playReminderSound() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (Utilities.config.sounds.playReminderSound) {
				String sound = Utilities.config.sounds.reminderSound;
				if (sound == null) {
					sound = "minecraft:entity.item.pickup";
				}
				player.playSound(player.getLocation(), sound, 0.8F, 1);
			}
		}
	}
}
