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
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mrsweeter.dreamskull.DreamSkull;

public class KillEntity implements Listener {
	
	@EventHandler
	public void onKillEntity(EntityDeathEvent event)	{
		
		Entity victim = event.getEntity();
		
		if (event.getEntity().getKiller() instanceof Player)	{
			
			Player killer = event.getEntity().getKiller();
			String entName = event.getEntityType().toString().toLowerCase();
			
			if (DreamSkull.validEntities.containsKey(entName))	{
				if (DreamSkull.valid.contains(entName))	{
					if (victim instanceof Player)	{
						// Kill PvP
						if (DreamSkull.autoKill)	{
							// autoKill allow (true) --> drop head allow
							dropEntityHead(victim, victim.getLocation(), killer);
						} else if (!DreamSkull.autoKill && !victim.getName().equals(killer.getName()))	{
							// autoKill disallow (false) --> victimName != killerName --> drop head allow
							dropEntityHead(victim, victim.getLocation(), killer);
						}
					} else if (!victim.getScoreboardTags().contains("msd_spawn"))	{
						dropEntityHead(event.getEntity(), event.getEntity().getLocation(), killer);
					}
				} else {
					if (killer.isOp() && DreamSkull.op_msg)	{
						killer.sendMessage("§c[§aDreamSkull§c] §7Entity §c" + entName + "§7 is configurable, please check config.yml");
					}
				}
			}
		}
	}
	
	static void dropEntityHead(Entity ent, Location paramLoc, Player killer) {
		
		int random = (int) (Math.random() * 10000);
		int chance = (int) (DreamSkull.valid.getDouble(ent.getType().toString().toLowerCase())*100);
		
		if (DreamSkull.looting)	{
			
			ItemStack itemKill = killer.getInventory().getItemInMainHand();
			
			if (itemKill.containsEnchantment(Enchantment.LOOT_BONUS_MOBS))	{
				int lvl = itemKill.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);
				chance += lvl * DreamSkull.loot_pct;
			}
		}
		
		if (random <= chance)	{
			Location loc = paramLoc;
			ItemStack skull;
			
			String entName = ent.getType().toString().toUpperCase();
			
			switch (ent.getType())	{
			case ZOMBIE:
				skull = new ItemStack(Material.SKULL_ITEM, 1,(byte)2);
				break;
			case SKELETON:
				skull = new ItemStack(Material.SKULL_ITEM, 1,(byte)0);
				break;
			case WITHER_SKELETON:
				skull = new ItemStack(Material.SKULL_ITEM, 1,(byte)1);
				break;
			case CREEPER:
				skull = new ItemStack(Material.SKULL_ITEM, 1,(byte)4);
				break;
			case ENDER_DRAGON:
				skull = new ItemStack(Material.SKULL_ITEM, 1,(byte)5);
				break;
			case PLAYER:
				entName = ent.getName();
				skull = new ItemStack(Material.SKULL_ITEM, 1,(byte)3);
				SkullMeta metaP = (SkullMeta) skull.getItemMeta();
				metaP.setOwner(entName);
				skull.setItemMeta(metaP);
				break;
			default:
				skull = new ItemStack(Material.SKULL_ITEM, 1,(byte)3);
				SkullMeta meta = (SkullMeta) skull.getItemMeta();
				meta.setOwner(DreamSkull.validEntities.get(ent.getType().toString().toLowerCase()));
				skull.setItemMeta(meta);
			}
			
			killer.getWorld().dropItem(loc, skull);
			if (DreamSkull.msg)	{
				ent.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, ent.getLocation(), 20, 0.5, 0.5, 0.5, 0.1);
				killer.sendMessage("§c[§aDreamSkull§c] §7Vous avez looté la tête de §c" + entName + " §7en le tuant !");
			}
		}
	}
	
	@EventHandler
	public void onSpawnerSpawn(SpawnerSpawnEvent event)	{
		
		if (!DreamSkull.spawn_loot)	{
			Entity ent = event.getEntity();
			ent.addScoreboardTag("msd_spawn");
		}
	}
}
