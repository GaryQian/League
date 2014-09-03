/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena.Minions;

import com.meloncraft.league.League;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Gary
 */
public class MinionTargetTask extends BukkitRunnable{
    League plugin;
    MinionPopulation pop;
    
    public MinionTargetTask(League plug, MinionPopulation po) {
        plugin = plug;
        pop = po;
    }
    
    public void run() {
        for (LivingEntity entity : pop.blueMidMinions) {
            pop.target(entity, "blue", "mid");
        }
        
        for (LivingEntity entity : pop.blueTopMinions) {
            pop.target(entity, "blue", "top");
        }
        
        for (LivingEntity entity : pop.blueBotMinions) {
            pop.target(entity, "blue", "bot");
        }
        
        
        
        for (LivingEntity entity : pop.purpleMidMinions) {
            pop.target(entity, "purple", "mid");
        }
        
        for (LivingEntity entity : pop.purpleTopMinions) {
            pop.target(entity, "purple", "top");
        }
        
        for (LivingEntity entity : pop.purpleBotMinions) {
            pop.target(entity, "purple", "bot");
        }
    }
    
    
    
}
