/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import com.meloncraft.league.Arena.Minions.MinionSpawnWaveTask;
import com.meloncraft.league.Arena.Turret;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitTask;


/**
 *
 * @author Gary
 */
public class ArenaHandler {
    public boolean start;
    League plugin;
    Teams teams;
    World world;
    Turret blueMid1, blueMid2, blueMid3, blueTop1, blueTop2, blueTop3, blueBot1, blueBot2, blueBot3, blueNexus1, blueNexus2, purpleMid1, purpleMid2, purpleMid3, purpleTop1, purpleTop2, purpleTop3, purpleBot1, purpleBot2, purpleBot3, purpleNexus1, purpleNexus2;
    
    public ArenaHandler(League plug, Teams tea) {
        teams = tea;
        plugin = plug;
        world = plugin.mainWorld;
        //blueMid1 = new Turret()
        
        //plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    public Turret isBlueTurret(Location loc) {
        if (blueMid1.isTurretBody(loc)) {
            return blueMid1;
        }
        else if (blueMid2.isTurretBody(loc)) {
            return blueMid2;
        }
        else if (blueMid3.isTurretBody(loc)) {
            return blueMid3;
        }
        
        //---------
        else if (blueBot1.isTurretBody(loc)) {
            return blueBot1;
        }
        else if (blueBot2.isTurretBody(loc)) {
            return blueBot2;
        }
        else if (blueBot3.isTurretBody(loc)) {
            return blueBot3;
        }
        
        //-------
        else if (blueTop1.isTurretBody(loc)) {
            return blueTop1;
        }
        else if (blueTop2.isTurretBody(loc)) {
            return blueTop2;
        }
        else if (blueTop3.isTurretBody(loc)) {
            return blueTop3;
        }
        
        //--------
        else if (blueNexus1.isTurretBody(loc)) {
            return blueNexus1;
        }
        else if (blueNexus2.isTurretBody(loc)) {
            return blueNexus2;
        }
        
        else {
            return null;
        }
        
    }
    public Turret isPurpleTurret(Location loc) {
        //--------
        if (purpleMid1.isTurretBody(loc)) {
            return purpleMid1;
        }
        else if (purpleMid2.isTurretBody(loc)) {
            return purpleMid2;
        }
        else if (purpleMid3.isTurretBody(loc)) {
            return purpleMid3;
        }
        
        //---------
        else if (purpleBot1.isTurretBody(loc)) {
            return purpleBot1;
        }
        else if (purpleBot2.isTurretBody(loc)) {
            return purpleBot2;
        }
        else if (purpleBot3.isTurretBody(loc)) {
            return purpleBot3;
        }
        
        //-------
        else if (purpleTop1.isTurretBody(loc)) {
            return purpleTop1;
        }
        else if (purpleTop2.isTurretBody(loc)) {
            return purpleTop2;
        }
        else if (purpleTop3.isTurretBody(loc)) {
            return purpleTop3;
        }
        
        //--------
        else if (purpleNexus1.isTurretBody(loc)) {
            return purpleNexus1;
        }
        else if (purpleNexus2.isTurretBody(loc)) {
            return purpleNexus2;
        }
        
        else {
            return null;
        }
    }
    
    //spawns a wave of minions
    public void spawnMinionWave() {
        BukkitTask spawnWaves = new MinionSpawnWaveTask(this.plugin).runTaskTimer(this.plugin, 800, 600);
    }
}
