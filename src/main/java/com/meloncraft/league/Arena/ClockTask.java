/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena;

import com.meloncraft.league.Champions.Champion;
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
        Champion champ;
        for (Player player : teams.getBlueTeam()) {
            champ = teams.getChampion(player);
            champ.respawnTimeTick();
            champ.spellCooldownTick();
        }
        for (Player player : teams.getPurpleTeam()) {
            champ = teams.getChampion(player);
            champ.respawnTimeTick();
            champ.spellCooldownTick();
        }
        arena.blueMid3.heal(15);
        arena.purpleMid3.heal(15);
        
        arena.blueTop3.heal(15);
        arena.purpleTop3.heal(15);
        
        arena.blueBot3.heal(15);
        arena.purpleBot3.heal(15);
        
        arena.blueNexus1.heal(15);
        arena.purpleNexus1.heal(15);
        
        arena.blueNexus2.heal(15);
        arena.purpleNexus2.heal(15);
    }
    
}
