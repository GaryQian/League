/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import com.meloncraft.league.Arena.Minions.MinionPopulation;
import java.util.List;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
/**
 *
 * @author Gary
 */
public final class League extends JavaPlugin {
    public List<World> worlds;
    public World mainWorld;
    
    public ArenaHandler arena;
    public static Teams teams;
    public MinionPopulation minionPopulation;
    
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getConfig();
        teams = new Teams(this);
        arena = new ArenaHandler(this, teams);
        new GeneralListeners(this, arena, teams);
        new JoinTeam(this, teams);
        minionPopulation = new MinionPopulation(this);
        
        
        //keep time day
        if (this.getConfig().getBoolean("maintain-day")) {
            arena.maintainDay();
        }
        

        
        //new Teams();
        
        worlds = this.getServer().getWorlds();
        mainWorld = this.getServer().getWorld("world");
        this.getLogger().info(mainWorld.getName());
        
        //Set Lobby Location
        Teams.setLobby(getConfig().getInt("blue-lobby.x"), 
                getConfig().getInt("blue-lobby.y"),
                getConfig().getInt("blue-lobby.z"),
                getConfig().getInt("purple-lobby.x"),
                getConfig().getInt("purple-lobby.y"),
                getConfig().getInt("purple-lobby.z"),
                mainWorld);
        
        
    }
    
    @Override
    public void onDisable() {
        
    }
    
    String version = "1.0.0";
    
    /*
    Basic Commands:
    */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if (cmd.getName().equalsIgnoreCase("l")) { // If the player typed /basic then do the following...
            //Version command. Displays Version.
		if (args[0].equalsIgnoreCase("version")) {
                    sender.sendMessage(version);
                    return true;
                }
                
                else if(args[0].equalsIgnoreCase("default")) {
                    return true;
                }
                else if(args[0].equalsIgnoreCase("joinblue")) {
                    JoinTeam.team.addBlue(sender.getServer().getPlayer(sender.getName()));
                    return true;
                }
                else if(args[0].equalsIgnoreCase("joinpurple")) {
                    JoinTeam.team.addPurple(sender.getServer().getPlayer(sender.getName()));
                    return true;
                }
	}
        if (cmd.getName().equalsIgnoreCase("forcestart")) {
            sender.getServer().getPlayer(sender.getName()).sendMessage("FORCE STARTING GAME!");
            arena.startGame();
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("team")) {
            sender.getServer().getPlayer(sender.getName()).sendMessage(teams.getTeam(sender.getServer().getPlayer(sender.getName())));
            return true;
        }
	return false;
    }
    
}
