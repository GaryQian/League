/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena;

import com.meloncraft.league.Arena.Minions.Minion;
import com.meloncraft.league.Champions.Champion;
import com.meloncraft.league.League;
import com.meloncraft.league.Teams;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 *
 * @author Gary
 */
public class Turret {
    League plugin;
    public Location center, temp;
    public World world;
    public String team;
    public Teams teams;
    public List<Location> turretBody;
    public int timesHit, health, height;
    public double damage, damageBase, incomingDamage;
    public static int reward;
    public Entity target, lastHit;
    public Entity targetMinion;
    public Class<Minion> minionClass;
    public Class<EntityPlayer> playerClass;
    public Collection<Entity> allEntities;
    public boolean championAttacked;
    public double x1, x2, z1, z2;
    
    private double distance, distance2;
    private boolean isMinionInRange;
    private Player targetPlayer;
    private Minion minion;
    private Champion champion;
    
    
    public Turret(double x, double y, double z, String tea, Teams te, League plug) {
        plugin = plug;
        team = tea;
        teams = te;
        health = 2550;
        damageBase = 120;
        reward = 150;
        championAttacked = false;
        turretBody = new ArrayList<Location>();
        world = plugin.mainWorld;
        height = plugin.getConfig().getInt("turret-height");
        x1 = x - 1;
        x2 = x + 1;
        
        z1 = z - 1;
        z2 = z + 1;

        center = new Location(plugin.mainWorld, x, y, z);
        
        allEntities = new ArrayList<Entity>();
        //playerClass = new Class<"EntityPlayer">();
        
        addColumnToBody(center);
        
        temp = center;
        temp.setX(temp.getX() - 1);
        addColumnToBody(temp);
        
        temp = center;
        temp.setX(temp.getX() + 1);
        addColumnToBody(temp);
        
        temp.setZ(temp.getZ() - 1);
        addColumnToBody(temp);
        
        temp = center;
        temp.setZ(temp.getZ() + 1);
        addColumnToBody(temp);
        
        temp = center;
        temp.setX(temp.getX() - 1);
        temp.setZ(temp.getZ() - 1);
        addColumnToBody(temp);
        
        temp = center;
        temp.setX(temp.getX() + 1);
        temp.setZ(temp.getZ() + 1);
        addColumnToBody(temp);
        
        temp = center;
        temp.setX(temp.getX() + 1);
        temp.setZ(temp.getZ() - 1);
        addColumnToBody(temp);
        
        temp = center;
        temp.setX(temp.getX() - 1);
        temp.setZ(temp.getZ() + 1);
        addColumnToBody(temp);
        center.setY(y);
        center.setX(x);
        center.setZ(z);
    }
    
    public void addColumnToBody(Location loc) {
        //The number counted to is the height
        for (int count = 0; count < height; count++) {
            temp = loc;
            temp.setY(loc.getY() - count);
            turretBody.add(temp);
        }
    }
    
    public List<Location> getTurretBody() {
        return turretBody;
    }
    
    
    //checks if the location is part of the turret
    public boolean isTurretBody(Location location) {
        if (location.getX() >= x1 && location.getX() <= x2) {
            if (location.getZ() >= z1 && location.getZ() <= z2) {
                return true;
            }
        }
        return false;
        /*for (Location loc : turretBody) {
            if (loc.getBlock().getLocation().getX()== location.getX()) {
                if (loc.getBlock().getLocation().getZ()== location.getZ()) {
                    return true;
                }
            }
            else {
                return false;
            }
        }
        return false;*/
        
        //Location loc = new Location(plugin.mainWorld, x, y, z);
        //return turretBody.contains(loc.getBlock().getLocation());
    }
    
    
    //returns the targeted Craftbukkit entity (NOT BUKKIT ENTITY!)
    public Entity findTarget() {
        plugin.getLogger().info("TESTING1");
        //allEntities = center.getWorld().getEntitiesByClasses(championClass, minionClass);
        //AxisAlignedBB aabb = AxisAlignedBB.a(-80.0, 30.0, -80.0, 80.0, 10.0, 80.0);
        //gets a list of all entities, and checks if they are within range. If they are, minions get priority, unless champion is being attacked.
        //allEntities.addAll(world.getHandle().a(minionClass, aabb));
        //allEntities = world.getEntities();        
        //allEntities.addAll(world.getEntities());

        distance2 = 10;
        isMinionInRange = false;
        target = null;
        targetPlayer = null;
        targetMinion = null;
        plugin.getLogger().info("TESTING2");
        for (Entity entity : world.getEntities()) {
            plugin.getLogger().info(entity.toString());
        }
        for (Entity entity : world.getEntities()) {
            distance = entity.getLocation().distance(center);
            plugin.getLogger().info("DISTANCE###" + distance);
            plugin.getLogger().info(entity.getLocation().toString() + center.toString());
            if (distance < 10) {
                plugin.getLogger().info("TESTING5");
                if (entity instanceof CraftPlayer) {
                    champion = teams.getChampion((Player) entity);
                    if (!teams.getTeam((Player) entity).equals(team)) {
                        plugin.getLogger().info("TESTING6");
                        targetPlayer = (Player) entity;
                    }
                }
                if (distance < distance2) {
                    plugin.getLogger().info("TESTING7");
                    if (entity.getType() == EntityType.SKELETON) {
                        plugin.getLogger().info("TESTING8");
                        if (!plugin.minionPopulation.getTeam(entity).equals(team)) {
                            plugin.getLogger().info("TESTING9");
                            isMinionInRange = true;
                            targetMinion = entity;
                            distance2 = distance;
                        }
                    }
                    
                }
            }
        }
        plugin.getLogger().info("TESTING3");
        if (championAttacked) {
            if (targetPlayer != null) {
                isMinionInRange = false;
            }
        }
        
        
        if (isMinionInRange) {
            target = targetMinion;
            if (target != null) {
            plugin.getLogger().info("TARGET NAME" + target.toString());
            }
            return targetMinion;
        }
        
        else if (targetPlayer != null) {
            plugin.getLogger().info("TESTING9");
            targetPlayer.sendMessage("WARNING: You have been targeted by the tower!");
            target = targetPlayer;
            if (target != null) {
            plugin.getLogger().info("TARGET NAME" + target.toString());
            }
            return targetPlayer;
        }
        else {
            target = null;
            return null;
        }
    }
    
    public Location getLocation() {
        return center;
    }
    
    public void setChampionAttacked(boolean bool) {
        championAttacked = bool;
    }
    
    //attack target, calculates damage
    public boolean attack() {
        plugin.getLogger().info("TESTING4");
        if (target != null) {
            if (target.equals(lastHit)) {
                damage = damageBase + (damageBase * Math.pow(1.375, timesHit));
                if (damage > damageBase * 2.0) {
                    damage = damageBase * 2;
                }
                timesHit++;
            }
            else {
                timesHit = 1;
                damage = damageBase;
            }
            if (target.getType() == EntityType.PLAYER) {
                
                targetPlayer.sendMessage("You have been hit by the tower!");
                champion = teams.getChampion(targetPlayer);
                champion.hit(damage);
                lastHit = target;
                return true;

            }
            else {
                targetMinion = target;
                //targetMinion.hit(damage);
                lastHit = target;
                return true;
            }
        }
        return false;
        
    }
    
    public void attackSequence() {
        findTarget();
        if (attack()) {
            findTarget();
        }
        
    }
    
    public void hit(double damage) {
        incomingDamage = damage * 0.6;
        health -= damage;
        refresh();
    }
    
    public void refresh() {
        
    }
}
