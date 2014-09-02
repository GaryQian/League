/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena.Minions;

import com.meloncraft.league.League;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

/**
 *
 * @author Gary
 */
public class MinionPopulation {
    League plugin;
    //List<Minion> blueMinions, purpleMinions;
    public List<LivingEntity> blueMidMinions, purpleMidMinions, blueTopMinions, purpleTopMinions, blueBotMinions, purpleBotMinions, allMinions;
    
    public MinionPopulation(League plug) {
        plugin = plug;
        blueMidMinions = new ArrayList<LivingEntity>();
        blueTopMinions = new ArrayList<LivingEntity>();
        blueBotMinions = new ArrayList<LivingEntity>();
        
        purpleMidMinions = new ArrayList<LivingEntity>();
        purpleTopMinions = new ArrayList<LivingEntity>();
        purpleBotMinions = new ArrayList<LivingEntity>();
        
        allMinions = new ArrayList<LivingEntity>();
    }
    
    public int getPopulation() {
        allMinions.clear();
        for (LivingEntity min : blueMidMinions) {
            allMinions.add((LivingEntity) min);
        }
        for (LivingEntity min : blueTopMinions) {
            allMinions.add((LivingEntity) min);
        }
        for (LivingEntity min : blueBotMinions) {
            allMinions.add((LivingEntity) min);
        }
        for (LivingEntity min : purpleMidMinions) {
            allMinions.add((LivingEntity) min);
        }
        for (LivingEntity min : purpleTopMinions) {
            allMinions.add((LivingEntity) min);
        }
        for (LivingEntity min : purpleBotMinions) {
            allMinions.add((LivingEntity) min);
        }
        return allMinions.size();
    }
    
    public List<LivingEntity> getAllMinions() {
        getPopulation();
        return allMinions;
        
    }
    
    public void addBlueMidMinion() {
        blueMidMinions.add((LivingEntity) (LivingEntity) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
    }
    public void addBlueTopMinion() {
        blueTopMinions.add((LivingEntity) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
    }
    public void addBlueBotMinion() {
        blueBotMinions.add((LivingEntity) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
    }
    
    public void addPurpleMidMinion() {
        purpleMidMinions.add((LivingEntity) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
    }
    public void addPurpleTopMinion() {
        purpleTopMinions.add((LivingEntity) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
    }
    public void addPurpleBotMinion() {
        purpleBotMinions.add((LivingEntity) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
    }
    
    public String getTeam(LivingEntity ent) {
        if (blueMidMinions.contains(ent) || blueTopMinions.contains(ent) || blueBotMinions.contains(ent)) {
            return "blue";
        }
        if (purpleMidMinions.contains(ent) || purpleTopMinions.contains(ent) || purpleBotMinions.contains(ent)) {
            return "purple";
        }
            return null;
    }
}
