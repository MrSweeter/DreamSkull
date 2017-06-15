package com.mrsweeter.dreamskull.Events;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;

import com.mrsweeter.dreamskull.DreamSkull;

public class Ressurect implements Listener {
	
	DreamSkull pl;
	
	public Ressurect(DreamSkull main) {
		pl = main;
	}
	
	@EventHandler
	public void onPlayerDeath (EntityResurrectEvent event)	{
		
		Entity victim = event.getEntity();
		
		if (DreamSkull.totem && victim instanceof Player && event.getEntity().getKiller() instanceof Player)	{
			
			Player p = (Player) victim;
			
			if (p.getInventory().getItemInMainHand().getType() == Material.TOTEM
				|| p.getInventory().getItemInOffHand().getType() == Material.TOTEM)	{
				
				KillEntity.dropEntityHead(pl, victim, victim.getLocation(), event.getEntity().getKiller());
			}
		}
	}
}
