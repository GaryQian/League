/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena;

import com.meloncraft.league.Arena.Minions.Minion;
import com.meloncraft.league.Champions.Champion;
import com.meloncraft.league.League;
import java.util.Collection;
import java.util.List;
import net.minecraft.server.v1_7_R4.AxisAlignedBB;
import net.minecraft.server.v1_7_R4.Entity;
import net.minecraft.server.v1_7_R4.World;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author Gary
 */
public class Tower {
    League plugin;
    public Location center, temp;
    public World world;
    public boolean team; //TRUE = blue FALSE = purple
    public List<Location> turretBody;
    public int timesHit;
    public double damage, damageBase;
    public static int reward;
    public Entity target, targetChampion, lastHit;
    public Class<Minion> minionClass;
    public Class<Champion> championClass;
    public Collection<Entity> allEntities;
    public boolean championAttacked;
    
    private double distance, distance2;
    private boolean isMinionInRange;
    private Player targetChampionPlayer;
    private Minion minion;
    private Champion champion;
    
    
    public Tower(Location cent, boolean tea) {
        
        damageBase = 20;
        reward = 150;
        championAttacked = false;
        
        
        addColumnToBody(cent);
        
        temp = cent;
        temp.setX(temp.getBlockX() - 1);
        addColumnToBody(temp);
        
        temp = cent;
        temp.setX(temp.getBlockX() + 1);
        addColumnToBody(temp);
        
        temp.setZ(temp.getBlockZ() - 1);
        addColumnToBody(temp);
        
        temp = cent;
        temp.setZ(temp.getBlockZ() + 1);
        addColumnToBody(temp);
        
        temp = cent;
        temp.setX(temp.getBlockX() - 1);
        temp.setZ(temp.getBlockZ() - 1);
        addColumnToBody(temp);
        
        temp = cent;
        temp.setX(temp.getBlockX() + 1);
        temp.setZ(temp.getBlockZ() + 1);
        addColumnToBody(temp);
        
        temp = cent;
        temp.setX(temp.getBlockX() + 1);
        temp.setZ(temp.getBlockZ() - 1);
        addColumnToBody(temp);
        
        temp = cent;
        temp.setX(temp.getBlockX() - 1);
        temp.setZ(temp.getBlockZ() + 1);
        addColumnToBody(temp);
    }
    
    public void addColumnToBody(Location loc) {
        //The number counted to is the height
        for (int count = 0; count < 5; count++) {
            temp = loc;
            temp.setY(loc.getBlockY() - count);
            turretBody.add(temp);
        }
    }
    
    public List<Location> getTurretBody() {
        return turretBody;
    }
    
    public Entity findTarget() {
        
        //allEntities = center.getWorld().getEntitiesByClasses(championClass, minionClass);
        //gets a list of all entities, and checks if they are within range. If they are, minions get priority, unless champion is being attacked.
        allEntities = world.a(minionClass, AxisAlignedBB.a(-80, 30, -80, 80, 10, 80));
        allEntities.addAll(world.a(minionClass, AxisAlignedBB.a(-80, 30, -80, 80, 10, 80)));
        
        distance2 = 10;
        isMinionInRange = false;
        target = null;
        targetChampion = null;
        
        for (Entity entity : allEntities) {
            distance = entity.getBukkitEntity().getLocation().distance(center);
            if (distance < 8) {
            
                if (distance < distance2) {
                    if (entity instanceof Minion) {
                        minion = (Minion) entity;
                        if (minion.getTeam() != team) {
                            isMinionInRange = true;
                            target = entity;
                            distance2 = distance;
                        }
                    }
                    else if (entity instanceof Champion) {
                        if (!isMinionInRange) {
                            champion = (Champion) entity;
                            if (champion.getTeam() != team) {
                                isMinionInRange = true;
                                targetChampion = entity;
                                distance2 = distance;
                            }
                        }
                    }
                }
            }
        }
        
        if (championAttacked) {
            if (targetChampion != null) {
                isMinionInRange = false;
            }
        }
        
        if (!isMinionInRange) {
            return target;
        }
        else {
            targetChampionPlayer = (Player) targetChampion.getBukkitEntity();
            targetChampionPlayer.sendMessage("WARNING: You have been targeted by the tower!");
            target = targetChampion;
            return target;
        }
    }
    
    public Location getLocation() {
        return center;
    }
    
    public void setChampionAttacked(boolean bool) {
        championAttacked = bool;
    }
    
    public void attack() {
        if (target.equals(lastHit)) {
            
            damage = damageBase + (damageBase * Math.pow(1.25, timesHit));
            timesHit++;
        }
        else {
            timesHit = 1;
            damage = damageBase;
        }
        if (target instanceof Champion) {
            targetChampionPlayer = (Player) target.getBukkitEntity();
            targetChampionPlayer.sendMessage("You have been hit by the tower!");
        }
        
        target.hit(damage);
    }
}
