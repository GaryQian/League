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
import net.minecraft.server.v1_7_R4.AxisAlignedBB;
import net.minecraft.server.v1_7_R4.Entity;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.World;
import org.bukkit.Location;
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
    public Minion targetMinion;
    public Class<Minion> minionClass;
    public Class<EntityPlayer> playerClass;
    public Collection<Entity> allEntities;
    public boolean championAttacked;
    public double x1, x2, z1, z2;
    
    private double distance, distance2;
    private boolean isMinionInRange;
    private EntityPlayer targetPlayer;
    private Minion minion;
    private Champion champion;
    private Player targetBukkitPlayer;
    
    
    public Turret(double x, double y, double z, String tea, Teams te, League plug) {
        plugin = plug;
        team = tea;
        teams = te;
        health = 200;
        damageBase = 20;
        reward = 150;
        championAttacked = false;
        turretBody = new ArrayList<Location>();
        height = plugin.getConfig().getInt("turret-height");
        x1 = x - 1;
        x2 = x + 1;
        
        z1 = z - 1;
        z2 = z + 1;
        center = new Location(plugin.mainWorld, x, y, z);
        
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
        
        //allEntities = center.getWorld().getEntitiesByClasses(championClass, minionClass);
        //gets a list of all entities, and checks if they are within range. If they are, minions get priority, unless champion is being attacked.
        allEntities = world.a(minionClass, AxisAlignedBB.a(-80, 30, -80, 80, 10, 80));
        allEntities.addAll(world.a(playerClass, AxisAlignedBB.a(-80, 30, -80, 80, 10, 80)));
        
        distance2 = 10;
        isMinionInRange = false;
        target = null;
        targetPlayer = null;
        
        for (Entity entity : allEntities) {
            distance = entity.getBukkitEntity().getLocation().distance(center);
            if (distance < 8) {
                if (entity instanceof EntityPlayer) {
                    champion = teams.getChampion((Player) entity.getBukkitEntity());
                    if (!champion.getTeam().equals(team)) {
                        targetPlayer = (EntityPlayer) entity;
                    }
                }
                if (distance < distance2) {
                    if (entity instanceof Minion) {
                        
                        if (!minion.getTeam().equals(team)) {
                            isMinionInRange = true;
                            targetMinion = (Minion) entity;
                            distance2 = distance;
                        }
                    }
                    
                }
            }
        }
        
        if (championAttacked) {
            if (targetPlayer != null) {
                isMinionInRange = false;
            }
        }
        
        if (isMinionInRange) {
            target = targetMinion;
            return targetMinion;
        }
        
        else if (targetPlayer != null) {
            
            targetPlayer.getBukkitEntity().sendMessage("WARNING: You have been targeted by the tower!");
            target = targetPlayer;
            return targetPlayer;
        }
        else {
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
    public void attack() {
        if (target.equals(lastHit)) {
            
            damage = damageBase + (damageBase * Math.pow(1.25, timesHit));
            timesHit++;
        }
        else {
            timesHit = 1;
            damage = damageBase;
        }
        if (target instanceof EntityPlayer) {
            targetBukkitPlayer = (Player) target.getBukkitEntity();
            targetBukkitPlayer.sendMessage("You have been hit by the tower!");
            champion = teams.getChampion(targetBukkitPlayer);
            champion.hit(damage);
            lastHit = target;
            
        }
        else {
            targetMinion = (Minion) target;
            targetMinion.hit(damage);
            lastHit = target;
        }
        
    }
    
    public void attackSequence() {
        findTarget();
        attack();
        findTarget();
    }
    
    public void hit(double damage) {
        incomingDamage = damage * 0.6;
        health -= damage;
        refresh();
    }
    
    public void refresh() {
        
    }
}
