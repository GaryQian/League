/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import com.meloncraft.league.Arena.ArenaHandler;
import com.meloncraft.league.Arena.Minions.MinionPopulation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
/**
 *
 * @author Gary
 */
public final class League extends JavaPlugin {
    public List<World> worlds;
    public World mainWorld, tempWorld;
    private WorldCreator creator;
    public ArenaHandler arena;
    public static Teams teams;
    public MinionPopulation minionPopulation;
    public Connection SQL;
    public boolean connected;
    
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getConfig();
        creator = new WorldCreator("world");
        creator.generator(this.getConfig().getString("main-world-generator"));
        mainWorld = this.getServer().createWorld(creator);
        worlds = this.getServer().getWorlds();
        this.getServer().createWorld(creator);
        mainWorld = this.getServer().getWorld("world");
        mainWorld.setAutoSave(false);
        teams = new Teams(this);
        arena = new ArenaHandler(this, teams);
        new GeneralListeners(this, arena, teams);
        new JoinTeam(this, teams);
        minionPopulation = new MinionPopulation(this);
        
        
        //keep time day
        if (this.getConfig().getBoolean("maintain-day")) {
            arena.maintainDay();
        }
        
        //Connect to SQL
        if (getConfig().getBoolean("use-SQL")) {
            Connection conn;
            String url = "jdbc:mysql://" + getConfig().getString("database-host") + ":" + getConfig().getString("databse-port") + "/" + getConfig().getString("database-name");
            
            //Attempt to connect
            try {
                //Connection succeeded
                connected = true;
                conn = DriverManager.getConnection(url, getConfig().getString("SQL-username"), getConfig().getString("SQL-password"));
                
            }
            catch(Exception e) {
                connected = false;
            }
        }
        
        
        //Set Lobby Location
        /*Teams.setLobby(getConfig().getInt("blue-lobby.x"), 
                getConfig().getInt("blue-lobby.y"),
                getConfig().getInt("blue-lobby.z"),
                getConfig().getInt("purple-lobby.x"),
                getConfig().getInt("purple-lobby.y"),
                getConfig().getInt("purple-lobby.z"),
                mainWorld);*/
        
        
    }
    
    @Override
    public void onDisable() {
        arena.blueMid1.fixTurret();
        arena.blueMid2.fixTurret();
        arena.blueMid3.fixTurret();
        
        arena.blueBot1.fixTurret();
        arena.blueBot2.fixTurret();
        arena.blueBot3.fixTurret();
        
        arena.blueTop1.fixTurret();
        arena.blueTop2.fixTurret();
        arena.blueTop3.fixTurret();
        
        arena.blueNexus1.fixTurret();
        arena.blueNexus2.fixTurret();
        
        
        
        
        arena.purpleMid1.fixTurret();
        arena.purpleMid2.fixTurret();
        arena.purpleMid3.fixTurret();
        
        arena.purpleBot1.fixTurret();
        arena.purpleBot2.fixTurret();
        arena.purpleBot3.fixTurret();
        
        arena.purpleTop1.fixTurret();
        arena.purpleTop2.fixTurret();
        arena.purpleTop3.fixTurret();
        
        arena.purpleNexus1.fixTurret();
        arena.purpleNexus2.fixTurret();
        
        for (Player player : this.getServer().getOnlinePlayers()) {
            player.kickPlayer("Thanks for playing!");
        }
        
        rollback("world");
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
        
        if (cmd.getName().equalsIgnoreCase("champion")) { // If the player typed /basic then do the following...
            //Version command. Displays Version.
            switch (args[0].toLowerCase()) {
                case "ashe": teams.setChampion(sender.getServer().getPlayer(sender.getName()), "Ashe");
                    break;
                case "master": if (args[1].equalsIgnoreCase("yi")) {
                    teams.setChampion(sender.getServer().getPlayer(sender.getName()), "Master Yi");
                }
                    break;
                
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
        if (cmd.getName().equalsIgnoreCase("randomchampion")) {
            teams.setRandomChampion(sender.getServer().getPlayer(sender.getName()));
            sender.getServer().getPlayer(sender.getName()).sendMessage("You are:" + sender.getServer().getPlayer(sender.getName()).getName());
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("setclock")) {
            if (args[0].length() == 1) {
                arena.setClock((int) args[0].charAt(0));
            }
            else if (args[0].length() == 2) {
                arena.setClock((int) 10 * args[0].charAt(0) + args[0].charAt(1));
            }
            else if (args[0].length() == 3) {
                arena.setClock((int) 100 * args[0].charAt(0) + 10 * args[0].charAt(1) + args[0].charAt(2));
            }
            else if (args[0].length() == 4) {
                arena.setClock((int) 1000 * args[0].charAt(0) + 100 * args[0].charAt(1) + 10 * args[0].charAt(2) + args[0].charAt(3));
            }
            else if (args[0].length() == 5) {
                arena.setClock((int) 10000 * args[0].charAt(0) + 1000 * args[0].charAt(1) + 100 * args[0].charAt(2) + 10 * args[0].charAt(3) + args[0].charAt(4));
            }
        }
        
        if (cmd.getName().equalsIgnoreCase("recall")) {
            arena.recall(sender.getServer().getPlayer(sender.getName()));
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("shutdown")) {
            this.getServer().shutdown();
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("fixTurrets")) {
            arena.blueMid1.fixTurret();
            arena.blueMid2.fixTurret();
            arena.blueMid3.fixTurret();

            arena.blueBot1.fixTurret();
            arena.blueBot2.fixTurret();
            arena.blueBot3.fixTurret();

            arena.blueTop1.fixTurret();
            arena.blueTop2.fixTurret();
            arena.blueTop3.fixTurret();

            arena.blueNexus1.fixTurret();
            arena.blueNexus2.fixTurret();




            arena.purpleMid1.fixTurret();
            arena.purpleMid2.fixTurret();
            arena.purpleMid3.fixTurret();

            arena.purpleBot1.fixTurret();
            arena.purpleBot2.fixTurret();
            arena.purpleBot3.fixTurret();

            arena.purpleTop1.fixTurret();
            arena.purpleTop2.fixTurret();
            arena.purpleTop3.fixTurret();

            arena.purpleNexus1.fixTurret();
            arena.purpleNexus2.fixTurret();
            return true;
        }
	return false;
    }
    
    
    
    //Unloading maps, to rollback maps. Will delete all player builds until last server save
    public void unloadMap(String mapname){
        if(Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(mapname), false)){
            this.getLogger().info("Successfully unloaded " + mapname);
        }else{
            this.getLogger().severe("COULD NOT UNLOAD " + mapname);
        }
    }
    //Loading maps (MUST BE CALLED AFTER UNLOAD MAPS TO FINISH THE ROLLBACK PROCESS)
    public void loadMap(String mapname){
        Bukkit.getServer().createWorld(new WorldCreator(mapname));
    }
 
    //Maprollback method, because were too lazy to type 2 lines
    public void rollback(String mapname){
        unloadMap(mapname);
        loadMap(mapname);
    }
}
