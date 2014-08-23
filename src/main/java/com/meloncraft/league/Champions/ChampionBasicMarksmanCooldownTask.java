/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import com.meloncraft.league.League;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Gary
 */
public class ChampionBasicMarksmanCooldownTask extends BukkitRunnable{
    League plugin;
    Champion champion;
    Entity projectile;
    
    public ChampionBasicMarksmanCooldownTask(League plug, Champion champ, Entity project) {
        plugin = plug;
        champion = champ;
        projectile = project;
    }
    
    public void run() {
        champion.resetBasic();
        projectile.remove();
    }
            
    
}