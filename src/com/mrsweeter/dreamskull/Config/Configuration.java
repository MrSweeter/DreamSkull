package com.mrsweeter.dreamskull.Config;

import com.mrsweeter.dreamskull.DreamSkull;

public class Configuration {
	
	public static void loadConfig(DreamSkull pl)	{		
		
		pl.reloadConfig();
		
		DreamSkull.msg = pl.getConfig().getBoolean("send_message");
		DreamSkull.totem = pl.getConfig().getBoolean("drop-allow-totem");
		DreamSkull.autoKill = pl.getConfig().getBoolean("auto-kill");
		DreamSkull.looting = pl.getConfig().getBoolean("looting-influence");
		DreamSkull.loot_pct = pl.getConfig().getInt("looting-pct-more");
		DreamSkull.valid = pl.getConfig().getConfigurationSection("entities");
	}

}
