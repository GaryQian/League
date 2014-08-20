/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import com.meloncraft.league.Arena.Turret;
import com.meloncraft.league.Champions.Champion;
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
    private Champion tempChampion;
    
    
    public GeneralListeners(League plug, ArenaHandler are, Teams tea) {
        plugin = plug;
        arena = are;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        team = tea;
        FileConfiguration config = plugin.getConfig();
        world = plugin.mainWorld;
        blueSwitcher = new Location(world,  plugin.getConfig().getDouble("blue-switcher.x"),  plugin.getConfig().getDouble("blue-switcher.y"),  plugin.getConfig().getDouble("blue-switcher.z"));
        purpleSwitcher = new Location(world,  plugin.getConfig().getDouble("purple-switcher.x"),  plugin.getConfig().getDouble("purple-switcher.y"),   plugin.getConfig().getDouble("purple-switcher.z"));
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
            if (event.getClickedBlock().getLocation().getX() == blueSwitcher.getX() && event.getClickedBlock().getLocation().getY() == blueSwitcher.getY() && event.getClickedBlock().getLocation().getZ() == blueSwitcher.getZ()) {
                if (team.getPurpleQueue().contains(player)) {
                    team.removePurpleQueue(player);
                    player.sendMessage("You are no longer on the Join-Purple Queue!");
                }
                else {
                    if (team.getTeamSize("purple") < 5) {
                        if (team.blueChampions[team.blueTeam.lastIndexOf(player)] != null) {
                                tempChampion = team.getChampion(player);
                                team.removeChampion(player);
                                team.removePlayer(player);
                                team.addPurple(player);
                                team.setChampion(player, tempChampion);
                            }
                            //dont't set champion because no champ selected
                            else {
                                team.removePlayer(player);
                                team.addPurple(player);
                            }
                            
                        if (!team.getBlueQueue().isEmpty()) {
                            //set champions
                            if (team.purpleChampions[team.purpleTeam.lastIndexOf(team.getBlueQueue(0))] != null) {
                                tempChampion = team.getChampion(team.getBlueQueue(0));
                                team.removeChampion(team.getBlueQueue(0));
                                team.getBlueQueue(0).sendMessage("You have been taken off the Queue and moved to BLUE!");
                                team.addBlue(team.removeBlueQueue());
                                team.setChampion(team.getBlueQueue(0), tempChampion);
                            }
                            //dont't set champion because no champ selected
                            else {
                                team.getBlueQueue(0).sendMessage("You have been taken off the Queue and moved to BLUE!");
                                team.addBlue(team.removeBlueQueue());
                            }
                            
                        }
                    }
                    else {
                        player.sendMessage("The PURPLE team is full! You have been added to the switch queue.");
                    }
                }
            }

            //purple switcher clicked
            if (event.getClickedBlock().getLocation().getX() == purpleSwitcher.getX() && event.getClickedBlock().getLocation().getY() == purpleSwitcher.getY() && event.getClickedBlock().getLocation().getZ() == purpleSwitcher.getZ()) {
                if (team.getPurpleQueue().contains(player)) {
                    team.removePurpleQueue(player);
                    player.sendMessage("You are no longer on the Join-Purple Queue!");
                }
                else {
                    if (team.getTeamSize("blue") < 5) {
                        if (team.purpleChampions[team.purpleTeam.lastIndexOf(player)] != null) {
                                tempChampion = team.getChampion(player);
                                team.removeChampion(player);
                                team.removePlayer(player);
                                team.addBlue(player);
                                team.setChampion(player, tempChampion);
                            }
                            //dont't set champion because no champ selected
                            else {
                                team.removePlayer(player);
                                team.addBlue(player);
                            }
                            
                        if (!team.getPurpleQueue().isEmpty()) {
                            //set champions
                            if (team.blueChampions[team.blueTeam.lastIndexOf(team.getPurpleQueue(0))] != null) {
                                tempChampion = team.getChampion(team.getPurpleQueue(0));
                                team.removeChampion(team.getPurpleQueue(0));
                                team.getPurpleQueue(0).sendMessage("You have been taken off the Queue and moved to PURPLE!");
                                team.addPurple(team.removePurpleQueue());
                                team.setChampion(team.getPurpleQueue(0), tempChampion);
                            }
                            //dont't set champion because no champ selected
                            else {
                                team.getPurpleQueue(0).sendMessage("You have been taken off the Queue and moved to BLUE!");
                                team.addPurple(team.removePurpleQueue());
                            }
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
            Turret turret = null;
            if (team.getTeam(player).equals("blue")) {
                plugin.getLogger().info("TEST2");
                turret = arena.isPurpleTurret(event.getClickedBlock().getLocation());
                plugin.getLogger().info("TEST6");
                if (turret != null) {
                    plugin.getLogger().info("TEST4");
                    turret.hit(team.getChampion(player).getDamage());
                    player.sendMessage("YOU HIT THE TURRET");
                }
            }
            else if (team.getTeam(player).equals("purple")) {
                plugin.getLogger().info("TEST3");
                turret = arena.isBlueTurret(event.getClickedBlock().getLocation());
                plugin.getLogger().info("TEST7");
                if (turret != null) {
                    plugin.getLogger().info("TEST5");
                    turret.hit(team.getChampion(player).getDamage());
                    player.sendMessage("YOU HIT THE TURRET");
                }
            }
            else {
                
            }
        }
        
        //_______
        //RIGHT CLICK AIR ACTIONS
        
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            
        }
    }
}