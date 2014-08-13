/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena;

import com.meloncraft.league.League;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.World;

/**
 *
 * @author Gary
 */
public class Turret {
    League plugin;
    public Location center;
    public World world;
    public boolean team; //TRUE = blue FALSE = purple
    public List<Location> turretBody;
    
    public Turret(Location cent, boolean tea) {
        
    }
}
