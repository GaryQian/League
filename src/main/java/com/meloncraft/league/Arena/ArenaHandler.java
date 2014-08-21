/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena;

import com.meloncraft.league.Arena.Minions.MinionSpawnWaveTask;
import com.meloncraft.league.Champions.RecallTask;
import com.meloncraft.league.DayTask;
import com.meloncraft.league.League;
import com.meloncraft.league.Teams;
import org.bukkit.ChatColor;
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
    public int clock;
    //public Player player;
    public FileConfiguration config;
    public Location blueSpawn, purpleSpawn;
    public Turret blueMid1, blueMid2, blueMid3, blueTop1, blueTop2, blueTop3, blueBot1, blueBot2, blueBot3, blueNexus1, blueNexus2, purpleMid1, purpleMid2, purpleMid3, purpleTop1, purpleTop2, purpleTop3, purpleBot1, purpleBot2, purpleBot3, purpleNexus1, purpleNexus2;
    
    public ArenaHandler(League plug, Teams tea) {
        clock = 0;
        teams = tea;
        plugin = plug;
        //world = plugin.mainWorld;
        config = plugin.getConfig();
        started = false;
        
        setSpawns(plugin.getConfig().getDouble("blue-spawn.x"), plugin.getConfig().getDouble("blue-spawn.y"), plugin.getConfig().getDouble("blue-spawn.z"), plugin.getConfig().getDouble("purple-spawn.x"), plugin.getConfig().getDouble("purple-spawn.y"), plugin.getConfig().getDouble("purple-spawn.z"), plugin.getConfig().getDouble("blue-spawn.pitch"), plugin.getConfig().getDouble("blue-spawn.yaw"), plugin.getConfig().getDouble("purple-spawn.pitch"), plugin.getConfig().getDouble("purple-spawn.yaw"), world);
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
        if (!blueMid1.isDead) {
            if (blueMid1.isTurretBody(loc)) {
                return blueMid1;
            }
        }
        if (!blueMid2.isDead) {
            if (blueMid2.isTurretBody(loc)) {
                return blueMid2;
            }
        }
        if (!blueMid3.isDead) {
            if (blueMid3.isTurretBody(loc)) {
                return blueMid3;
            }
        }
        
        //---------
        if (!blueBot1.isDead) {
            if (blueBot1.isTurretBody(loc)) {
                return blueBot1;
            }
        }
        if (!blueBot2.isDead) {
            if (blueBot2.isTurretBody(loc)) {
                return blueBot2;
            }
        }
        if (!blueBot3.isDead) {
            if (blueBot3.isTurretBody(loc)) {
                return blueBot3;
            }
        }
        
        //-------
        if (!blueTop1.isDead) {
            if (blueTop1.isTurretBody(loc)) {
                return blueTop1;
            }
        }
        if (!blueTop2.isDead) {
            if (blueTop2.isTurretBody(loc)) {
                return blueTop2;
            }
        }
        if (!blueTop3.isDead) {
            if (blueTop3.isTurretBody(loc)) {
                return blueTop3;
            }
        }
        
        //--------
        if (!blueNexus1.isDead) {
            if (blueNexus1.isTurretBody(loc)) {
                return blueNexus1;
            }
        }
        if (!blueNexus2.isDead) {
            if (blueNexus2.isTurretBody(loc)) {
                return blueNexus2;
            }
        }
        return null;
        
    }
    
    
    public Turret isPurpleTurret(Location loc) {
        if (!purpleMid1.isDead) {
            if (purpleMid1.isTurretBody(loc)) {
                return purpleMid1;
            }
        }
        if (!purpleMid2.isDead) {
            if (purpleMid2.isTurretBody(loc)) {
                return purpleMid2;
            }
        }
        if (!purpleMid3.isDead) {
            if (purpleMid3.isTurretBody(loc)) {
                return purpleMid3;
            }
        }
        
        //---------
        if (!purpleBot1.isDead) {
            if (purpleBot1.isTurretBody(loc)) {
                return purpleBot1;
            }
        }
        if (!purpleBot2.isDead) {
            if (purpleBot2.isTurretBody(loc)) {
                return purpleBot2;
            }
        }
        if (!purpleBot3.isDead) {
            if (purpleBot3.isTurretBody(loc)) {
                return purpleBot3;
            }
        }
        
        //-------
        if (!purpleTop1.isDead) {
            if (purpleTop1.isTurretBody(loc)) {
                return purpleTop1;
            }
        }
        if (!purpleTop2.isDead) {
            if (purpleTop2.isTurretBody(loc)) {
                return purpleTop2;
            }
        }
        if (!purpleTop3.isDead) {
            if (purpleTop3.isTurretBody(loc)) {
                return purpleTop3;
            }
        }
        
        //--------
        if (!purpleNexus1.isDead) {
            if (purpleNexus1.isTurretBody(loc)) {
                return purpleNexus1;
            }
        }
        if (!purpleNexus2.isDead) {
            if (purpleNexus2.isTurretBody(loc)) {
                return purpleNexus2;
            }
        }
        return null;
    }
    
    
    public void setSpawns(double x1, double y1, double z1, double x2, double y2, double z2, double pitch1, double yaw1, double pitch2, double yaw2, World world) {
        blueSpawn = new Location(world, x1, y1, z1, (float) yaw1, (float) pitch1);
        purpleSpawn = new Location(world, x2, y2, z2, (float) yaw2, (float) pitch2);
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
            plugin.getLogger().info("STARTING TURRET ATTACKS!");
            BukkitTask turretAttack = new TurretAttackTask(plugin, teams, this).runTaskTimer(plugin, 10 * 20, 40);
            //make teh clock tick
            BukkitTask clockTask = new ClockTask(plugin, this, teams).runTaskTimer(plugin, 0, 20);
            //spawnMinionWave();
        }
    }
    
    public void clockTick() {
        clock++;
    }
    
    public void setClock(int time) {
        clock = time;
    }
    
    public void recall(Player player) {
        if (started) {
            teams.getChampion(player).setRecalling(true);
            player.sendMessage(ChatColor.GREEN + "Recalling. Hold still for 8 seconds!");
            
            BukkitTask championRecall = new RecallTask(plugin, teams, player).runTaskLater(this.plugin, 8 * 20);
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
