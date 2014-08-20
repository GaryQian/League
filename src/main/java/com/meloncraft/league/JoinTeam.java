/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author Gary
 */
public class JoinTeam implements Listener {
    public static Teams team;
    //public Location blueSwitcher, purpleSwitcher;
    public World world;
    Player player;
    League plugin;
    
    public JoinTeam(League plug, Teams tea) {
        plugin = plug;
        team = tea;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        FileConfiguration config = plugin.getConfig();
        world = plugin.mainWorld;
        //blueSwitcher = new Location(world, plugin.getConfig().getInt("blue-switcher.x"), plugin.getConfig().getInt("blue-switcher.y"), plugin.getConfig().getInt("blue-switcher.z"));
        //purpleSwitcher = new Location(world, plugin.getConfig().getInt("purple-switcher.x"), plugin.getConfig().getInt("purple-switcher.y"), plugin.getConfig().getInt("purple-switcher.z"));
    }
    
    public String decideSmallerTeam() {
        if (team.getTeamSize("blue") < team.getTeamSize("purple")) {
            return "blue";
        }
        else {
            return "purple";
        }
    }
    
    public void assignTeam(Player player) {
        if (team.getSmallerTeam().equals("blue")) {
            if (team.addBlue(player)) {
            plugin.getLogger().info(player.getName() + " Joined Blue");
            }
        }
        else {
            if (team.addPurple(player)) {
            plugin.getLogger().info(player.getName() + " Joined Purple");
            }
        }
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        assignTeam(event.getPlayer());
        event.getPlayer().setHealthScale(40);
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        player = event.getPlayer();
        team.removePlayer(player);
    }
    
    
}

