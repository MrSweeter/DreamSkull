package com.mrsweeter.dreamskull;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.mrsweeter.dreamskull.Commands.Commands;
import com.mrsweeter.dreamskull.Events.Dead;
import com.mrsweeter.dreamskull.Events.KillEntity;
import com.mrsweeter.dreamskull.Events.Ressurect;

public class DreamSkull extends JavaPlugin	{
	
	Logger log = Logger.getLogger("Minecraft");
	public static PluginManager pm = Bukkit.getPluginManager();
	public static boolean msg;
	public static boolean autoKill;
	public static boolean totem;
	public static boolean looting;
	public static int loot_pct;
	public static Map<String, String> validEntities = new HashMap<String, String>();
	public static ConfigurationSection valid;
	

	public void onEnable() {

		// Generate/repair config
		saveDefaultConfig();
		com.mrsweeter.dreamskull.Config.Configuration.loadConfig(this);
		com.mrsweeter.dreamskull.Config.ValidEntity.loadEntities();

		// EventListener
		pm.registerEvents(new Dead(), this);
		pm.registerEvents(new Ressurect(), this);
		pm.registerEvents(new KillEntity(), this);
		
		getCommand("dsreload").setExecutor(new Commands(this));

		log.info(Color.GREEN + "=============== " + Color.YELLOW + "DreamSkull enable" + Color.GREEN + " ===============" + Color.RESET);

	}

	public void onDisable() {
		
		log.info(Color.GREEN + "=============== " + Color.YELLOW + "DreamSkull disable" + Color.GREEN + " ===============" + Color.RESET);

	}
}
