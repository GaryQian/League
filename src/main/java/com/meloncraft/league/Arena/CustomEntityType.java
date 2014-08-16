/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meloncraft.league.Arena;

import com.meloncraft.league.Arena.Monsters.Dragon;
import com.meloncraft.league.Arena.Minions.Minion;
import net.minecraft.server.v1_7_R4.EntityInsentient;
import net.minecraft.server.v1_7_R4.EntitySkeleton;
import net.minecraft.server.v1_7_R4.EntityWither;
import org.bukkit.entity.EntityType;

/**
 *
 * @author Gary Qian
 */
public enum CustomEntityType {
 
    MeeleMinion("MeeleMinion", 51, EntityType.SKELETON, EntitySkeleton.class, Minion.class),
    MageMinion("MageMinion", 51, EntityType.SKELETON, EntitySkeleton.class, Minion.class),
    Dragon("Dragon", 63, EntityType.ENDER_DRAGON, EntitySkeleton.class, Dragon.class),
    //Champion("Champion", 51, EntityType.PLAYER, EntityPlayer.class, Champion.class),
    Baron("Baron", 64, EntityType.WITHER, EntityWither.class, Baron.class);
 
    private String name;
    private int id;
    private EntityType entityType;
    private Class<? extends EntityInsentient> nmsClass;
    private Class<? extends EntityInsentient> customClass;
 
    private CustomEntityType(String name, int id, EntityType entityType, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass){
        this.name = name;
        this.id = id;
        this.entityType = entityType;
        this.nmsClass = nmsClass;
        this.customClass = customClass;
    }
 
    public String getName(){
        return this.name;
    }
 
    public int getID(){
        return this.id;
    }
 
    public EntityType getEntityType(){
        return this.entityType;
    }
 
    public Class<? extends EntityInsentient> getNMSClass(){
        return this.nmsClass;
    }
 
    public Class<? extends EntityInsentient> getCustomClass(){
        return this.customClass;
    }
 
}