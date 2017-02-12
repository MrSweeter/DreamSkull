package com.mrsweeter.plugin;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.mrsweeter.plugin.Commands.Commands;
import com.mrsweeter.plugin.Events.Dead;

public class DreamSkull extends JavaPlugin	{
	
	Logger log = Logger.getLogger("Minecraft");
	public static PluginManager pm = Bukkit.getPluginManager();
	public static int chance;
	public static boolean msg;

	public void onEnable() {

		// Generate/repair config
		saveDefaultConfig();
		com.mrsweeter.plugin.Config.Configuration.loadConfig(this);

		// EventListener
		pm.registerEvents(new Dead(), this);
		getCommand("dsreload").setExecutor(new Commands(this));

		log.info(Color.GREEN + "=============== " + Color.YELLOW + "DreamSkull enable" + Color.GREEN + " ===============" + Color.RESET);

	}

	public void onDisable() {
		
		log.info(Color.GREEN + "=============== " + Color.YELLOW + "DreamSkull disable" + Color.GREEN + " ===============" + Color.RESET);

	}
}
