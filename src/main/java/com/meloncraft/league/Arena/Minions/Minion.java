/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena.Minions;

import net.minecraft.server.v1_7_R4.EntitySkeleton;
import net.minecraft.server.v1_7_R4.GenericAttributes;
import net.minecraft.server.v1_7_R4.World;
//import org.bukkit.World;


/**
 *
 * @author Gary
 */
public class Minion extends EntitySkeleton{
    //sets up the health and other stats of minions
    public Minion(World world) {
        super(world);
    }
    
    /*public Entity getTarget() {
        world.
        return
    }*/
    
    //UPDATE INSTRUCTIONS: OVERRRIDE THE METHOD IN EntityZombie.java containing "this.getAttributeInstance(GenericAttributes.b).setValue("
    
    @Override
    protected void aD() {
        super.aD();
        this.getAttributeInstance(GenericAttributes.b).setValue(3000.0D);
    }
}
