/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Gary
 */
public class ChampionMasterYi implements ChampionInstance {
    public ItemStack[] kit;
    public Champion champion;
    
    public ChampionMasterYi(Champion champ) {
        champion = champ;
        kit = new ItemStack[9];
        ArrayList<String> lore = new ArrayList<String>();
        
        kit[0] = new ItemStack(Material.GOLD_SWORD);
        kit[0].getItemMeta().setDisplayName(ChatColor.GOLD + "Alpha Strike");
        lore.add("Master Yi teleports between up to 4 targets, dealing damage to each");
        kit[0].getItemMeta().setLore(lore);
        lore.clear();
        
        kit[1] = new ItemStack(Material.EMERALD);
        kit[1].getItemMeta().setDisplayName(ChatColor.GOLD + "Meditate");
        lore.add("Master Yi regains health and takes reduced damage for a short duration");
        kit[1].getItemMeta().setLore(lore);
        lore.clear();
        
        
        kit[2] = new ItemStack(Material.REDSTONE_TORCH_ON);
        kit[2].getItemMeta().setDisplayName(ChatColor.GOLD + "Wuju Style");
        lore.add("Master Yi passively gains attack damage. Upon activation, Master Yi deals additional damage. Passive is lost on cooldown.");
        kit[2].getItemMeta().setLore(lore);
        lore.clear();
        
        kit[3] = new ItemStack(Material.BLAZE_POWDER);
        kit[3].getItemMeta().setDisplayName(ChatColor.GOLD + "Highlander");
        lore.add("Increases movement speed and attack speed. Kills or assists extend Highlander's duration and reduces cooldowns");
        kit[3].getItemMeta().setLore(lore);
        lore.clear();
        
    }
    
    public void qSpell() {
        champion.sendMessage("Q");
    }
    
    public void wSpell() {
        champion.sendMessage("W");
    }
    
    public void eSpell() {
        champion.sendMessage("E");
    }
    
    public void rSpell() {
        champion.sendMessage("R");
    }
    
}