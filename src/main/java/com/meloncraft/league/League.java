/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

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
    
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getConfig();
        teams = new Teams(this);
        arena = new ArenaHandler(this, teams);
        new GeneralListeners(this, arena, teams);
        new JoinTeam(this, teams);

        
        //new Teams();
        
        worlds = this.getServer().getWorlds();
        mainWorld = worlds.get(0);
        
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
                    
                }
                else if(args[0].equalsIgnoreCase("joinblue")) {
                    JoinTeam.team.addBlue(sender.getServer().getPlayer(sender.getName()));
                }
                else if(args[0].equalsIgnoreCase("joinpurple")) {
                    JoinTeam.team.addPurple(sender.getServer().getPlayer(sender.getName()));
                }
	}
	return false;
    }
    
}
