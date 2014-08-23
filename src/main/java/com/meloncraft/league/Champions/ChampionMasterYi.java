/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import com.meloncraft.league.League;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Gary
 */
public class ChampionMasterYi implements ChampionInstance {
    public ItemStack[] kit;
    public Champion champion;
    private ItemMeta meta;
    League plugin;
    
    public ChampionMasterYi(Champion champ, League plug) {
        champion = champ;
        kit = new ItemStack[9];
        ArrayList<String> lore = new ArrayList<String>();
        plugin = plug;
        
        kit[0] = new ItemStack(Material.GOLD_SWORD);
        meta = kit[0].getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Alpha Strike");
        lore.add("Master Yi teleports between up to 4 targets, dealing damage to each");
        meta.setLore(lore);
        kit[0].setItemMeta(meta);
        lore.clear();
        
        kit[1] = new ItemStack(Material.EMERALD);
        meta = kit[1].getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Meditate");
        lore.add("Master Yi regains health and takes reduced damage for a short duration");
        meta.setLore(lore);
        kit[1].setItemMeta(meta);
        lore.clear();
        
        kit[2] = new ItemStack(Material.REDSTONE_TORCH_ON);
        meta = kit[2].getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Wuju Style");
        lore.add("Master Yi passively gains attack damage. Upon activation, Master Yi deals additional damage. Passive is lost on cooldown.");
        meta.setLore(lore);
        kit[2].setItemMeta(meta);
        lore.clear();
        
        kit[3] = new ItemStack(Material.BLAZE_POWDER);
        meta = kit[3].getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Highlander");
        lore.add("Increases movement speed and attack speed. Kills or assists extend Highlander's duration and reduces cooldowns");
        meta.setLore(lore);
        kit[3].setItemMeta(meta);
        lore.clear();
        
    }
    
    public ItemStack[] getKit() {
        return kit;
    }
    
    public boolean basicAttack(LivingEntity target) {
        target.damage(champion.getDamage());
        
        return false;
    }
    
    public void qSpell(Entity target) {
        champion.sendMessage("Q");
        
        champion.setQCooldown(10);
        
        
    }
    
    public void wSpell(Entity target) {
        champion.sendMessage("W");
        champion.setWCooldown(30);
    }
    
    public void eSpell(Entity target) {
        champion.sendMessage("E");
        champion.setECooldown(15);
    }
    
    public void rSpell(Entity target) {
        champion.sendMessage("R");
        champion.setRCooldown(60);
    }
    
}