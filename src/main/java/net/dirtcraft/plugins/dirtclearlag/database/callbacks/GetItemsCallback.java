package net.dirtcraft.plugins.dirtclearlag.database.callbacks;

import net.dirtcraft.plugins.dirtclearlag.data.WhitelistedItem;
import org.bukkit.NamespacedKey;

import java.util.List;

public interface GetItemsCallback {
	void onSuccess(List<NamespacedKey> items);
}
