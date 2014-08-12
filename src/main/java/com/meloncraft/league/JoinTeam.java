/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 *
 * @author Gary
 */
public class JoinTeam implements Listener {
    Teams team;
    League plugin;
    FileConfiguration config = plugin.getConfig();
    
    public JoinTeam(League plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin = this.plugin;
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
    
    @EventHandler
    public void onSwitch(PlayerInteractEvent event) {
        //BLUE switcher clicked
        if (event.getClickedBlock().getLocation().getX() == plugin.getConfig().getInt("blue-switcher.x") && event.getClickedBlock().getLocation().getX() == plugin.getConfig().getInt("blue-switcher.y") && event.getClickedBlock().getLocation().getX() == plugin.getConfig().getInt("blue-switcher.z")) {
            if (team.getPurpleQueue().contains(event.getPlayer())) {
                team.removePurpleQueue(event.getPlayer());
                event.getPlayer().sendMessage("You are no longer on the Join-Purple Queue!");
            }
            else {
                if (team.getTeamSize("purple") < 5) {
                    team.addPurple(event.getPlayer());
                    if (!team.getBlueQueue().isEmpty()) {
                        team.getBlueQueue(0).sendMessage("You have been taken off the Queue and moved to BLUE!");
                        team.addBlue(team.removeBlueQueue());
                    }
                }
                else {
                    event.getPlayer().sendMessage("The PURPLE team is full! You have been added to the switch queue.");
                }
            }
        }
        if (event.getClickedBlock().getLocation().getX() == plugin.getConfig().getInt("purple-switcher.x") && event.getClickedBlock().getLocation().getX() == plugin.getConfig().getInt("purple-switcher.y") && event.getClickedBlock().getLocation().getX() == plugin.getConfig().getInt("purple-switcher.z")) {
            if (team.getPurpleQueue().contains(event.getPlayer())) {
                team.removePurpleQueue(event.getPlayer());
                event.getPlayer().sendMessage("You are no longer on the Join-Purple Queue!");
            }
            else {
                if (team.getTeamSize("blue") < 5) {
                    team.addBlue(event.getPlayer());
                    if (!team.getPurpleQueue().isEmpty()) {
                        team.getPurpleQueue(0).sendMessage("You have been taken off the Queue and moved to PURPLE!");
                        team.addPurple(team.removePurpleQueue());
                    }
                }
                else {
                    event.getPlayer().sendMessage("The BLUE team is full! You have been added to the switch queue.");
                }
            }
        }
    }
}

