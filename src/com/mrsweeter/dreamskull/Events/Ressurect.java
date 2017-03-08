package com.mrsweeter.dreamskull.Events;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;

import com.mrsweeter.dreamskull.DreamSkull;

public class Ressurect implements Listener {
	
	@EventHandler
	public void onPlayerDeath (EntityResurrectEvent event)	{
		
		if (DreamSkull.totem)	{
			if (event.getEntityType() == EntityType.PLAYER && event.getEntity().getKiller() instanceof Player)	{
				if ( ((Player) event.getEntity()).getInventory().getItemInMainHand().getType() == Material.TOTEM || 
						((Player) event.getEntity()).getInventory().getItemInOffHand().getType() == Material.TOTEM) {
					
					Dead.dropHead(event.getEntity(), event.getEntity().getLocation(), event.getEntity().getKiller());
				}
			}
		}
	}
}
