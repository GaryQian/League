/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Gary
 */
public interface ChampionInstance {
    public ItemStack[] kit = new ItemStack[9];
    
    public double qScale = 0;
    public double wScale = 0;
    public double eScale = 0;
    public double rScale = 0;
    
    public void setup();
    
    public ItemStack[] getKit();
    
    public boolean basicAttack(LivingEntity target);
    
    public void qSpell(LivingEntity target, int level);
    
    public void wSpell(LivingEntity target, int level);
    
    public void eSpell(LivingEntity target, int level);
    
    public void rSpell(LivingEntity target, int level);
    
    public boolean isMarksman();
}
