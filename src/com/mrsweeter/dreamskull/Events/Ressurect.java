package com.mrsweeter.dreamskull.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mrsweeter.dreamskull.DreamSkull;

public class Ressurect implements Listener {
	
	private ItemStack totem = new ItemStack(Material.TOTEM);
	
	@EventHandler
	public void onPlayerDeath (EntityResurrectEvent event)	{
		
		if (DreamSkull.totem)	{
			if (event.getEntityType() == EntityType.PLAYER && event.getEntity().getKiller() instanceof Player)	{
				if ( ((Player) event.getEntity()).getInventory().getItemInMainHand() == totem || 
						((Player) event.getEntity()).getInventory().getItemInOffHand() == totem) {
					
					int random = (int) (Math.random() * 100);
					int chance = DreamSkull.chance;
					
					if (random <= chance)	{
						Player p = (Player) event.getEntity();
						Location loc = event.getEntity().getLocation();
						ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1,(byte)3);
						SkullMeta meta = (SkullMeta) skull.getItemMeta();
						meta.setOwner(p.getName());
						skull.setItemMeta(meta);
						p.getWorld().dropItem(loc, skull);
						if (DreamSkull.msg)	{
							p.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation(), 20, 0.5, 0.5, 0.5, 0.1);
							p.getKiller().sendMessage("§c[§aDreamSkull§c] §7Vous avez looté la tête de §c" + p.getName() + " §7en le tuant !");
						}
					}
				}
			}
		}
	}
}
