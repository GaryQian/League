/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena.Minions;

import com.meloncraft.league.League;
import java.util.List;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Gary
 */
public class MinionSpawnTask extends BukkitRunnable{
    League plugin;
    public static List<Entity> blueMidMinions, purpleMidMinions, blueTopMinions, purpleTopMinions, blueBotMinions, purpleBotMinions;
    static MinionPopulation minionPopulation;
    int maxHealth;
    
    public MinionSpawnTask(League plug, int health) {
        this.plugin = plug;
        maxHealth = health;
        minionPopulation = plugin.minionPopulation;
    }
    
    
    //Task spawns a minion at each nexus
    @Override
    public void run() {
        PotionEffect effect = new PotionEffect(PotionEffectType.SPEED, 9999, 1);
        // What you want to schedule goes here
        /*blueMidMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
        purpleMidMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
        
        blueTopMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
        purpleTopMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
        
        blueBotMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("blue-nexus.x"), plugin.getConfig().getDouble("blue-nexus.y"), plugin.getConfig().getDouble("blue-nexus.z")) , EntityType.ZOMBIE));
        purpleBotMinions.add(plugin.mainWorld.spawnEntity(new Location(plugin.mainWorld, plugin.getConfig().getDouble("purple-nexus.x"), plugin.getConfig().getDouble("purple-nexus.y"), plugin.getConfig().getDouble("purple-nexus.z")) , EntityType.ZOMBIE));
        */
        
        minionPopulation.addBlueMidMinion();
        minionPopulation.blueMidMinions.get(minionPopulation.blueMidMinions.size() - 1).setMaxHealth(maxHealth);
        minionPopulation.blueMidMinions.get(minionPopulation.blueMidMinions.size() - 1).setHealth(maxHealth);
        //minionPopulation.blueMidMinions.get(minionPopulation.blueMidMinions.size() - 1).addPotionEffect(effect);
        
        minionPopulation.addBlueTopMinion();
        minionPopulation.blueTopMinions.get(minionPopulation.blueMidMinions.size() - 1).setMaxHealth(maxHealth);
        minionPopulation.blueTopMinions.get(minionPopulation.blueMidMinions.size() - 1).setHealth(maxHealth);
        //minionPopulation.blueTopMinions.get(minionPopulation.blueMidMinions.size() - 1).addPotionEffect(effect);
        
        minionPopulation.addBlueBotMinion();
        minionPopulation.blueBotMinions.get(minionPopulation.blueMidMinions.size() - 1).setMaxHealth(maxHealth);
        minionPopulation.blueBotMinions.get(minionPopulation.blueMidMinions.size() - 1).setHealth(maxHealth);
        //minionPopulation.blueBotMinions.get(minionPopulation.blueMidMinions.size() - 1).addPotionEffect(effect);
        
        
        minionPopulation.addPurpleMidMinion();
        minionPopulation.purpleMidMinions.get(minionPopulation.purpleMidMinions.size() - 1).setMaxHealth(maxHealth);
        minionPopulation.purpleMidMinions.get(minionPopulation.purpleMidMinions.size() - 1).setHealth(maxHealth);
        //minionPopulation.purpleMidMinions.get(minionPopulation.purpleMidMinions.size() - 1).addPotionEffect(effect);
        
        minionPopulation.addPurpleTopMinion();
        minionPopulation.purpleTopMinions.get(minionPopulation.purpleMidMinions.size() - 1).setMaxHealth(maxHealth);
        minionPopulation.purpleTopMinions.get(minionPopulation.purpleMidMinions.size() - 1).setHealth(maxHealth);
        //minionPopulation.purpleTopMinions.get(minionPopulation.purpleMidMinions.size() - 1).addPotionEffect(effect);
        
        minionPopulation.addPurpleBotMinion();
        minionPopulation.purpleBotMinions.get(minionPopulation.purpleMidMinions.size()).setMaxHealth(maxHealth);
        minionPopulation.purpleBotMinions.get(minionPopulation.purpleMidMinions.size()).setHealth(maxHealth);
        //minionPopulation.purpleBotMinions.get(minionPopulation.purpleMidMinions.size() - 1).addPotionEffect(effect);

    }
}
