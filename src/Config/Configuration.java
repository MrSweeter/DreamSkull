package Config;

import com.MrSweeter.DreamSkull.DreamSkull;

public class Configuration {
	
	public static void loadConfig(DreamSkull pl)	{		
		
		pl.reloadConfig();
		DreamSkull.chance = pl.getConfig().getInt("loot_chance");
		DreamSkull.msg = pl.getConfig().getBoolean("send_message");
	}

}
