/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league.Champions;

import com.meloncraft.league.League;
import com.meloncraft.league.Teams;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Gary
 */
public class RecallTask extends BukkitRunnable{
    League plugin;
    Teams teams;
    Player player;
    
    public RecallTask(League plug, Teams team, Player play) {
        plugin = plug;
        teams = team;
        player = play;
    }
    
    public void run() {
        if (plugin.arena.started) {
            if (teams.getChampion(player).recalling) {
                if (teams.getTeam(player).equals("blue")) {
                    player.teleport(plugin.arena.blueSpawn);
                }
                else if (teams.getTeam(player).equals("purple")) {
                    player.teleport(plugin.arena.purpleSpawn);
                }
            }
            //else {
                //player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "RECALL INTERRUPTED!");
            //}
            teams.getChampion(player).setRecalling(false);
        }
    }
    
    
}
