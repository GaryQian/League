/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena.Minions;

import com.meloncraft.league.League;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

/**
 *
 * @author Gary
 */
public class MinionPopulation {
    static League plugin;
    //List<Minion> blueMinions, purpleMinions;
    public static List<Minion> blueMidMinions, purpleMidMinions, blueTopMinions, purpleTopMinions, blueBotMinions, purpleBotMinions, allMinions;
    
    public MinionPopulation(League plug) {
        plugin = plug;
    }
    
    public int getPopulation() {
        for (Minion min : blueMidMinions) {
            allMinions.add(min);
        }
        for (Minion min : blueTopMinions) {
            allMinions.add(min);
        }
        for (Minion min : blueBotMinions) {
            allMinions.add(min);
        }
        for (Minion min : purpleMidMinions) {
            allMinions.add(min);
        }
        for (Minion min : purpleTopMinions) {
            allMinions.add(min);
        }
        for (Minion min : purpleBotMinions) {
            allMinions.add(min);
        }
        return allMinions.size();
    }
    
    public List<Minion> getAllMinions() {
        getPopulation();
        return allMinions;
        
    }
    
    public static void addBlueMidMinion() {
        blueMidMinions.add((Minion) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
    }
    public static void addBlueTopMinion() {
        blueTopMinions.add((Minion) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
    }
    public static void addBlueBotMinion() {
        blueBotMinions.add((Minion) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
    }
    
    public static void addPurpleMidMinion() {
        purpleMidMinions.add((Minion) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
    }
    public static void addPurpleTopMinion() {
        purpleTopMinions.add((Minion) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
    }
    public static void addPurpleBotMinion() {
        purpleBotMinions.add((Minion) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
    }
}
