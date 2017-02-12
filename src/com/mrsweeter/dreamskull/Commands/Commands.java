package com.mrsweeter.dreamskull.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.mrsweeter.dreamskull.DreamSkull;

public class Commands implements CommandExecutor	{
	
	private DreamSkull pl;
	
	public Commands(DreamSkull main)	{
		this.pl = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if(sender.hasPermission("dreamSkull.reload")){
			if (commandLabel.toLowerCase().equals("dsreload") || args.length != 0)	{
				com.mrsweeter.dreamskull.Config.Configuration.loadConfig(pl);
				sender.sendMessage("§c[§aDreamSkull§c] §7Reload complete");
				return true;
			}
		} else	{
			sender.sendMessage("§cVous n'avez pas la permission d'executer cette commande !");
		}
		return false;
	}
}
