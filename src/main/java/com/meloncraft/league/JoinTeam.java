/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Gary
 */
public class JoinTeam implements Listener {
    Teams team = new Teams();
    
    
    public JoinTeam(League plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    public String decideSmallerTeam() {
        if (team.getTeamSize("blue") < team.getTeamSize("purple")) {
            return "blue";
        }
        else {
            return "purple";
        }
    }
    
    @EventHandler
    public void onJoin(PlayerLoginEvent event) {
        if (decideSmallerTeam().equals("blue")) {
            team.addBlue(event.getPlayer());
        }
        else {
            team.addPurple(event.getPlayer());
        }
    }
}
