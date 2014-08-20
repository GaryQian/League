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

/**
 *
 * @author Gary
 */
public class MinionPopulation {
    static League plugin;
    //List<Minion> blueMinions, purpleMinions;
    public static List<Entity> blueMidMinions, purpleMidMinions, blueTopMinions, purpleTopMinions, blueBotMinions, purpleBotMinions, allMinions;
    
    public MinionPopulation(League plug) {
        plugin = plug;
    }
    
    public int getPopulation() {
        allMinions.clear();
        for (Entity min : blueMidMinions) {
            allMinions.add(min);
        }
        for (Entity min : blueTopMinions) {
            allMinions.add(min);
        }
        for (Entity min : blueBotMinions) {
            allMinions.add(min);
        }
        for (Entity min : purpleMidMinions) {
            allMinions.add(min);
        }
        for (Entity min : purpleTopMinions) {
            allMinions.add(min);
        }
        for (Entity min : purpleBotMinions) {
            allMinions.add(min);
        }
        return allMinions.size();
    }
    
    public List<Entity> getAllMinions() {
        getPopulation();
        return allMinions;
        
    }
    
    public static void addBlueMidMinion() {
        blueMidMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
    }
    public static void addBlueTopMinion() {
        blueTopMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
    }
    public static void addBlueBotMinion() {
        blueBotMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
    }
    
    public static void addPurpleMidMinion() {
        purpleMidMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
    }
    public static void addPurpleTopMinion() {
        purpleTopMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
    }
    public static void addPurpleBotMinion() {
        purpleBotMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
    }
    
    public String getTeam(Entity ent) {
        if (blueMidMinions.contains(ent) || blueTopMinions.contains(ent) || blueBotMinions.contains(ent)) {
            return "blue";
        }
        if (purpleMidMinions.contains(ent) || purpleTopMinions.contains(ent) || purpleBotMinions.contains(ent)) {
            return "purple";
        }
        else {
            return null;
        }
    }
}
