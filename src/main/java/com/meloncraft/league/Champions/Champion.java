/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import com.meloncraft.league.Teams;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.entity.Player;

/**
 *
 * @author Gary
 */
public class Champion {
    public int health, maxHealth, armor, AD, AP, speed, mana, energy, level, kills, deaths, assists, range, gold;
    public double healthRegen, manaRegen, damage, incomingDamage;
    public String team; //TRUE = Blue FALSE = purple
    public Player player;
    public String champion;
    
    
    static MinecraftServer server = ((CraftServer)Bukkit.getServer()).getHandle().getServer();
    //adds Bonus stats above basic based on the champion.
    //public Champion(World world) {
    //public Champion(Player play, WorldServer world, GameProfile s, PlayerInteractManager itemInWorldManager) {
    public Champion(Player play, String champ, String tea) {
        champion = champ;
        maxHealth = 550;
        health = 550;
        armor = 0;
        AD = 6;
        AP = 6;
        speed = 1;
        mana = 20;
        healthRegen = 5;
        manaRegen = 1;
        level = 1;
        
        player = play;
        player.setHealthScale(40);
        player.setMaxHealth(maxHealth);
        player.setHealth(health);
        team = tea;
    }
    
    public void addLevel() {
        /* This should be overwritten in subclasses to make different champions scale different
        level++;
        health += 5;
        AD += 1;
        AP += 1;
        mana += 2;
        */
    }
    
    public void QAttack(){
        //Again over written
        //why not just make this an interface??
    }
    
    public String getTeam() {
        return team;
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
    
    public void hit(double damage) {
        incomingDamage = (double) damage * (1 / (1 + (armor / 100)));
        health -= damage;
        refresh();
    }
    
    public void refresh() {
        player.setHealth(health);
    }
}
