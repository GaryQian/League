/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import static com.meloncraft.league.GeneralListeners.teams;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
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
        if (!plugin.arena.started) {
            event.getPlayer().teleport(new Location(plugin.mainWorld, 0, 15, 0));
            assignTeam(event.getPlayer());
            event.getPlayer().setHealthScale(40);
            event.getPlayer().setHealth(event.getPlayer().getMaxHealth());
            event.getPlayer().getInventory().clear();
        }
        else {
            if (teams.getTeam(event.getPlayer()).equals("blue")) {
                event.getPlayer().teleport(plugin.arena.blueSpawn);
            }
            else if (teams.getTeam(event.getPlayer()).equals("purple")) {
                event.getPlayer().teleport(plugin.arena.purpleSpawn);
            }
        }
        event.getPlayer().setHealthScale(40);
    }
    
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        if (plugin.arena.started) {
            if (!plugin.arena.disconnectedPlayers.contains(player.getUniqueId())) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Game in progress");
            }
        }
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (!plugin.arena.started) {
            player = event.getPlayer();
            team.removePlayer(player);
        }
    }
    
    
}

