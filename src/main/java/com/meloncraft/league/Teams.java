/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author Gary
 */
public class Teams {
    static Player[] blueTeam;
    static Player[] purpleTeam;
    int count;
    int emptySlot;
    int emptySlot2;
    public Teams() {
        Location blueSpawn, purpleSpawn;
        blueTeam  = new Player[4];
        purpleTeam  = new Player[4];
        int count = 0;
        emptySlot = 0;
    }
    
    public int getEmptySlot(String team) {
        count = 0;
        if (team.equalsIgnoreCase("blue")) {
            while (count < 5) {
                if (blueTeam[count] == null) {
                    emptySlot = count;
                    count = 10;
                }
                else {
                    count++;
                    emptySlot = 5;
                }
            }
            return emptySlot;
        }
        else {
            while (count < 5) {
                if (purpleTeam[count] == null) {
                    emptySlot = count;
                    count = 10;
                }
                else {
                    count++;
                    emptySlot = 5;
                }
            }
            return emptySlot;
        }
        
    }
    
    public int getTeamSize(String team) {
        count = 0;
        emptySlot = 0;
        if (team.equalsIgnoreCase("blue")) {
            while (count < 5) {
                if (blueTeam[count] == null) {
                    count++;
                }
                else {
                    count++;
                    emptySlot++;
                }
            }
            return emptySlot;
        }
        else {
            while (count < 5) {
                if (purpleTeam[count] == null) {
                    count++;
                }
                else {
                    count++;
                    emptySlot++;
                }
            }
            return emptySlot;
        }
    }
    
    public boolean addBlue(Player player) {
        emptySlot2 = getEmptySlot("blue");
        if (emptySlot2 < 5) {
            blueTeam[emptySlot2] = player;
            player.sendMessage("You have joined the Blue Team!");
            return true;
        }
        else {
            return false;
        }
        
    }
    
    public boolean addPurple(Player player) {
        emptySlot2 = getEmptySlot("purple");
        if (emptySlot2 < 5) {
            purpleTeam[emptySlot2] = player;
            player.sendMessage("You have joined the Purple Team!");
            player.
            return true;
        }
        else {
            return false;
        }
        
    }
    
    public int getBlueSize() {
        emptySlot2 = getTeamSize("blue");
        return emptySlot2;
    }
    
    public int getPurpleSize() {
        emptySlot2 = getTeamSize("purple");
        return emptySlot2;
    }
    
    public Player[] getBlueTeam() {
        return blueTeam;
    }
    
    public Player[] getPurpleTeam() {
        return purpleTeam;
    }
    
}
