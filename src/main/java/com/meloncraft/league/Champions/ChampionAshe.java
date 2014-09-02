/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import com.meloncraft.league.League;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

/**
 *
 * @author Gary
 */
public class ChampionAshe implements ChampionInstance {
    public ItemStack[] kit;
    public Champion champion;
    private ItemMeta meta;
    public boolean qActive;
    League plugin;
    double qCost, wCost, eCost, rCost;
    public boolean isMarksman;
    
    public ChampionAshe(Champion champ, League plug) {
        champion = champ;
        kit = new ItemStack[9];
        ArrayList<String> lore = new ArrayList<String>();
        qActive = false;
        plugin = plug;
        isMarksman = true;
        
        qCost = 40;
        wCost = 100;
        eCost = 50;
        rCost = 150;
        
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
    
    public boolean basicAttack(LivingEntity target) {
        Location loc = champion.player.getEyeLocation();
        loc.setY(loc.getY() + .2);
        loc.add(champion.player.getEyeLocation().getDirection().multiply(1.5));
        Entity arrow = champion.player.getWorld().spawnArrow(loc, champion.player.getEyeLocation().getDirection(), 4f, 0);
        
        if (target != null && target instanceof LivingEntity) {
            target.damage(champion.getDamage());
            if (qActive) {
                if (target instanceof Player) {
                    Player play = (Player) target;
                    play.setWalkSpeed(.12f);
                    champion.drainMana(qCost);
                    BukkitTask slowdown = new SlowTask(plugin, plugin.teams.getChampion(play)).runTaskLater(plugin, 35);
                }
            }
            BukkitTask removeArrow = new RemoveProjectileTask(arrow).runTaskLater(plugin, 3);
            return true;
        }
        BukkitTask removeArrow = new RemoveProjectileTask(arrow).runTaskLater(plugin, 3);
        
        return false;
    }
    
    public ItemStack[] getKit() {
        return kit;
    }
    
    public void qSpell(LivingEntity target, int level) {
        if (champion.qLevel > 0) {
            meta = kit[0].getItemMeta();
            ArrayList<String> lore = new ArrayList<String>();
            champion.sendMessage("Q");
            
            if (qActive) {
                qActive = false;
                meta.setDisplayName(ChatColor.BLUE + "Frost Shot - " + ChatColor.RED + "OFF");
                lore.add("While active, each basic attack slows and uses mana");
                meta.setLore(lore);
                kit[0].setItemMeta(meta);
                champion.getPlayer().getInventory().setItem(0, kit[0]);
                lore.clear();
            }
            else {
                qActive = true;
                meta.setDisplayName(ChatColor.BLUE + "Frost Shot - " + ChatColor.GREEN + "ON");
                lore.add("While active, each basic attack slows and uses mana");
                meta.setLore(lore);
                kit[0].setItemMeta(meta);
                champion.getPlayer().getInventory().setItem(0, kit[0]);
                lore.clear();
            }
        }
        else {
            champion.player.sendMessage(ChatColor.RED + "You have not learned this skill yet!");
        }
        
    }
    
    public void wSpell(LivingEntity target, int level) {
        champion.sendMessage("W");
        champion.drainMana(wCost);
        champion.setWCooldown(20);
    }
    
    public void eSpell(LivingEntity target, int level) {
        champion.sendMessage("E");
        champion.drainMana(eCost);
        champion.setECooldown(20);
    }
    
    public void rSpell(LivingEntity target, int level) {
        champion.sendMessage("R");
        champion.drainMana(rCost);
        champion.setRCooldown(90);
    }
    
    public boolean isMarksman() {
        return isMarksman;
    }
    
}
