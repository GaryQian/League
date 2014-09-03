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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.BlockIterator;

/**
 *
 * @author Gary
 */
public class Champion {
    public int level, kills, deaths, assists, gold, respawnTime, qCooldown, wCooldown, eCooldown, rCooldown, dCooldown, fCooldown, recallCooldown, qLevel, wLevel, eLevel, rLevel, points;
    public double range, incomingDamage, speed, xp, totalXp, xpNeeded;
    //public float speed;
    public double health, damage, mana, AP, armor, magicResist, healthRegen, manaRegen, lifesteal, attackSpeed, baseAttackSpeed;
    public double maxHealth, maxMana;
    public double baseHealth, baseDamage, baseMana;
    public double bonusHealth, bonusDamage, bonusMana, bonusHealthRegen, bonusManaRegen;
    public double healthScale, damageScale, manaScale;
    public ItemStack[] kit, inv;
    public String team;
    public Player player;
    public String champion;
    public ChampionInstance championInstance;
    public boolean recalling, 
            //basicAttack cooldown
            basicAttack;
    public ItemMeta portalMeta;
    
    League plugin;
    
    
    static MinecraftServer server = ((CraftServer)Bukkit.getServer()).getHandle().getServer();
    //adds Bonus stats above basic based on the champion.
    //public Champion(World world) {
    //public Champion(Player play, WorldServer world, GameProfile s, PlayerInteractManager itemInWorldManager) {
    public Champion(Player play, String champ, String tea, League plug) {
        plugin = plug;
        player = play;
        recalling = false;
        recallCooldown = 0;
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
        respawnTime = 0;
        inv = new ItemStack[103];
        xp = 0;
        totalXp = 0;
        xpNeeded = 280;
        
        
        
        setChampion(champion);
        
        baseHealth = 550;
        healthScale = 92;
        baseDamage = 55;
        damageScale = 3;
        baseMana = 500;
        manaScale = 50;
        speed = .2;
        healthRegen = 5;
        manaRegen = 1;
        baseAttackSpeed = 27;
        range = 5;
        
        
        attackSpeed = baseAttackSpeed;
        refresh();
        player.setWalkSpeed((float) speed);
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
    
    public void addXp(double num) {
        xp += num;
        totalXp += num;
        
        if (xp >= xpNeeded && level < 18) {
            addLevel();
        }
    }
    
    public void addLevel() {
        level++;
        points++;
        xp -= xpNeeded;
        xpNeeded += 100;
    }
    
    public void setChampion(String champ) {
        switch (champ) {
            case "Ashe": championInstance = new ChampionAshe(this, plugin);
                        kit = championInstance.getKit();
                break;
            case "Master Yi": championInstance = new ChampionMasterYi(this, plugin);
                        kit = championInstance.getKit();
                break;
        }
        kit[8] = new ItemStack(Material.PORTAL);
        portalMeta = kit[8].getItemMeta();
        portalMeta.setDisplayName("Recall");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Rightclick and stay still to go back to spawn");
        portalMeta.setLore(lore);
        kit[8].setItemMeta(portalMeta);
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
    
    public void storeInventory() {
        inv = player.getInventory().getContents();
    }
    
    public void returnInventory() {
        player.getInventory().setContents(inv);
    }
    
    public void qSpell(){
        championInstance.qSpell(getTarget(range), qLevel);
    }
    public void wSpell(){
        championInstance.wSpell(getTarget(range), wLevel);
    }
    public void eSpell(){
        championInstance.eSpell(getTarget(range), eLevel);
    }
    public void rSpell(){
        championInstance.rSpell(getTarget(range), rLevel);
    }
    public void dSpell(){
        
    }
    public void fSpell(){
        
    }
    
    
    
    public void setQCooldown(int i) {
        qCooldown = i;
    }
    public void setWCooldown(int i) {
        wCooldown = i;
    }
    public void setECooldown(int i) {
        eCooldown = i;
    }
    public void setRCooldown(int i) {
        rCooldown = i;
    }
    
    public void levelQ() {
        qLevel++;
    }
    public void levelW() {
        wLevel++;
    }
    public void levelE() {
        eLevel++;
    }
    public void levelR() {
        rLevel++;
    }
    
    public void drainMana(double amount) {
        mana -= amount;
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
                setRecalling(false, true);
            }
            boolean hit = false;
            LivingEntity targ = getTarget(range);
            hit = championInstance.basicAttack(targ);
            if (hit = true) {
                if (lifesteal > 0) {
                    health += damage * (lifesteal / 100);
                }
                basicAttack = false;
                BukkitTask basicCooldown = new ChampionBasicCooldownTask(plugin, this).runTaskLater(plugin, (int) attackSpeed);
                return damage;
            }
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
    
    public void setRecalling(boolean rec, boolean message) {
        if (!rec && message) {
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[WARNING]: " + ChatColor.GOLD + " Recalling Interrupted! Don't move at all to successfully recall");
        }
        
        if (rec) {
            recallCooldown = 8;
        }
        else {
            recallCooldown = 0;
        }
        
        recalling = rec;
    }
    
    public boolean getRecalling() {
        return recalling;
    }
    
    public void resetBasic() {
        basicAttack = true;
    }
    
    public void multiplyAttackSpeed(double speed) {
        attackSpeed = baseAttackSpeed / (1 + speed);
    }
    
    public void setAttackSpeed(double speed) {
        attackSpeed = speed;
    }
    
    public void resetAttackSpeed() {
        attackSpeed = baseAttackSpeed;
    }
    
    public void spellCooldownTick() {
        qCooldown--;
        if (qCooldown >= 1 && player.getInventory().getItem(0) != null) {
            player.getInventory().getItem(0).setAmount(qCooldown);
            player.getInventory().getItem(0).removeEnchantment(Enchantment.ARROW_DAMAGE);
        }
        else if (player.getInventory().getItem(0) != null) {
            player.getInventory().getItem(0).setAmount(1);
            player.getInventory().getItem(0).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, qLevel);
        }
        
        wCooldown--;
        if (wCooldown >= 1 && player.getInventory().getItem(1) != null) {
            player.getInventory().getItem(1).setAmount(wCooldown);
            player.getInventory().getItem(1).removeEnchantment(Enchantment.ARROW_DAMAGE);
        }
        else if (player.getInventory().getItem(1) != null) {
            player.getInventory().getItem(1).setAmount(1);
            player.getInventory().getItem(1).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, wLevel);
        }
        
        eCooldown--;
        if (eCooldown >= 1 && player.getInventory().getItem(2) != null) {
            player.getInventory().getItem(2).setAmount(eCooldown);
            player.getInventory().getItem(2).removeEnchantment(Enchantment.ARROW_DAMAGE);
        }
        else if (player.getInventory().getItem(2) != null) {
            player.getInventory().getItem(2).setAmount(1);
            player.getInventory().getItem(2).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, eLevel);
        }
        
