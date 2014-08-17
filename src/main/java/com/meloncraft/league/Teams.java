/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 *
 * @author Gary
 */
public class Teams {
    private static List<Player> blueTeam;
    private static List<Player> purpleTeam;
    private static List<Player> blueQueue, purpleQueue;
    private static int count;
    private static int emptySlot, emptySlot2;
    private static double x, y, z;
    private static Location blueLobby, purpleLobby;
    public List<World> worlds;
    public World mainWorld;
    League plugin;
    
    public Teams(League plug) {
        //blueTeam  = new Player[4];
        //purpleTeam  = new Player[4];
        int count = 0;
        emptySlot = 0;
        plugin = plug;
        setLobby(plugin.getConfig().getInt("blue-lobby.x"), plugin.getConfig().getInt("blue-lobby.y"), plugin.getConfig().getInt("blue-lobby.z"), plugin.getConfig().getInt("purple-lobby.x"), plugin.getConfig().getInt("purple-lobby.y"), plugin.getConfig().getInt("purple-lobby.z"), mainWorld);
    }
    
    public static void setLobby(double x1, double y1, double z1, double x2, double y2, double z2, World world) {
        blueLobby = new Location(world, x1, y1, z1);
        purpleLobby = new Location(world, x2, y2, z2);
    }
    
    public static String getSmallerTeam() {
        if (getTeamSize("blue") <= getTeamSize("purple")) {
            return "blue";
        }
        else {
            return "purple";
        }
    }
    
    public static int getTeamSize(String team) {
        count = 0;
        emptySlot = 0;
        if (team.equalsIgnoreCase("blue")) {
            return blueTeam.size();

        }
        else {
            
            return purpleTeam.size();
        }
    }
    
    public static boolean addBlue(Player player) {
        if (getTeamSize("blue") < 5) {
            blueTeam.add(player);
            player.sendMessage("You have joined the Blue Team!");
            player.teleport(blueLobby);
            return true;
        }
        else {
            return false;
        }
        
    }
    
    public static boolean addPurple(Player player) {
        if (getTeamSize("purple") < 5) {
            purpleTeam.add(player);
            player.sendMessage("You have joined the Purple Team!");
            player.teleport(purpleLobby);
            return true;
        }
        else {
            return false;
        }
        
    }
    
    public static int getBlueSize() {
        return blueTeam.size();
    }
    
    public static int getPurpleSize() {
        return purpleTeam.size();
    }
    
    public static List<Player> getBlueTeam() {
        return blueTeam;
    }
    
    public static List<Player> getPurpleTeam() {
        return purpleTeam;
    }
    
    public static boolean removePlayer(Player player) {
        if (blueTeam.contains(player)) {
            blueTeam.remove(player);
            return true;
        }
        else if (purpleTeam.contains(player)) {
            purpleTeam.remove(player);
            return true;
        }
        else {
            return false;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    //-------------------------
    //adds a player to the queue to join Blue
    public static void addBlueQueue(Player player) {
        blueQueue.add(player);
    }
    //adds a player to the queue to join Purple
    public static void addPurpleQueue(Player player) {
        purpleQueue.add(player);
    }
    //---------------------------
    public static List<Player> getBlueQueue() {
        return blueQueue;
    }
    public static List<Player> getPurpleQueue() {
        return purpleQueue;
    }
    //----------------------------
    public static Player removeBlueQueue() {
        return blueQueue.remove(0);
    }
    public static Player removePurpleQueue() {
        return purpleQueue.remove(0);
    }
    
    public static boolean removeBlueQueue(Player player) {
        return blueQueue.remove(player);
    }
    public static boolean removePurpleQueue(Player player) {
        return purpleQueue.remove(player);
    }
    //----------------------------
    public static Player getBlueQueue(int i) {
        return blueQueue.get(i);
    }
    public static Player getPurpleQueue(int i) {
        return purpleQueue.get(i);
    }
    
}
