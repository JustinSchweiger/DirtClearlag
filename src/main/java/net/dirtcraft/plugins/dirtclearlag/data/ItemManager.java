package net.dirtcraft.plugins.dirtclearlag.data;

import net.dirtcraft.plugins.dirtclearlag.DirtClearlag;
import net.dirtcraft.plugins.dirtclearlag.database.DatabaseOperations;
import net.dirtcraft.plugins.dirtclearlag.utils.Strings;
import net.dirtcraft.plugins.dirtclearlag.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class ItemManager {
	private static BukkitTask itemScheduler;
	private static final Set<NamespacedKey> whitelistedItems = new HashSet<>();
	private static final Bar bossBar = new Bar();

	private static void startScheduler() {
		int interval = Math.max(Utilities.config.general.interval, 180);

		itemScheduler = Bukkit.getScheduler().runTaskTimerAsynchronously(DirtClearlag.getPlugin(), new Runnable() {
			int timer = interval;

			@Override
			public void run() {
				if (timer == 0) {
					Utilities.playClearSound();
					bossBar.setVisibility(false);
					bossBar.setProgress(1.0);

					timer = interval;

					Bukkit.getScheduler().runTask(DirtClearlag.getPlugin(), () -> {
						int itemsCleared = 0;
						List<World> worlds = Bukkit.getWorlds();
						for (World world : worlds) {
							Collection<Item> items = world.getEntitiesByClass(Item.class);
							for (Item item : items) {
								if (isWhitelisted(item.getItemStack().getType().getKey())) continue;

								item.remove();
								itemsCleared++;
							}
						}

						sendMessage(Strings.ITEMS_CLEARED.replace("{AMOUNT}", String.valueOf(itemsCleared)));
					});
				}

				if (timer == 180) {
					sendMessage(Strings.REMINDER.replace("{TIME}", String.valueOf(180)));
					bossBar.setVisibility(true);
					bossBar.setProgress(1.0);
					BarColor barColor;
					try {
						barColor = BarColor.valueOf(Utilities.config.bossbar.bar180Sec);
					} catch (IllegalArgumentException ignored) {
						barColor = BarColor.BLUE;
					}
					bossBar.setColor(barColor);


					for (Player player : Bukkit.getOnlinePlayers()) {
						if (bossBar.getBossBar().getPlayers().contains(player)) continue;

						bossBar.addPlayer(player);
					}

					Utilities.playReminderSound();
				} else if (timer == 60) {
					BarColor barColor;
					try {
						barColor = BarColor.valueOf(Utilities.config.bossbar.bar60Sec);
					} catch (IllegalArgumentException ignored) {
						barColor = BarColor.PURPLE;
					}
					bossBar.setColor(barColor);
					sendMessage(Strings.REMINDER.replace("{TIME}", String.valueOf(60)));
					Utilities.playReminderSound();
				} else if (timer == 30) {
					BarColor barColor;
					try {
						barColor = BarColor.valueOf(Utilities.config.bossbar.bar30Sec);
					} catch (IllegalArgumentException ignored) {
						barColor = BarColor.PINK;
					}
					bossBar.setColor(barColor);
					sendMessage(Strings.REMINDER.replace("{TIME}", String.valueOf(30)));
					Utilities.playReminderSound();
				}

				if (timer <= 180 && timer > 10) {
					bossBar.setProgress((double) timer / 180);
					bossBar.setTitle(Strings.BAR_TITLE.replace("{TIME}", String.valueOf(timer)));
				}

				if (timer == 10) {
					bossBar.setColor(BarColor.RED);
				}

				if (timer <= 10) {
					bossBar.setProgress((double) timer / 180);
					bossBar.setTitle(Strings.BAR_TITLE_COUNTDOWN.replace("{TIME}", String.valueOf(timer)));
					Utilities.playCountdownSound();
				}

				timer--;
			}
		}, 0L, 20L);
	}

	private static void sendMessage(String message) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(message);
		}
	}

	public static void restartScheduler() {
		itemScheduler.cancel();
		bossBar.setVisibility(false);
		startScheduler();
	}

	public static void initManager() {
		startScheduler();
		DatabaseOperations.getWhitelistedItems(items -> {
			for (NamespacedKey item : items) {
				addWhitelistedItem(item);
			}
		});
	}

	public static boolean isWhitelisted(NamespacedKey item) {
		return whitelistedItems.contains(item);
	}

	public static void addWhitelistedItem(NamespacedKey key) {
		whitelistedItems.add(key);
	}

	public static boolean removeWhitelistedItem(NamespacedKey key) {
		return whitelistedItems.remove(key);
	}

	public static Bar getBossBar() {
		return bossBar;
	}

	public static Set<NamespacedKey> getWhitelistedItems() {
		return whitelistedItems;
	}
}
