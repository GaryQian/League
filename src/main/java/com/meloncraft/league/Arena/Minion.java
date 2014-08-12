/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

/**
 *
 * @author Gary
 */
public interface Minion extends LivingEntity{
    
    //sets up the health and other stats of minions
    public void setupMinion();
    
    public Entity getTarget();
}
