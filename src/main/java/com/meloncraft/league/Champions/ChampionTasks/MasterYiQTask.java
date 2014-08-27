/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions.ChampionTasks;

import com.meloncraft.league.Champions.Champion;
import com.meloncraft.league.League;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
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
        Entity ent = null;
        boolean cont = true;
        int counter = 0;
        while (cont && counter < 4) {
            counter++;
            ent = champion.getClosestEntity(champion.range + 4);
            if (ent != null) {
                if (plugin.teams.getTeam(ent).equals(champion.team)) {
                    cont = false;
                }
            }
        }
        if (counter < 4) {
            if (ent != null) {
                if (ent instanceof Player) {
                    plugin.teams.getChampion((Player) ent).hit(champion.getDamage() + champion.qLevel * champion.championInstance.qScale);
                    Location loc = ent.getLocation();
                    loc.add(champion.getPlayer().getEyeLocation().getDirection().multiply(-1));
                    champion.getPlayer().teleport(ent);
                } else if (ent instanceof LivingEntity) {
                    ent = (LivingEntity) ent;
                    LivingEntity entLive = (LivingEntity) ent;
                    entLive.damage(champion.getDamage() + champion.qLevel * champion.championInstance.qScale);
                }
            }
        }
    }
    
}