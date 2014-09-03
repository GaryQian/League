/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena.Minions;

import com.meloncraft.league.League;
import com.meloncraft.league.Teams;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author Gary
 */
public class MinionPopulation {
    League plugin;
    //List<Minion> blueMinions, purpleMinions;
    public List<LivingEntity> blueMidMinions, purpleMidMinions, blueTopMinions, purpleTopMinions, blueBotMinions, purpleBotMinions, allMinions;
    LivingEntity topMiddle, botMiddle, blueNexus, purpleNexus;
    Teams teams;
    
    public MinionPopulation(League plug, Teams team) {
        plugin = plug;
        teams = team;
        blueMidMinions = new ArrayList<LivingEntity>();
        blueTopMinions = new ArrayList<LivingEntity>();
        blueBotMinions = new ArrayList<LivingEntity>();
        
        purpleMidMinions = new ArrayList<LivingEntity>();
        purpleTopMinions = new ArrayList<LivingEntity>();
        purpleBotMinions = new ArrayList<LivingEntity>();
        
        allMinions = new ArrayList<LivingEntity>();
        
    }
    
    public void spawnTargetMinions() {
        //targets for the minions to walk towards
        topMiddle = (LivingEntity) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("top-middle.x"), plugin.getConfig().getDouble("top-middle.y") - 3, plugin.getConfig().getDouble("top-middle.z")), EntityType.ZOMBIE);
        botMiddle = (LivingEntity) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("bot-middle.x"), plugin.getConfig().getDouble("bot-middle.y") - 3, plugin.getConfig().getDouble("bot-middle.z")), EntityType.ZOMBIE);
        
        blueNexus = (LivingEntity) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y") - 6, plugin.getConfig().getDouble("blue-nexus.z")), EntityType.ZOMBIE);
        purpleNexus = (LivingEntity) plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y") - 6, plugin.getConfig().getDouble("purple-nexus.z")), EntityType.ZOMBIE);
        
        topMiddle.setMaxHealth(9999999);
        botMiddle.setMaxHealth(9999999);
        blueNexus.setMaxHealth(9999999);
        purpleNexus.setMaxHealth(9999999);
        
        topMiddle.setHealth(9999999);
        botMiddle.setHealth(9999999);
        blueNexus.setHealth(9999999);
        purpleNexus.setHealth(9999999);
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
    
    public LivingEntity target(LivingEntity entity, String team, String lane) {
        List<Entity> entities = new ArrayList<Entity>();
        entities = entity.getNearbyEntities(8, 5, 8);
        double dist, dist2;
        dist = 10;
        dist2 = 10;
        LivingEntity targ = null;
        Creature creature = (Creature) entity;
        String enemy = null;
        if (team.equals("blue")) enemy = "purple";
        else if (team.equals("purple")) enemy = "blue";
        boolean isMinion = false;
        //check for minions
        for (Entity ent : entities) {
            if (ent != null) {
                dist2 = entity.getLocation().distance(ent.getLocation());
                if (ent instanceof LivingEntity && enemy.equals(getTeam((LivingEntity) ent)) && dist2 < dist && !(ent instanceof Player)) {
                    dist = dist2;
                    targ = (LivingEntity) ent;
                    isMinion = true;
                }
            }
        }
        //check for players
        if (targ == null && !isMinion) {
            dist = 10;
            for (Entity ent : entities) {
                if (ent != null) {
                    
                    dist2 = entity.getLocation().distance(ent.getLocation());
                    if (ent instanceof Player && enemy.equals(teams.getTeam((Player) ent)) && dist2 < dist) {
                        dist = dist2;
                        targ = (LivingEntity) ent;
                    }
                }
            }
        }
        
        if (targ == null && !isMinion) {
            if (lane.equals("top")) {
                if (creature.getLocation().distance(topMiddle.getLocation()) < 10) {
                    if (team.equals("blue")) {
                        creature.setTarget(purpleNexus);
                    }
                    else if (team.equals("purple")) {
                        creature.setTarget(blueNexus);
                    }
                }
                else {
                    creature.setTarget(topMiddle);
                }
            }
            if (lane.equals("bot")) {
                if (creature.getLocation().distance(botMiddle.getLocation()) < 10) {
                    if (team.equals("blue")) {
                        creature.setTarget(purpleNexus);
                    }
                    else if (team.equals("purple")) {
                        creature.setTarget(blueNexus);
                    }
                }
                else {
                    creature.setTarget(botMiddle);
                }
                
            }
            
            
            //mid lane pathfinding
            if (lane.equals("mid")) {
                if (team.equals("blue")) {
                    creature.setTarget(purpleNexus);
                    topMiddle.setHealth(9999999);
                    botMiddle.setHealth(9999999);
                    blueNexus.setHealth(9999999);
                    purpleNexus.setHealth(9999999);
                }
                else if (team.equals("purple")) {
                    creature.setTarget(blueNexus);
                }
            }
        }
        else {
            creature.setTarget(targ);
        }
        return targ;
    }
}
