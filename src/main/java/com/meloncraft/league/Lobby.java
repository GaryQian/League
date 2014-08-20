/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import org.bukkit.Location;

/**
 *
 * @author Gary
 */
public class Lobby {
    public League plugin;
    public Teams teams;
    public Location blueLobby, purpleLobby;
    
    public Lobby(League plug, Teams team) {
        plugin = plug;
        teams = team;
        blueLobby = teams.blueLobby;
        purpleLobby = teams.purpleLobby;   
    }
}
