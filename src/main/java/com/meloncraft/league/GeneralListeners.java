/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import com.meloncraft.league.Arena.Turret;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 *
 * @author Gary
 */
public class GeneralListeners implements Listener {
    public static Teams team;
    //League plugin;
    public Location blueSwitcher, purpleSwitcher;
    public World world;
    Player player;
    League plugin;
    ArenaHandler arena;
    
    
    public GeneralListeners(League plug, ArenaHandler are, Teams tea) {
        plugin = plug;
        arena = are;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        team = tea;
        FileConfiguration config = plugin.getConfig();
        world = plugin.mainWorld;
        blueSwitcher = new Location(world, plugin.getConfig().getInt("blue-switcher.x"), plugin.getConfig().getInt("blue-switcher.y"), plugin.getConfig().getInt("blue-switcher.z"));
        purpleSwitcher = new Location(world, plugin.getConfig().getInt("purple-switcher.x"), plugin.getConfig().getInt("purple-switcher.y"), plugin.getConfig().getInt("purple-switcher.z"));
    }
    
    
    //Prevent Items from dropping
    @EventHandler
    public void onItemDrop(ItemSpawnEvent event) {
        
        //if (event.getEntityType() == EntityType.DROPPED_ITEM) {
            event.setCancelled(true);
        //}
        
    }
    
    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }
    
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        
        player = event.getPlayer();
        
        
        
        
        //_________
        //RIGHT CLICK ACTIONS
        
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
        //BLUE switcher clicked
            plugin.getLogger().info("" + event.getClickedBlock().getLocation().getX());
            if (event.getClickedBlock().getLocation().equals(blueSwitcher)) {
                if (team.getPurpleQueue().contains(player)) {
                    team.removePurpleQueue(player);
                    player.sendMessage("You are no longer on the Join-Purple Queue!");
                }
                else {
                    if (team.getTeamSize("purple") < 5) {
                        team.removePlayer(player);
                        team.addPurple(player);
                        if (!team.getBlueQueue().isEmpty()) {
                            team.getBlueQueue(0).sendMessage("You have been taken off the Queue and moved to BLUE!");
                            team.addBlue(team.removeBlueQueue());
                        }
                    }
                    else {
                        player.sendMessage("The PURPLE team is full! You have been added to the switch queue.");
                    }
                }
            }

            //purple switcher clicked
            if (event.getClickedBlock().getLocation().equals(purpleSwitcher)) {
                if (team.getPurpleQueue().contains(player)) {
                    team.removePurpleQueue(player);
                    player.sendMessage("You are no longer on the Join-Purple Queue!");
                }
                else {
                    if (team.getTeamSize("blue") < 5) {
                        team.removePlayer(player);
                        team.addBlue(player);
                        if (!team.getPurpleQueue().isEmpty()) {
                            team.getPurpleQueue(0).sendMessage("You have been taken off the Queue and moved to PURPLE!");
                            team.addPurple(team.removePurpleQueue());
                        }
                    }
                    else {
                        player.sendMessage("The BLUE team is full! You have been added to the switch queue.");
                    }
                }
            }
        }



        //_______
        //LEFT CLICK ACTIONS
        
        else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Turret turret;
            if (team.getTeam(player).equals("blue")) {
                turret = arena.isBlueTurret(event.getClickedBlock().getLocation());
                if (turret != null) {
                    turret.hit(team.getChampion(player).getDamage());
                }
            }
        }
        
        //_______
        //RIGHT CLICK AIR ACTIONS
        
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            
        }
    }
}