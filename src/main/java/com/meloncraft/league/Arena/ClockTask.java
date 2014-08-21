/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena;

import com.meloncraft.league.League;
import com.meloncraft.league.Teams;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Gary
 */
public class ClockTask extends BukkitRunnable{
    League plugin;
    ArenaHandler arena;
    Teams teams;
    
    public ClockTask(League plug, ArenaHandler aren, Teams team) {
        plugin = plug;
        arena = aren;
        teams = team;
    }
    
    public void run() {
        arena.clockTick();
        for (Player player : teams.getBlueTeam()) {
            teams.getChampion(player).respawnTimeTick();
        }
        for (Player player : teams.getPurpleTeam()) {
            teams.getChampion(player).respawnTimeTick();
        }
    }
    
}
