/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
/**
 *
 * @author Gary
 */
public final class League extends JavaPlugin {
    @Override
    public void onEnable() {
        getConfig();
        new JoinTeam(this);
        new Teams();
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
		if (args[0].equalsIgnoreCase("version")) {
                    sender.sendMessage(version);
                    return true;
                }
                
                else if (args[0].equalsIgnoreCase("help") || args[1].equalsIgnoreCase("")) {
                    
                }
                
                else if(args[0].equalsIgnoreCase("default")) {
                    
                }
	}
	return false;
    }
    
}
