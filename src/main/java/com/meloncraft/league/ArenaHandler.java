/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import com.meloncraft.league.Arena.Minions.MinionSpawnWaveTask;
import com.meloncraft.league.Arena.Tower;
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
    Tower blueMid1;
    Turret blueMid2, blueMid3, blueTop1, blueTop2, blueTop3, blueBot1, blueBot2, blueBot3, blueNexus1, blueNexus2, purpleMid1, purpleMid2, purpleMid3, purpleTop1, purpleTop2, purpleTop3, purpleBot1, purpleBot2, purpleBot3, purpleNexus1, purpleNexus2;
    
    public void ArenaHandler() {
        world = plugin.mainWorld;
        //blueMid1 = new Turret()
        
        //plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    
    //spawns a wave of minions
    public void spawnMinionWave() {
        BukkitTask spawnWaves = new MinionSpawnWaveTask(this.plugin).runTaskTimer(this.plugin, 800, 600);
    }
}
