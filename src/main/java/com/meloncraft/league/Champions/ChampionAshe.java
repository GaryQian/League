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
public class ChampionAshe implements ChampionInstance {
    public ItemStack[] kit;
    public Champion champion;
    
    public ChampionAshe(Champion champ) {
        champion = champ;
        kit = new ItemStack[9];
        ArrayList<String> lore = new ArrayList<String>();
        
        kit[0] = new ItemStack(Material.ICE);
        kit[0].getItemMeta().setDisplayName(ChatColor.BLUE + "Frost Shot");
        lore.add("While active, each basic attack slows and uses mana");
        kit[0].getItemMeta().setLore(lore);
        lore.clear();
        
        kit[1] = new ItemStack(Material.NETHER_STAR);
        kit[1].getItemMeta().setDisplayName(ChatColor.BLUE + "Volley");
        lore.add("Fires 7 arrows in a cone, applying frost shot effects.");
        kit[1].getItemMeta().setLore(lore);
        lore.clear();
        
        
        kit[2] = new ItemStack(Material.RAW_CHICKEN);
        kit[2].getItemMeta().setDisplayName(ChatColor.BLUE + "Hawkshot");
        lore.add("Ashe gains bonus gold on killing minions. Activate to scout");
        kit[2].getItemMeta().setLore(lore);
        lore.clear();
        
        kit[3] = new ItemStack(Material.ARROW);
        kit[3].getItemMeta().setDisplayName(ChatColor.BLUE + "Enchanted Crystal Arrow");
        lore.add("Ashe fires a straight arrow, damaging and stunning the first champion it hits");
        lore.add("and applies slows to nearby champions");
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
