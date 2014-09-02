/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions.ChampionTasks;

import com.meloncraft.league.Champions.Champion;
import com.meloncraft.league.League;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Gary
 */
public class MasterYiQTask extends BukkitRunnable{
    League plugin;
    Champion champion;
    
    public MasterYiQTask(League plug, Champion champ) {
        plugin = plug;
        champion = champ;
    }
    
    public void run() {
        
        LivingEntity ent = null;
        ent = champion.getClosestEnemyEntity(champion.range + 4);
        
        if (ent != null) {
            if (ent instanceof Player) {
                plugin.teams.getChampion((Player) ent).hit(champion.getDamage() + champion.qLevel * champion.championInstance.qScale);
                Location loc = ent.getLocation();
                loc.add(champion.getPlayer().getEyeLocation().getDirection().multiply(-1.5));
                champion.getPlayer().teleport(loc);
            } else if (ent instanceof LivingEntity) {
                Location loc = ent.getLocation();
                loc.add(champion.getPlayer().getEyeLocation().getDirection().multiply(-1.5));
                champion.getPlayer().teleport(loc);
                ent.damage(champion.getDamage() + champion.qLevel * champion.championInstance.qScale);
            }
        }
    }
    
    
}

