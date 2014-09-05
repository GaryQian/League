/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena.Minions;

//import com.meloncraft.league.League;
import net.minecraft.server.v1_7_R4.EntityCreature;
import net.minecraft.server.v1_7_R4.PathfinderGoal;

public class PathfinderGoalWalkToTile extends PathfinderGoal{
    
    float speed;
    private EntityCreature entitycreature;
    //public League plugin;
    
    public PathfinderGoalWalkToTile(EntityCreature entitycreature, float speed){
        this.entitycreature = entitycreature;
        //plugin = plug;
    }
    
    
    @Override
    public boolean a() {
        
        //UPDATE INSTRUCTION: USE METHOD FOUND IN CORRESPONDING SPOT IN https://github.com/Bukkit/mc-dev/blob/master/net/minecraft/server/PathfinderGoalRandomStroll.java
        
        if (this.entitycreature.aN() >= 100) {
            return false;
        }
        else if (true) {
            return true;
        } 
        else {
            return false;
        }
        
        
        
        
        /*else if (this.a.aI().nextInt(120) != 0) {
            return false;
        } else {
            Vec3D vec3d = RandomPositionGenerator.a(this.a, 10, 7);

            if (vec3d == null) {
                return false;
            } else {
                this.b = vec3d.a;
                this.c = vec3d.b;
                this.d = vec3d.c;
                return true;
            }
        }
        
        /*
        
        if (this.entitycreature.aI() >= 100) {
            return false;
        } 
        else if (<shoudwalk?>) {
            return true;
        }
        else {
            return false;
        }
                */
    }
    
    @Override
    public void c(){
        this.entitycreature.getNavigation().a(x, y, z, speed);
    }
    
    
    
}