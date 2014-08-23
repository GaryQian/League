/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import com.meloncraft.league.League;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Gary
 */
public class SlowTask extends BukkitRunnable{
    
    League plugin;
    Champion champion;
    
    public SlowTask(League plug, Champion champ) {
        plugin = plug;
        champion = champ;
    }
    
    public void run() {
        champion.resetWalkSpeed();
    }
    
}
