/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import com.meloncraft.league.League;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

/**
 *
 * @author Gary
 */
public class Champion {
    public int level, kills, deaths, assists, gold, respawnTime, qCooldown, wCooldown, eCooldown, rCooldown, dCooldown, fCooldown, qLevel, wLevel, eLevel, rLevel, points;
    public double range, speed, incomingDamage;
    public double health, damage, mana, AP, armor, magicResist, healthRegen, manaRegen, lifesteal, attackSpeed;
    public double maxHealth, maxMana;
    public double baseHealth, baseDamage, baseMana;
    public double bonusHealth, bonusDamage, bonusMana, bonusHealthRegen, bonusManaRegen;
    public double healthScale, damageScale, manaScale;
    public ItemStack[] kit;
    public String team;
    public Player player;
    public String champion;
    public ChampionInstance championInstance;
    public boolean recalling, 
            //basicAttack cooldown
            basicAttack;
    
    League plugin;
    
    
    static MinecraftServer server = ((CraftServer)Bukkit.getServer()).getHandle().getServer();
    //adds Bonus stats above basic based on the champion.
    //public Champion(World world) {
    //public Champion(Player play, WorldServer world, GameProfile s, PlayerInteractManager itemInWorldManager) {
    public Champion(Player play, String champ, String tea, League plug) {
        plugin = plug;
        player = play;
        recalling = false;
        basicAttack = true;
        level = 1;
        respawnTime = 0;
        champion = champ;
        bonusHealth = 0;
        bonusDamage = 0;
        bonusMana = 0;
        bonusHealthRegen = 0;
        bonusManaRegen = 0;
        armor = 0;
        lifesteal = 0;
        attackSpeed = 27;
        points = 0;
        qCooldown = 0;
        wCooldown = 0;
        eCooldown = 0;
        rCooldown = 0;
        dCooldown = 0;
        fCooldown = 0;
        qLevel = 0;
        wLevel = 0;
        eLevel = 0;
        rLevel = 0;
        kit = new ItemStack[9];
        
        setChampion(champion);
        
        baseHealth = 550;
        healthScale = 92;
        baseDamage = 55;
        damageScale = 3;
        baseMana = 500;
        manaScale = 50;
        speed = 1;
        healthRegen = 5;
        manaRegen = 1;
        
        
        
        refresh();
        health = maxHealth;
        
        player.setHealthScale(40);
        player.setMaxHealth(maxHealth);
        player.setHealth(maxHealth);
        team = tea;
        giveKit();
    }
    
    public String getTeam() {
        return team;
    }
    
    public void addLevel() {
        level++;
        points++;
    }
    
    public void setChampion(String champ) {
        switch (champ) {
            case "Ashe": championInstance = (ChampionAshe) new ChampionAshe(this);
                        kit = championInstance.kit;
                break;
            case "Master Yi": championInstance = (ChampionMasterYi) new ChampionMasterYi(this);
                        kit = championInstance.kit;
                break;
        }
        kit[8] = new ItemStack(Material.PORTAL);
    }
    
    public void giveKit() {
        if (kit[0] != null) {
            player.getInventory().setItem(0, kit[0]);//q
        }
        if (kit[1] != null) {
            player.getInventory().setItem(1, kit[1]);//w
        }
        if (kit[2] != null) {
            player.getInventory().setItem(2, kit[2]);//e
        }
        if (kit[3] != null) {
            player.getInventory().setItem(3, kit[3]);//r
        }
        if (kit[4] != null) {
            player.getInventory().setItem(4, kit[4]);//d
        }
        if (kit[5] != null) {
            player.getInventory().setItem(5, kit[5]);//f
        }
        if (kit[8] != null) {
            player.getInventory().setItem(8, kit[8]);//recall
        }
        
        
        
        
        
        
        
    }
    
    public void qSpell(){
        
    }
    public void wSpell(){
        
    }
    public void eSpell(){
        
    }
    public void rSpell(){
        
    }
    public void dSpell(){
        
    }
    public void fSpell(){
        
    }
    
    public double getDamage() {
        return damage;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public String getName() {
        return champion;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getRespawnTime() {
        return respawnTime;
    }
    
    public void respawnTimeTick() {
        respawnTime--;
    }
    
    public void setRespawnTime(int time) {
        respawnTime = time;
    }
    
    public double basicAttack() {
        if (plugin.arena.started && basicAttack) {
            if (recalling) {
                setRecalling(false);
            }
            if (lifesteal > 0) {
                health += damage * (lifesteal / 100);
            }
            basicAttack = false;
            BukkitTask basicCooldown = new ChampionBasicCooldownTask(plugin, this).runTaskLater(plugin, (int) attackSpeed);
            return damage;
        }
        return 0;
    }
            

    public void hit(double damage) {
        incomingDamage = (double) damage * (1 / (1 + (armor / 100)));
        health -= incomingDamage;
        player.sendMessage("" + incomingDamage);
        player.damage(incomingDamage);
        recalling = false;
        refresh();
    }
    
    public void setRecalling(boolean rec) {
        if (!rec) {
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[WARNING]: " + ChatColor.GOLD + " Recalling Interrupted! Don't move at all to successfully recall");
        }
        recalling = rec;
    }
    
    public boolean getRecalling() {
        return recalling;
    }
    
    public void resetBasic() {
        basicAttack = true;
    }
    
    public void spellCooldownTick() {
        qCooldown--;
        if (qCooldown > 1) {
            player.getInventory().getItem(1).setAmount(qCooldown);
        }
        else {
            player.getInventory().getItem(1).setAmount(1);
            player.getInventory().getItem(1).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, qLevel);
        }
        
        wCooldown--;
        if (wCooldown > 1) {
            player.getInventory().getItem(1).setAmount(wCooldown);
        }
        else {
            player.getInventory().getItem(1).setAmount(1);
            player.getInventory().getItem(1).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, wLevel);
        }
        
        eCooldown--;
        if (eCooldown > 1) {
            player.getInventory().getItem(1).setAmount(eCooldown);
        }
        else {
            player.getInventory().getItem(1).setAmount(1);
            player.getInventory().getItem(1).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, eLevel);
        }
        
        rCooldown--;
        if (rCooldown > 1) {
            player.getInventory().getItem(1).setAmount(rCooldown);
        }
        else {
            player.getInventory().getItem(1).setAmount(1);
            player.getInventory().getItem(1).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, rLevel);
        }
        
        dCooldown--;
        if (dCooldown > 1) {
            player.getInventory().getItem(1).setAmount(dCooldown);
        }
        else {
            player.getInventory().getItem(1).setAmount(1);
            player.getInventory().getItem(1).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        }
        
        fCooldown--;
        if (fCooldown > 1) {
            player.getInventory().getItem(1).setAmount(fCooldown);
        }
        else {
            player.getInventory().getItem(1).setAmount(1);
            player.getInventory().getItem(1).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        }
    }
    
    public void sendMessage(String str) {
        player.sendMessage(str);
    }
    
    public void refresh() {
        
        //calculate stats
        maxHealth = baseHealth + bonusHealth + healthScale;
        damage = baseDamage + bonusDamage + damageScale * level;
        maxMana = baseMana + bonusMana + manaScale * level;
        
        player.setLevel(level);
        player.setMaxHealth(maxHealth);
        player.setHealth(health);
    }
}