        rCooldown--;
        if (rCooldown >= 1 && player.getInventory().getItem(3) != null) {
            player.getInventory().getItem(3).setAmount(rCooldown);
            player.getInventory().getItem(3).removeEnchantment(Enchantment.ARROW_DAMAGE);
        }
        else if (player.getInventory().getItem(3) != null) {
            player.getInventory().getItem(3).setAmount(1);
            player.getInventory().getItem(3).addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, rLevel);
        }
        
        /*dCooldown--;
        if (dCooldown > 1) {
            player.getInventory().getItem(4).setAmount(dCooldown);
        }
        else {
            player.getInventory().getItem(4).setAmount(1);
            player.getInventory().getItem(4).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        }
        
        fCooldown--;
        if (fCooldown > 1) {
            player.getInventory().getItem(5).setAmount(fCooldown);
        }
        else {
            player.getInventory().getItem(5).setAmount(1);
            player.getInventory().getItem(5).addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        }*/
        
        recallCooldown--;
        if (recallCooldown >= 1 && player.getInventory().getItem(3) != null) {
            player.getInventory().getItem(8).setAmount(recallCooldown);
            
        }
        else if (player.getInventory().getItem(8) != null) {
            player.getInventory().getItem(8).setAmount(1);
            
        }
    }
    
    public void sendMessage(String str) {
        player.sendMessage(str);
    }
    
    
    public List<Entity> getNearbyEntities(double rang) {
        
        return player.getNearbyEntities(2 * rang, rang, 2 * rang);
        
        
        /*ArrayList<Entity> entities = new ArrayList<Entity>();
        for (Entity entity : plugin.mainWorld.getEntities()) {
            if (player.getLocation().distance(entity.getLocation()) <= range && entity instanceof LivingEntity) {
                entities.add(entity);
            }
        }
        if (entities.size() > 0) {
            return entities;
        }
        return null;
                */
    }
    
    public LivingEntity getClosestEntity(double rang) {
        Entity ent = null;
        double dist = rang + 1;
        List<Entity> entities = getNearbyEntities(rang);
        if (entities != null) {
            for (Entity entity : entities) {
                if (entity.getLocation().distance(player.getLocation()) <= dist && entity instanceof LivingEntity) {
                    dist = entity.getLocation().distance(player.getLocation());
                    ent = entity;
                }
            }
            return (LivingEntity) ent;
        }
        return null;
    }
    
    public LivingEntity getClosestEnemyEntity(double rang) {
        Entity ent = null;
        double dist = rang + 1;
        List<Entity> entities = getNearbyEntities(rang);
        if (entities != null) {
            for (Entity entity : entities) {
                if (entity.getLocation().distance(player.getLocation()) <= dist && entity instanceof LivingEntity && !plugin.teams.getTeam(entity).equals(team)) {
                    dist = entity.getLocation().distance(player.getLocation());
                    ent = entity;
                }
            }
            return (LivingEntity) ent;
        }
        return null;
    }
    
    public Entity getClosestVisibleEntity(double rang) {
        Entity ent = null;
        double dist = rang + 1;
        List<Entity> entities = new ArrayList<Entity>();
        entities = getNearbyEntities(rang);
        if (entities != null) {
            for (Entity entity : entities) {
                if (entity.getLocation().distance(player.getLocation()) <= dist && player.hasLineOfSight(entity) && entity instanceof LivingEntity) {
                    dist = entity.getLocation().distance(player.getLocation());
                    ent = entity;
                }
            }
            return ent;
        }
        return null;
    }
    
    public LivingEntity getTarget(double rang) {
        
        Entity ent = null;
        BlockIterator iterator = new BlockIterator(player.getWorld(), player.getLocation().toVector(), player.getEyeLocation().getDirection(), 0, (int) rang + 1);
        while (iterator.hasNext()) {
            Block block = iterator.next();
            Location loc = null;
            for (Entity entity : getNearbyEntities(rang)) {
                loc = entity.getLocation();
                if (loc.getBlock().equals(block) && entity instanceof LivingEntity) {
                    plugin.getLogger().info(entity.toString());
                    ent = entity;
                    return (LivingEntity) entity;
                }
                loc = loc.add(0, 1, 0);
                if (loc.getBlock().equals(block) && entity instanceof LivingEntity) {
                    plugin.getLogger().info(entity.toString());
                    ent = entity;
                    return (LivingEntity) entity;
                }
            }
        }
        plugin.getLogger().info("no target aimed at");
        if (ent == null) {
            return (LivingEntity) getClosestEntity(range);
        }
        return null;
    }
    
    public void resetWalkSpeed() {
        player.setWalkSpeed((float) speed);
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
