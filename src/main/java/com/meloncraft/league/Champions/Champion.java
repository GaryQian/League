/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.PlayerInteractManager;
import net.minecraft.server.v1_7_R4.WorldServer;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;

/**
 *
 * @author Gary
 */
public class Champion extends EntityPlayer{
    public int health, armor, AD, AP, speed, mana, energy, level;
    public double healthRegen, manaRegen;
    public boolean team; //TRUE = Blue FALSE = purple
    
    
    static MinecraftServer server = ((CraftServer)Bukkit.getServer()).getHandle().getServer();
    //adds Bonus stats above basic based on the champion.
    //public Champion(World world) {
    public Champion(WorldServer world, GameProfile s, PlayerInteractManager itemInWorldManager) {
        super(server , world, s, itemInWorldManager);
        health = 80;
        armor = 0;
        AD = 6;
        AP = 6;
        speed = 1;
        mana = 20;
        healthRegen = 5;
        manaRegen = 1;
        level = 1;
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
    
    public boolean getTeam() {
        return team;
    }
    
    public void hit(double damage) {
        
    }
}
