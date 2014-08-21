/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import com.meloncraft.league.League;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 *
 * @author Gary
 */
public class Champion {
    public int level, kills, deaths, assists, gold, respawnTime, qCooldown, wCooldown, eCooldown, rCooldown, dCooldown, fCooldown;
    public double range, speed, incomingDamage;
    public double health, damage, mana, AP, armor, magicResist, healthRegen, manaRegen, lifesteal;
    public double maxHealth, maxMana;
    public double baseHealth, baseDamage, baseMana;
    public double bonusHealth, bonusDamage, bonusMana, bonusHealthRegen, bonusManaRegen;
    public double healthScale, damageScale, manaScale;
    public String team; //TRUE = Blue FALSE = purple
    public Player player;
    public String champion;
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
        
        baseHealth = 550;
        healthScale = 92;
        baseDamage = 55;
        damageScale = 3;
        baseMana = 500;
        manaScale = 50;
        speed = 1;
        healthRegen = 5;
        manaRegen = 1;
        
        
        player = play;
        player.setHealthScale(40);
        player.setMaxHealth(maxHealth);
        player.setHealth(health);
        team = tea;
    }
    
    public String getTeam() {
        return team;
    }
    
    public void addLevel() {
        level++;
    }
    
    public void qAttack(){
        
    }
    public void wAttack(){
        
    }
    public void eAttack(){
        
    }
    public void rAttack(){
        
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
    
    public void refresh() {
        
        //calculate stats
        maxHealth = baseHealth + bonusHealth + healthScale;
        damage = baseDamage + bonusDamage + damageScale * level;
        maxMana = baseMana + bonusMana + manaScale * level;
        
        player.setLevel(level);
        player.setMaxHealth(maxHealth);
        //player.setHealth(health);
    }
}
