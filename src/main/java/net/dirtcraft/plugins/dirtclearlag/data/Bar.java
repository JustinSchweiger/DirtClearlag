package net.dirtcraft.plugins.dirtclearlag.data;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Bar {
	private final BossBar bossBar;

	public Bar() {
		bossBar = Bukkit.createBossBar("", BarColor.PURPLE, BarStyle.SOLID);
		bossBar.setVisible(false);
		bossBar.setProgress(1.0);
	}

	public void setVisibility(boolean visible) {
		bossBar.setVisible(visible);
	}

	public void setTitle(String title) {
		bossBar.setTitle(title);
	}

	public void setProgress(double progress) {
		bossBar.setProgress(progress);
	}

	public void setColor(BarColor color) {
		bossBar.setColor(color);
	}

	public void addPlayer(Player player) {
		bossBar.addPlayer(player);
	}

	public BossBar getBossBar() {
		return bossBar;
	}
}
