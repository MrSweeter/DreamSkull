package com.mrsweeter.dreamskull.Config;

import org.bukkit.entity.Player;

import com.mrsweeter.dreamskull.DreamSkull;

public class Statistics {

	public static boolean checkStatistic(Player killer, Player victim)	{
		
		PluginConfiguration stats = DreamSkull.stats;
		String killer_UUID = killer.getUniqueId().toString();
		
		updateStats(killer, victim);
		
		int dead = stats.getInt(killer_UUID + ".dead");
		int kill = stats.getInt(killer_UUID + ".kill");
		
		int ratio;
		if (dead != 0)	{
			ratio = kill / dead * 10000;
		} else {
			ratio = kill * 10000;
		}
		int random = (int) (Math.random() * 10000);
		if (random <= ratio)	{
			return true;
		}
		return false;
		
	}
	
	private static void updateStats(Player killer, Player victim)	{
		
		PluginConfiguration stats = DreamSkull.stats;
		String killer_UUID = killer.getUniqueId().toString();
		String victim_UUID = victim.getUniqueId().toString();
		
		if (!stats.contains(killer_UUID.toString()))	{
			stats.set(killer_UUID + ".dead", 0);
			stats.set(killer_UUID + ".kill", 1);
		} else {
			stats.set(killer_UUID + ".kill", stats.getInt(killer_UUID + ".kill")+1);
		}
		stats.set(killer_UUID + ".name", killer.getName());
		
		if (!stats.contains(victim_UUID.toString()))	{
			stats.set(victim_UUID + ".dead", 1);
			stats.set(victim_UUID + ".kill", 0);
		} else {
			stats.set(victim_UUID + ".dead", stats.getInt(victim_UUID + ".dead")+1);
		}
		stats.set(victim_UUID + ".name", victim.getName());
		
		stats.save();
	}

}
