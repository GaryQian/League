/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meloncraft.league.Arena;

import com.meloncraft.league.Arena.Minions.MeeleMinion;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.EntityInsentient;

/**
 *
 * @author Gary Qian
 */
public enum CustomEntityType {
 
    MeeleMinion("MeeleMinion", 51, EntityType.SKELETON, EntitySkeleton.class, MeeleMinion.class),
    MageMinion("MageMinion", 51, EntityType.SKELETON, EntitySkeleton.class, MageMinion.class),
    Dragon("Dragon", 51, EntityType.ENDER_DRAGON, EntitySkeleton.class, Dragon.class),
    Baron("Baron", 51, EntityType.WITHER, EntityWither.class, Baron.class);
 
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