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
public class Cooldown extends BukkitRunnable{
    League plugin;
    boolean ready;
    
    public Cooldown() {
        plugin = this.plugin;
    }
    
    //allows player to attack
    public void run() {
        ready = true;
    }
}
