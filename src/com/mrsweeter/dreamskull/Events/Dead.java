package com.mrsweeter.dreamskull.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mrsweeter.dreamskull.DreamSkull;

public class Dead implements Listener {
	
	@EventHandler
	public void onPlayerDeath (EntityDeathEvent event)	{
		
		if (event.getEntity() instanceof Player && event.getEntity().getKiller() instanceof Player)	{
			if (DreamSkull.autoKill)	{
				dropHead(event.getEntity(), event.getEntity().getLocation(), event.getEntity().getKiller());
			} else if (!DreamSkull.autoKill && !event.getEntity().getName().equals(event.getEntity().getKiller().getName()))	{
				dropHead(event.getEntity(), event.getEntity().getLocation(), event.getEntity().getKiller());
			}
		}
	}

	static void dropHead(Entity ent, Location paramLoc, Player killer) {
		int random = (int) (Math.random() * 100);
		int chance = 0;
		if (DreamSkull.getConfiguration().contains("player"))	{
			
			chance = DreamSkull.getConfiguration().getInt("player");
			
			if (DreamSkull.looting && chance != 0)	{
				
				ItemStack itemKill = killer.getInventory().getItemInMainHand();
				
				if (itemKill.containsEnchantment(Enchantment.LOOT_BONUS_MOBS))	{
					int lvl = itemKill.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);
					chance += lvl * DreamSkull.loot_pct;
				}
			}
			
			if (random <= chance)	{
				Player p = (Player) ent;
				Location loc = paramLoc;
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
