/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

/**
 *
 * @author Gary
 */
public class GeneralListeners implements Listener {
    Teams team;
    //League plugin;
    
    
    public GeneralListeners(League plugin) {
        FileConfiguration config = plugin.getConfig();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        //plugin = this.plugin;
    }
    
    
    //Prevent Items from dropping
    public void onItemDrop(ItemSpawnEvent event) {
        
        if (event.getEntityType() == EntityType.DROPPED_ITEM) {
            event.setCancelled(true);
        }
        
    }
}