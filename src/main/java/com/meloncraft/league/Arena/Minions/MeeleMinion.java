/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena.Minions;

import net.minecraft.server.v1_7_R4.EntitySkeleton;
import net.minecraft.server.v1_7_R4.World;

/**
 *
 * @author Gary
 */
public class MeeleMinion extends Minion {
    World world;
    public MeeleMinion(World world) {
        super(world);
    }
}
