package com.mrsweeter.dreamskull;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.mrsweeter.dreamskull.Commands.Commands;
import com.mrsweeter.dreamskull.Config.PluginConfiguration;
import com.mrsweeter.dreamskull.Events.KillEntity;
import com.mrsweeter.dreamskull.Events.Ressurect;

public class DreamSkull extends JavaPlugin	{
	
	Logger log = Logger.getLogger("Minecraft");
	public static PluginManager pm = Bukkit.getPluginManager();
	public static boolean msg;
	public static boolean op_msg;
	public static boolean autoKill;
	public static boolean totem;
	public static boolean looting;
	public static int loot_pct;
	public static boolean spawn_loot;
	public static Map<String, String> validEntities = new HashMap<String, String>();
	public static ConfigurationSection valid;
	public static PluginConfiguration stats;
	public static boolean statsP;
	

	public void onEnable() {

		// Generate/repair config
		saveDefaultConfig();
		com.mrsweeter.dreamskull.Config.Configuration.loadConfig(this);
		com.mrsweeter.dreamskull.Config.ValidEntity.loadEntities();

		// EventListener
		pm.registerEvents(new KillEntity(), this);
		
		String spigotV = Bukkit.getServer().getVersion();
		String version = spigotV.substring(spigotV.indexOf('(')+5, spigotV.lastIndexOf(')'));
		
		// Version 1.9 and 1.10 allow
		if (version.startsWith("1.10") || version.startsWith("1.9"))	{
			log.warning(Color.RED + "=============== -drop-allow-totem- option in config.yml is useless in " + version + " ===============" + Color.RESET);
		} else {
			pm.registerEvents(new Ressurect(), this);
		}
		
		stats = new PluginConfiguration(this, "stats.yml");
		
		getCommand("dsreload").setExecutor(new Commands(this));

		log.info(Color.GREEN + "=============== " + Color.YELLOW + "DreamSkull enable" + Color.GREEN + " ===============" + Color.RESET);
		
		Updater updater = new Updater(37538);
		if (updater.checkUpdate(this.getDescription().getVersion()) && Updater.updateAvailable())	{
			log.warning(Color.RED + "=============== A newest version of DreamSkull is available ===============" + Color.RESET);
		}
	}

	public void onDisable() {
		
		log.info(Color.GREEN + "=============== " + Color.YELLOW + "DreamSkull disable" + Color.GREEN + " ===============" + Color.RESET);

	}
}
