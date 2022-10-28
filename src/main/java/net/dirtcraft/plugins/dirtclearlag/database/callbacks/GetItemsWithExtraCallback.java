package net.dirtcraft.plugins.dirtclearlag.database.callbacks;

import net.dirtcraft.plugins.dirtclearlag.data.WhitelistedItem;

import java.util.List;

public interface GetItemsWithExtraCallback {
	void onSuccess(List<WhitelistedItem> items);
}
