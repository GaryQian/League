/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Gary
 */
public class ChampionAshe implements ChampionInstance {
    public ItemStack[] kit;
    public Champion champion;
    private ItemMeta meta;
    
    public ChampionAshe(Champion champ) {
        champion = champ;
        kit = new ItemStack[9];
        ArrayList<String> lore = new ArrayList<String>();
        
        kit[0] = new ItemStack(Material.ICE);
        meta = kit[0].getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Frost Shot");
        lore.add("While active, each basic attack slows and uses mana");
        meta.setLore(lore);
        kit[0].setItemMeta(meta);
        lore.clear();
        
        kit[1] = new ItemStack(Material.NETHER_STAR);
        meta = kit[1].getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Volley");
        lore.add("Fires 7 arrows in a cone, applying frost shot effects.");
        meta.setLore(lore);
        kit[1].setItemMeta(meta);
        lore.clear();
        
        kit[2] = new ItemStack(Material.RAW_CHICKEN);
        meta = kit[2].getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Hawkshot");
        lore.add("Ashe gains bonus gold on killing minions. Activate to scout");
        meta.setLore(lore);
        kit[2].setItemMeta(meta);
        lore.clear();
        
        kit[3] = new ItemStack(Material.ARROW);
        meta = kit[3].getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Enchanted Crystal Arrow");
        lore.add("Ashe fires a straight arrow, damaging and stunning the first champion it hits");
        lore.add("and applies slows to nearby champions");
        meta.setLore(lore);
        kit[3].setItemMeta(meta);
        lore.clear();
        
    }
    
    public ItemStack[] getKit() {
        return kit;
    }
    
    public void qSpell(Entity target) {
        champion.sendMessage("Q");
        champion.setQCooldown(2);
    }
    
    public void wSpell(Entity target) {
        champion.sendMessage("W");
        champion.setWCooldown(20);
    }
    
    public void eSpell(Entity target) {
        champion.sendMessage("E");
        champion.setECooldown(20);
    }
    
    public void rSpell(Entity target) {
        champion.sendMessage("R");
        champion.setRCooldown(90);
    }
    
}
