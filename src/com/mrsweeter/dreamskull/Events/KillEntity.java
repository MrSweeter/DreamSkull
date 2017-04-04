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

public class KillEntity implements Listener {
	
	@EventHandler
	public void onKillEntity(EntityDeathEvent event)	{
		
		if (event.getEntity().getKiller() instanceof Player)	{
			
			Player player = event.getEntity().getKiller();
			String ent = event.getEntityType().toString().toLowerCase();
			
			if (!ent.equals("player") && DreamSkull.validEntities.containsKey(ent))	{
				if (DreamSkull.valid.contains(ent))	{
					dropEntityHead(event.getEntity(), event.getEntity().getLocation(), player);
				} else {
					if (player.isOp())	{
						player.sendMessage("§c[§aDreamSkull§c] §7Entity §c" + ent + "§7 is configurable, please check config.yml");
					}
				}
			}
		}
	}
	
	static void dropEntityHead(Entity ent, Location paramLoc, Player killer) {
		
		int random = (int) (Math.random() * 100);
		int chance = DreamSkull.valid.getInt(ent.getType().toString().toLowerCase());
		
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
			default:
				skull = new ItemStack(Material.SKULL_ITEM, 1,(byte)3);
				SkullMeta meta = (SkullMeta) skull.getItemMeta();
				meta.setOwner(DreamSkull.validEntities.get(ent.getType().toString().toLowerCase()));
				skull.setItemMeta(meta);
			}
			
			killer.getWorld().dropItem(loc, skull);
			if (DreamSkull.msg)	{
				ent.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, ent.getLocation(), 20, 0.5, 0.5, 0.5, 0.1);
				killer.sendMessage("§c[§aDreamSkull§c] §7Vous avez looté la tête de §c" + ent.getType().toString().toUpperCase() + " §7en le tuant !");
			}
		}
	}
}
