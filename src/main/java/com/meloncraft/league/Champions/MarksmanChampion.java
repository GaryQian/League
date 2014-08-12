/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 *
 * @author Daniel
 */
public class MarksmanChampion implements BasicChampion{
    //scaling factors per level....adjust to buff/debuff champions
    private final int HEALTHSCALE = 3, ARMORSCALE = 1, ADSCALE = 3, APSCALE = 1, SPEEDSCALE = 1, MANASCALE = 3;
    private final double HEALTHREGENSCALE = 0.5, MANAREGENSCALE = .5;
    //cooldown times for abilities
    private final int QCDMAX = 5, WCDMAX = 10, ECDMAX = 15, RCDMAX = 60;
    private final int QMANACOST = 10, WMANACOST = 10, EMANACOST = 10, RMANACOST = 25;
    
    //variables to store stat values
    public int Health, Armor, AttackDamage, AbilityPower, Speed, Mana, Level;
    public double HealthRegen, ManaRegen;
    //running count of how close to the ability
    public int QCooldown, WCooldown, ECooldown, RCooldown;
    
    //constructor to initialize a marksman champion
    public MarksmanChampion(){
            Health=BASEHEALTH;
            Armor=BASEARMOR;
            AttackDamage=BASEAD;
            AbilityPower=BASEAP;
            Speed=BASESPEED;
            Mana=BASEMANA;
            Level=1;
            HealthRegen=BASEHEALTHREGEN;
            ManaRegen=BASEMANAREGEN;
            }
    
    //method to scale powers when level is increased
    @Override
    public void AddLevel(){
        Health+=HEALTHSCALE;
        Armor+=ARMORSCALE;
        AttackDamage+=ADSCALE;
        AbilityPower+=APSCALE;
        Speed+=SPEEDSCALE;
        Mana+=MANASCALE;
        Level++;
        HealthRegen+=HEALTHREGENSCALE;
        ManaRegen+=MANAREGENSCALE;
    }
    
    public void BasicAttack(){
        /*
        Place Basic Attack code here
        */
    }
    
    public void QAttack(){
        if (QCooldown == 0) {
            if(Mana >= QMANACOST){
                Mana -= QMANACOST;
                QCooldown = QCDMAX;
                /*
                Place Q Spell code here
                */
            }
            else{
                //SOP("You do not have enough mana for this spell!");
            }
        }
        else{
            //SO{("This spell is not ready yet!");
        }
    }
    
    public void WAttack(){
        if (WCooldown == 0) {
            if(Mana >= WMANACOST){
                Mana -= WMANACOST;
                WCooldown = WCDMAX;
                /*
                Place W Spell code here
                */
            }
            else{
                //SOP("You do not have enough mana for this spell!");
            }
        }
        else{
            //SO{("This spell is not ready yet!");
        }
    }
    
    public void EAttack(){
        if (ECooldown == 0) {
            if(Mana >= EMANACOST){
                Mana -= EMANACOST;
                ECooldown = ECDMAX;
                /*
                Place E Spell code here
                */
            }
            else{
                //SOP("You do not have enough mana for this spell!");
            }
        }
        else{
            //SO{("This spell is not ready yet!");
        }
    }
    
    public void RAttack(){
        if (RCooldown == 0) {
            if(Mana >= RMANACOST){
                Mana -= RMANACOST;
                RCooldown = RCDMAX;
                /*
                Place E Spell code here
                */
            }
            else{
                //SOP("You do not have enough mana for this spell!");
            }
        }
        else{
            //SO{("This spell is not ready yet!");
        }
    }
}
