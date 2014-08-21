/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import com.meloncraft.league.Arena.ArenaHandler;
import com.meloncraft.league.League;
import com.meloncraft.league.Teams;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Gary
 */
public class ChampionBasicCooldownTask extends BukkitRunnable{
    League plugin;
    Champion champion;
    
    public ChampionBasicCooldownTask(League plug, Champion champ, int ticks) {
        plugin = plug;
        champion = champ;
    }
    
    public void run() {
        champion.resetBasic();
    }
            
    
}
