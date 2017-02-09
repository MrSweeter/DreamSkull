package com.MrSweeter.DreamSkull;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import Commands.Commands;
import Events.Dead;

public class DreamSkull extends JavaPlugin	{
	
	Logger log = Logger.getLogger("Minecraft");
	public static PluginManager pm = Bukkit.getPluginManager();
	public static int chance;
	public static boolean msg;

	public void onEnable() {

		// Generate/repair config
		saveDefaultConfig();
		Config.Configuration.loadConfig(this);

		// EventListener
		pm.registerEvents(new Dead(), this);
		getCommand("dsreload").setExecutor(new Commands(this));

		log.info("\u001B[32m=============== \u001B[33mDreamSkull enable \u001B[32m===============\u001B[0m");

	}

	public void onDisable() {
		
		log.info("\u001B[32m=============== \u001B[33mDreamSkull disable \u001B[32m===============\u001B[0m");

	}
}
