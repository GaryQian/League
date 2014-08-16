/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Arena.Monsters;

import net.minecraft.server.v1_7_R4.EntityEnderDragon;
import net.minecraft.server.v1_7_R4.World;

/**
 *
 * @author Gary
 */
public class Dragon extends EntityEnderDragon{
    World world;
    public Dragon(World world) {
        super(world);
    }
}
