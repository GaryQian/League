/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

/**
 *
 * @author Daniel
 */
public interface BasicChampion {
    //create variables to be used in champion classes.....values will be overwritten but java wanted initial values
    public final int BASEHEALTH = 80, BASEARMOR = 0, BASEAD = 6, BASEAP = 6, BASESPEED = 1, BASEMANA = 20, BASELEVEL = 1;
    public final double BASEHEALTHREGEN = 5, BASEMANAREGEN = 1;

    //class to add a level and increase stats
    public void AddLevel();
    
    //code to execute a Basic attack
    public void BasicAttack();
    
    //code to execute a Q attack
    public void QAttack();
    
    //code to execute a W attack
    public void WAttack();
    
    //code to execute an E attack
    public void EAttack();
    
    //code to execute an R attack
    public void RAttack();
    
    
    
    
    
}
