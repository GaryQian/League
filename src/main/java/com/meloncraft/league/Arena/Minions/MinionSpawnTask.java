/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena.Minions;

import com.meloncraft.league.League;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 *
 * @author Gary
 */
public class MinionSpawnTask extends BukkitRunnable{
    League plugin;
    public static List<Entity> blueMidMinions, purpleMidMinions, blueTopMinions, purpleTopMinions, blueBotMinions, purpleBotMinions;
    static MinionPopulation minionPopulation;
    
    public MinionSpawnTask(League plug) {
        this.plugin = plug;
    }
    
    
    //Task spawns a minion at each nexus
    @Override
    public void run() {
        // What you want to schedule goes here
        /*blueMidMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
        purpleMidMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
        
        blueTopMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
        purpleTopMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
        
        blueBotMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
        purpleBotMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
        */
        
        minionPopulation.addBlueMidMinion();
        minionPopulation.addBlueTopMinion();
        minionPopulation.addBlueBotMinion();
        minionPopulation.addPurpleMidMinion();
        minionPopulation.addPurpleTopMinion();
        minionPopulation.addPurpleBotMinion();
    }
}
