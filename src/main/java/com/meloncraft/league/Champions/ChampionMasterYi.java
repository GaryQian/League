/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import com.meloncraft.league.Champions.ChampionTasks.MasterYiQTask;
import com.meloncraft.league.League;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

/**
 *
 * @author Gary
 */
public class ChampionMasterYi implements ChampionInstance {
    public ItemStack[] kit;
    public Champion champion;
    private ItemMeta meta;
    League plugin;
    public boolean isMarksman;
    public double qScale = 0;
    public double wScale = 0;
    public double eScale = 0;
    public double rScale = 0;
    public double range;
    double qCost, wCost, eCost, rCost;
    Location loc;
    
    public ChampionMasterYi(Champion champ, League plug) {
        champion = champ;
        kit = new ItemStack[9];
        ArrayList<String> lore = new ArrayList<String>();
        plugin = plug;
        isMarksman = false;
        qScale = 30;
        wScale = 0;
        eScale = 0;
        rScale = 0;
        
        range = 2.5;
        
        qCost = 40;
        wCost = 100;
        eCost = 50;
        rCost = 150;
        
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
        if (target != null && target instanceof LivingEntity) {
            target.damage(champion.getDamage());
            return true;
        }
        return false;
    }
    
    public void qSpell(LivingEntity target, int level) {
        if (champion.qLevel > 0) {
            champion.sendMessage("Q");
            if (target != null && target instanceof LivingEntity) {
                
                loc = target.getLocation();
                champion.getPlayer().teleport(loc);
                if (target instanceof Player) {
                    plugin.teams.getChampion((Player) target).hit(champion.getDamage() + level * qScale);
                }
                else {
                    target.damage(champion.getDamage() + level * qScale);
                }
                champion.setQCooldown(10);
            }
            
            BukkitTask secondAttack = new MasterYiQTask(plugin, champion).runTaskLater(plugin, 5);
            BukkitTask thirdAttack = new MasterYiQTask(plugin, champion).runTaskLater(plugin, 10);
            BukkitTask fourthAttack = new MasterYiQTask(plugin, champion).runTaskLater(plugin, 15);
            /*new BukkitRunnable() {
            @Override
            public void run() {
            Entity ent = null;
            boolean cont = true;
            while (cont) {
            ent = champion.getClosestEntity(champion.range + 4 );
            if (plugin.teams.getTeam(ent).equals(champion.team)) {
            cont = false;
            }
            }
            if (ent != null) {
            if (ent instanceof Player) {
            plugin.teams.getChampion((Player) ent).hit(champion.getDamage() + level * qScale);
            }
            else {
            target.damage(champion.getDamage() + level * qScale);
            }
            }
            }
            }.runTaskLater(plugin, 5);*/
            if (target != null && target instanceof LivingEntity) {
                champion.getPlayer().teleport(loc);
            }
        }
        else {
            champion.player.sendMessage(ChatColor.RED + "You have not learned this skill yet!");
        }
        
        
        
        
        
    }
    
    public void wSpell(LivingEntity target, int level) {
        champion.sendMessage("W");
        champion.setWCooldown(30);
    }
    
    public void eSpell(LivingEntity target, int level) {
        champion.sendMessage("E");
        champion.setECooldown(15);
    }
    
    public void rSpell(LivingEntity target, int level) {
        champion.sendMessage("R");
        champion.player.setWalkSpeed(.31f);
        champion.drainMana(rCost);
        BukkitTask rtimer = new SlowTask(plugin, champion).runTaskLater(plugin, 15 * 20);
        champion.setRCooldown(90);
    }
    
    public boolean isMarksman() {
        return isMarksman;
    }
}