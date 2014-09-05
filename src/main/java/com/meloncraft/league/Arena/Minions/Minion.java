/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena.Minions;

import java.lang.reflect.Field;
import net.minecraft.server.v1_7_R4.EntityHuman;
import net.minecraft.server.v1_7_R4.EntityVillager;
import net.minecraft.server.v1_7_R4.EntityZombie;
import net.minecraft.server.v1_7_R4.GenericAttributes;
import net.minecraft.server.v1_7_R4.PathfinderGoalFloat;
import net.minecraft.server.v1_7_R4.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_7_R4.World;
import org.bukkit.craftbukkit.v1_7_R4.util.UnsafeList;
//import org.bukkit.World;


/**
 *
 * @author Gary
 */
public class Minion extends EntityZombie{
    //public League plugin;
    
    //sets up the health and other stats of minions
    public Minion(World world) {
        super(world);
        
        //plugin = plug;
        
        /*try {
            
            Field gsa = net.minecraft.server.v1_7_R4.PathfinderGoalSelector.class.getDeclaredField("a");
            gsa.setAccessible(true);
            
            
            gsa.set(this.goalSelector, new UnsafeList());
            gsa.set(this.targetSelector, new UnsafeList());
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        
        this.getNavigation().b(true);
        this.goalSelector.a(1, new PathfinderGoalWalkToTile(this, .2F));//this.bw can be replaced by a custom speed
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, false));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityVillager.class, 1.0D, true));
        //this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        //this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
        //this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
        //this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        //this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        //this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
        //this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 0, true));
        //this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.a(0.6F, 1.8F);
    */
    }
    
    
    /*public Entity getTarget() {
        world.
        return
    }*/
    
    //UPDATE INSTRUCTIONS: OVERRRIDE THE METHOD IN EntityZombie.java containing "this.getAttributeInstance(GenericAttributes.b).setValue("
    
    @Override
    protected void aD() {
        super.aD();
        this.getAttributeInstance(GenericAttributes.b).setValue(150.0D);
    }
}
