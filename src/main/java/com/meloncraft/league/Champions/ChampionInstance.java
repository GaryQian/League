/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Gary
 */
public interface ChampionInstance {
    public ItemStack[] kit = new ItemStack[9];
    
    public ItemStack[] getKit();
    
    public void qSpell(Entity target);
    
    public void wSpell(Entity target);
    
    public void eSpell(Entity target);
    
    public void rSpell(Entity target);
    
}
