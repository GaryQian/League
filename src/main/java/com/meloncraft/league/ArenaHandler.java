/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import com.meloncraft.league.Arena.Minions.MinionSpawnWaveTask;
import com.meloncraft.league.Arena.Turret;
import com.meloncraft.league.Arena.TurretAttackTask;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;


/**
 *
 * @author Gary
 */
public class ArenaHandler {
    public boolean start;
    public League plugin;
    public Teams teams;
    public World world;
    public boolean started;
    //public Player player;
    public FileConfiguration config;
    public Location blueSpawn, purpleSpawn;
    public Turret blueMid1, blueMid2, blueMid3, blueTop1, blueTop2, blueTop3, blueBot1, blueBot2, blueBot3, blueNexus1, blueNexus2, purpleMid1, purpleMid2, purpleMid3, purpleTop1, purpleTop2, purpleTop3, purpleBot1, purpleBot2, purpleBot3, purpleNexus1, purpleNexus2;
    
    public ArenaHandler(League plug, Teams tea) {
        teams = tea;
        plugin = plug;
        //world = plugin.mainWorld;
        config = plugin.getConfig();
        started = false;
        
        setSpawns(plugin.getConfig().getDouble("blue-spawn.x"), plugin.getConfig().getDouble("blue-spawn.y"), plugin.getConfig().getDouble("blue-spawn.z"), plugin.getConfig().getDouble("purple-spawn.x"), plugin.getConfig().getDouble("purple-spawn.y"), plugin.getConfig().getDouble("purple-spawn.z"), world);
        //blueSpawn = new Location(plugin.mainWorld, config.getDouble("blue-spawn.x"), config.getDouble("blue-spawn.y"), config.getDouble("blue-spawn.z"));
        //purpleSpawn = new Location(plugin.mainWorld, config.getDouble("purple-spawn.x"), config.getDouble("purple-spawn.y"), config.getDouble("purple-spawn.z"));
        
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
    
    
    public void setSpawns(double x1, double y1, double z1, double x2, double y2, double z2, World world) {
        blueSpawn = new Location(world, x1, y1, z1);
        purpleSpawn = new Location(world, x2, y2, z2);
    }
    
    public boolean getStarted() {
        return started;
    }
    
    public void startGame() {
        if (!started) {
            started = true;
            
            //randomly select champions
            for (Player player : teams.getBlueTeam()) {
                if (teams.getChampion(player) == null) {
                    teams.setRandomChampion(player);
                }
            }
            for (Player player : teams.getPurpleTeam()) {
                if (teams.getChampion(player) == null) {
                    teams.setRandomChampion(player);
                }
            }
            
            //teleport to the respective spawns
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                blueSpawn.setWorld(player.getWorld());
                purpleSpawn.setWorld(player.getWorld());
                if (teams.getTeam(player).equals("blue")) {
                    //player.sendMessage("GAME FORCE-STARTED");
                    player.teleport(blueSpawn);
                }
                else if (teams.getTeam(player).equals("purple")){
                    //player.sendMessage("GAME FORCE-STARTED");
                    player.teleport(purpleSpawn);
                }
                else {
                    //assign random champ
                }
            }
            //start turret attacking
            plugin.getLogger().info("STARTING TURRET ATTACKIN!");
            BukkitTask turretAttack = new TurretAttackTask(plugin, teams, this).runTaskTimer(plugin, 10 * 20, 40);
            //spawnMinionWave();
        }
    }
    
    public void turretsAttack() {
        blueMid1.attackSequence();
        blueMid2.attackSequence();
        blueMid3.attackSequence();
        
        blueBot1.attackSequence();
        blueBot2.attackSequence();
        blueBot3.attackSequence();
        
        blueTop1.attackSequence();
        blueTop2.attackSequence();
        blueTop3.attackSequence();
        
        blueNexus1.attackSequence();
        blueNexus2.attackSequence();
        
        
        
        
        purpleMid1.attackSequence();
        purpleMid2.attackSequence();
        purpleMid3.attackSequence();
        
        purpleBot1.attackSequence();
        purpleBot2.attackSequence();
        purpleBot3.attackSequence();
        
        purpleTop1.attackSequence();
        purpleTop2.attackSequence();
        purpleTop3.attackSequence();
        
        purpleNexus1.attackSequence();
        purpleNexus2.attackSequence();
    }
    
    //spawns a waves of minions
    public void spawnMinionWave() {
        BukkitTask spawnWaves = new MinionSpawnWaveTask(plugin).runTaskTimer(plugin, 800, 600);
    }
    
    public void maintainDay() {
        BukkitTask task = new DayTask(plugin).runTaskTimer(plugin, 100, 1500);
    }
    
}
