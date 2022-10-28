package net.dirtcraft.plugins.dirtclearlag.listeners;

import net.dirtcraft.plugins.dirtclearlag.data.Bar;
import net.dirtcraft.plugins.dirtclearlag.data.ItemManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Bar bar = ItemManager.getBossBar();

		if (!bar.getBossBar().getPlayers().contains(event.getPlayer())) {
			bar.addPlayer(event.getPlayer());
		}
	}

	public void onPlayerLeave(PlayerQuitEvent event) {
		Bar bar = ItemManager.getBossBar();

		if (bar.getBossBar().getPlayers().contains(event.getPlayer())) {
			bar.getBossBar().removePlayer(event.getPlayer());
		}
	}
}
