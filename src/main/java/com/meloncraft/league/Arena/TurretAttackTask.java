/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena;

import com.meloncraft.league.ArenaHandler;
import com.meloncraft.league.League;
import com.meloncraft.league.Teams;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Gary
 */
public class TurretAttackTask extends BukkitRunnable{
    League plugin;
    Teams teams;
    ArenaHandler arena;
    
    public TurretAttackTask(League plug, Teams team, ArenaHandler aren) {
        plugin = plug;
        teams = team;
        arena = aren;
    }
    
    public void run() {
        arena.turretsAttack();
    }
    
}
