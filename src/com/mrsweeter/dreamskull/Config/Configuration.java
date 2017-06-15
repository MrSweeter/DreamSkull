package com.mrsweeter.dreamskull.Config;

import com.mrsweeter.dreamskull.DreamSkull;

public class Configuration {
	
	public static void loadConfig(DreamSkull pl)	{		
		
		pl.reloadConfig();
		
		DreamSkull.msg = pl.getConfig().getBoolean("send_message");
		DreamSkull.op_msg = pl.getConfig().getBoolean("op_message");
		DreamSkull.totem = pl.getConfig().getBoolean("drop-allow-totem");
		DreamSkull.autoKill = pl.getConfig().getBoolean("auto-kill");
		DreamSkull.looting = pl.getConfig().getBoolean("looting-influence");
		DreamSkull.loot_pct = (int) (pl.getConfig().getDouble("looting-pct-more")*100);
		DreamSkull.spawn_loot = pl.getConfig().getBoolean("spawner-loot");
		DreamSkull.valid = pl.getConfig().getConfigurationSection("entities");
		DreamSkull.statsP = pl.getConfig().getBoolean("stats-active");
		DreamSkull.colorCode = pl.getConfig().getString("color");
		DreamSkull.reloadMsg = pl.getConfig().getString("reload").replace(DreamSkull.colorCode, "§");
		DreamSkull.configurableMsg = pl.getConfig().getString("configurable").replace(DreamSkull.colorCode, "§");
		DreamSkull.lootMsg = pl.getConfig().getString("loot").replace(DreamSkull.colorCode, "§");
	}

}
