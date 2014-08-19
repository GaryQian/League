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
import org.bukkit.configuration.file.FileConfiguration;
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
    FileConfiguration config;
    Turret blueMid1, blueMid2, blueMid3, blueTop1, blueTop2, blueTop3, blueBot1, blueBot2, blueBot3, blueNexus1, blueNexus2, purpleMid1, purpleMid2, purpleMid3, purpleTop1, purpleTop2, purpleTop3, purpleBot1, purpleBot2, purpleBot3, purpleNexus1, purpleNexus2;
    
    public ArenaHandler(League plug, Teams tea) {
        teams = tea;
        plugin = plug;
        world = plugin.mainWorld;
        config = plugin.getConfig();
        
        
        //create Turrets
        blueMid1 = new Turret(config.getDouble("blueMid1.x"), config.getDouble("blueMid1.y"), config.getDouble("blueMid1.z"), "blue", teams, plugin);
        blueMid2 = new Turret(config.getDouble("blueMid2.x"), config.getDouble("blueMid2.y"), config.getDouble("blueMid2.z"), "blue", teams, plugin);
        blueMid3 = new Turret(config.getDouble("blueMid3.x"), config.getDouble("blueMid3.y"), config.getDouble("blueMid3.z"), "blue", teams, plugin);
        
        blueBot1 = new Turret(config.getDouble("blueBot1.x"), config.getDouble("blueBot1.y"), config.getDouble("blueBot1.z"), "blue", teams, plugin);
        blueBot2 = new Turret(config.getDouble("blueBot2.x"), config.getDouble("blueBot2.y"), config.getDouble("blueBot2.z"), "blue", teams, plugin);
        blueBot3 = new Turret(config.getDouble("blueBot3.x"), config.getDouble("blueBot3.y"), config.getDouble("blueBot3.z"), "blue", teams, plugin);
        
        blueTop1 = new Turret(config.getDouble("blueTop1.x"), config.getDouble("blueTop1.y"), config.getDouble("blueTop1.z"), "blue", teams, plugin);
        blueTop2 = new Turret(config.getDouble("blueTop2.x"), config.getDouble("blueTop2.y"), config.getDouble("blueTop2.z"), "blue", teams, plugin);
        blueTop3 = new Turret(config.getDouble("blueTop3.x"), config.getDouble("blueTop3.y"), config.getDouble("blueTop3.z"), "blue", teams, plugin);
        
        blueNexus1 = new Turret(config.getDouble("blueNexus1.x"), config.getDouble("blueNexus1.y"), config.getDouble("blueNexus1.z"), "blue", teams, plugin);
        blueNexus2 = new Turret(config.getDouble("blueNexus2.x"), config.getDouble("blueNexus2.y"), config.getDouble("blueNexus2.z"), "blue", teams, plugin);
        
        
        
        
        purpleMid1 = new Turret(config.getDouble("purpleMid1.x"), config.getDouble("purpleMid1.y"), config.getDouble("purpleMid1.z"), "purple", teams, plugin);
        purpleMid2 = new Turret(config.getDouble("purpleMid2.x"), config.getDouble("purpleMid2.y"), config.getDouble("purpleMid2.z"), "purple", teams, plugin);
        purpleMid3 = new Turret(config.getDouble("purpleMid3.x"), config.getDouble("purpleMid3.y"), config.getDouble("purpleMid3.z"), "purple", teams, plugin);
        
        purpleBot1 = new Turret(config.getDouble("purpleBot1.x"), config.getDouble("purpleBot1.y"), config.getDouble("purpleBot1.z"), "purple", teams, plugin);
        purpleBot2 = new Turret(config.getDouble("purpleBot2.x"), config.getDouble("purpleBot2.y"), config.getDouble("purpleBot2.z"), "purple", teams, plugin);
        purpleBot3 = new Turret(config.getDouble("purpleBot3.x"), config.getDouble("purpleBot3.y"), config.getDouble("purpleBot3.z"), "purple", teams, plugin);
        
        purpleTop1 = new Turret(config.getDouble("purpleTop1.x"), config.getDouble("purpleTop1.y"), config.getDouble("purpleTop1.z"), "purple", teams, plugin);
        purpleTop2 = new Turret(config.getDouble("purpleTop2.x"), config.getDouble("purpleTop2.y"), config.getDouble("purpleTop2.z"), "purple", teams, plugin);
        purpleTop3 = new Turret(config.getDouble("purpleTop3.x"), config.getDouble("purpleTop3.y"), config.getDouble("purpleTop3.z"), "purple", teams, plugin);
        
        purpleNexus1 = new Turret(config.getDouble("purpleNexus1.x"), config.getDouble("purpleNexus1.y"), config.getDouble("purpleNexus1.z"), "purple", teams, plugin);
        purpleNexus2 = new Turret(config.getDouble("purpleNexus2.x"), config.getDouble("purpleNexus2.y"), config.getDouble("purpleNexus2.z"), "purple", teams, plugin);
        //plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    public Turret isBlueTurret(Location loc) {
        if (blueMid1.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return blueMid1;
        }
        else if (blueMid2.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return blueMid2;
        }
        else if (blueMid3.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return blueMid3;
        }
        
        //---------
        else if (blueBot1.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return blueBot1;
        }
        else if (blueBot2.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return blueBot2;
        }
        else if (blueBot3.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return blueBot3;
        }
        
        //-------
        else if (blueTop1.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return blueTop1;
        }
        else if (blueTop2.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return blueTop2;
        }
        else if (blueTop3.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return blueTop3;
        }
        
        //--------
        else if (blueNexus1.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return blueNexus1;
        }
        else if (blueNexus2.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return blueNexus2;
        }
        
        else {
            return null;
        }
        
    }
    public Turret isPurpleTurret(Location loc) {
        //--------
        if (purpleMid1.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return purpleMid1;
        }
        else if (purpleMid2.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return purpleMid2;
        }
        else if (purpleMid3.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return purpleMid3;
        }
        
        //---------
        else if (purpleBot1.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return purpleBot1;
        }
        else if (purpleBot2.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return purpleBot2;
        }
        else if (purpleBot3.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return purpleBot3;
        }
        
        //-------
        else if (purpleTop1.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return purpleTop1;
        }
        else if (purpleTop2.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return purpleTop2;
        }
        else if (purpleTop3.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return purpleTop3;
        }
        
        //--------
        else if (purpleNexus1.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return purpleNexus1;
        }
        else if (purpleNexus2.isTurretBody(loc.getX(), loc.getY(), loc.getZ())) {
            return purpleNexus2;
        }
        
        else {
            return null;
        }
    }
    
    //spawns a wave of minions
    public void spawnMinionWave() {
        BukkitTask spawnWaves = new MinionSpawnWaveTask(plugin).runTaskTimer(plugin, 800, 600);
    }
    
    public void maintainDay() {
        BukkitTask task = new DayTask(plugin).runTaskTimer(plugin, 100, 1500);
    }
}
