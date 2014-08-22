/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import com.meloncraft.league.League;

/**
 *
 * @author Gary
 */
public class ChampionSpellCooldownTask {
    League plugin;
    Champion champion;
    
    public ChampionSpellCooldownTask(League plug, Champion champ) {
        plugin = plug;
        champion = champ;
    }
    
    public void run() {
        champion.resetBasic();
    }
            
    
}

