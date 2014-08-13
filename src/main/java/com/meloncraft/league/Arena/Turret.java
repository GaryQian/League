/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena;

import com.meloncraft.league.Arena.Minions.MeeleMinion;
import com.meloncraft.league.Champions.Champion;
import com.meloncraft.league.League;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

/**
 *
 * @author Gary
 */
public class Turret {
    League plugin;
    public Location center, temp;
    public World world;
    public boolean team; //TRUE = blue FALSE = purple
    public List<Location> turretBody;
    public int damage;
    public static int reward;
    public Entity target;
    
    public Turret(Location cent, boolean tea) {
        
        damage = 20;
        reward = 150;
        
        
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
        //center.getWorld().getEntitiesByClasses(MeeleMinion, MageMinion, Champion);
        return target;
    }
    
    public Location getLocation() {
        return center;
    }
}
