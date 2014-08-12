/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import com.meloncraft.league.Arena.Minions.MinionSpawnWaveTask;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitTask;


/**
 *
 * @author Gary
 */
public class ArenaHandler {
    public boolean start;
    League plugin;
    World world;
    
    public void ArenaHandler() {
        world = plugin.mainWorld;
        //plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    
    //spawns a wave of minions
    public void spawnMinionWave() {
        BukkitTask spawnWaves = new MinionSpawnWaveTask(this.plugin).runTaskTimer(this.plugin, 800, 600);
    }
}
