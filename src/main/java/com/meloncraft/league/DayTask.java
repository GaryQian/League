/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Gary
 */
public class DayTask extends BukkitRunnable{
    League plugin;
    
    public DayTask (League plug) {
        plugin = plug;
    }
    
    public void run() {
        plugin.mainWorld.setTime(0);
    }
}
