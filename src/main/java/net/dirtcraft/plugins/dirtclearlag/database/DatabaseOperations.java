package net.dirtcraft.plugins.dirtclearlag.database;

import net.dirtcraft.plugins.dirtclearlag.DirtClearlag;
import net.dirtcraft.plugins.dirtclearlag.data.WhitelistedItem;
import net.dirtcraft.plugins.dirtclearlag.database.callbacks.GetItemsWithExtraCallback;
import net.dirtcraft.plugins.dirtclearlag.database.callbacks.HandleItemCallback;
import net.dirtcraft.plugins.dirtclearlag.database.callbacks.GetItemsCallback;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
	public static void getWhitelistedItems(final GetItemsCallback getItemsCallback) {
		Bukkit.getScheduler().runTaskAsynchronously(DirtClearlag.getPlugin(), () -> {
			try (Connection connection = Database.getConnection();
			     PreparedStatement statement = connection.prepareStatement("SELECT * FROM whitelisteditems")) {
				ResultSet resultSet = statement.executeQuery();

				List<NamespacedKey> whitelistedItems = new ArrayList<>();
				while (resultSet.next()) {
					whitelistedItems.add(NamespacedKey.fromString(resultSet.getString("namespaced_key")));
				}

				Bukkit.getScheduler().runTask(DirtClearlag.getPlugin(), () -> getItemsCallback.onSuccess(whitelistedItems));
			} catch (SQLException ignored) {}
		});
	}

	public static void addItem(final NamespacedKey namespacedKey, final String playerName, final HandleItemCallback handleItemCallback) {
		Bukkit.getScheduler().runTaskAsynchronously(DirtClearlag.getPlugin(), () -> {
			try (Connection connection = Database.getConnection();
			     PreparedStatement statement = connection.prepareStatement("INSERT INTO whitelisteditems VALUES (?, ?, ?)")) {
				statement.setString(1, namespacedKey.toString());
				statement.setString(2, playerName);
				statement.setString(3, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
				statement.executeUpdate();

				Bukkit.getScheduler().runTask(DirtClearlag.getPlugin(), handleItemCallback::onSuccess);
			} catch (SQLException ignored) {
				Bukkit.getScheduler().runTask(DirtClearlag.getPlugin(), handleItemCallback::onFailure);
			}
		});
	}

	public static void removeItem(final NamespacedKey namespacedKey, final HandleItemCallback handleItemCallback) {
		Bukkit.getScheduler().runTaskAsynchronously(DirtClearlag.getPlugin(), () -> {
			try (Connection connection = Database.getConnection();
			     PreparedStatement statement = connection.prepareStatement("DELETE FROM whitelisteditems WHERE namespaced_key = ?")) {
				statement.setString(1, namespacedKey.toString());
				statement.executeUpdate();

				Bukkit.getScheduler().runTask(DirtClearlag.getPlugin(), handleItemCallback::onSuccess);
			} catch (SQLException ignored) {
				Bukkit.getScheduler().runTask(DirtClearlag.getPlugin(), handleItemCallback::onFailure);
			}
		});
	}

	public static void getWhitelistedItemsWithExtra(final GetItemsWithExtraCallback getItemsWithExtraCallback) {
		Bukkit.getScheduler().runTaskAsynchronously(DirtClearlag.getPlugin(), () -> {
			try (Connection connection = Database.getConnection();
			     PreparedStatement statement = connection.prepareStatement("SELECT * FROM whitelisteditems")) {
				ResultSet resultSet = statement.executeQuery();

				List<WhitelistedItem> whitelistedItems = new ArrayList<>();
				while (resultSet.next()) {
					whitelistedItems.add(new WhitelistedItem(
							NamespacedKey.fromString(resultSet.getString("namespaced_key")),
							resultSet.getString("player_name"),
							LocalDateTime.parse(resultSet.getString("date_added"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
					));
				}

				Bukkit.getScheduler().runTask(DirtClearlag.getPlugin(), () -> getItemsWithExtraCallback.onSuccess(whitelistedItems));
			} catch (SQLException ignored) {}
		});
	}
}
