/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena;

import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Gary
 */
public class MinionFindTargetTask extends BukkitRunnable{
    MinionPopulation minionPopulation;
    public void run() {
        for (Minion minion : minionPopulation.getAllMinions()) {
            for (Entity targ : minion.getNearbyEntities(3, 3, 3)) {
                
            }
        }
    }
}
