/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Gary
 */
public interface ChampionInstance {
    public ItemStack[] kit = new ItemStack[9];
    
    public void qSpell();
    
    public void wSpell();
    
    public void eSpell();
    
    public void rSpell();
    
}
