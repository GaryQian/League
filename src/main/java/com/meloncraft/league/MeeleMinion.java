/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;

/**
 *
 * @author Gary
 */
public class MeeleMinion {
    public double range;
    public boolean team;
    public Zombie minion;
    League plugin;
    public World world;
    
    public void MeeleMinion() {
        world = plugin.mainWorld;
        
                
    }
    
}
