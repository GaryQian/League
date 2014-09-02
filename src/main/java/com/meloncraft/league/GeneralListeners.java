/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.meloncraft.league;

import com.meloncraft.league.Arena.ArenaHandler;
import com.meloncraft.league.Arena.Turret;
import com.meloncraft.league.Champions.Champion;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Gary
 */
public class GeneralListeners implements Listener {
    public static Teams teams;
    //League plugin;
    public Location blueSwitcher, purpleSwitcher;
    public World world;
    Player player;
    League plugin;
    ArenaHandler arena;
    private Champion tempChampion;
    Location tempLoc;
    //private int count;
    
    
    public GeneralListeners(League plug, ArenaHandler are, Teams tea) {
        plugin = plug;
        arena = are;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        teams = tea;
        
        
        
        FileConfiguration config = plugin.getConfig();
        world = plugin.mainWorld;
        blueSwitcher = new Location(world,  plugin.getConfig().getDouble("blue-switcher.x"),  plugin.getConfig().getDouble("blue-switcher.y"),  plugin.getConfig().getDouble("blue-switcher.z"));
        purpleSwitcher = new Location(world,  plugin.getConfig().getDouble("purple-switcher.x"),  plugin.getConfig().getDouble("purple-switcher.y"),   plugin.getConfig().getDouble("purple-switcher.z"));
    }
    
    
    //Prevent Items from dropping
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        
        //if (event.getEntityType() == EntityType.DROPPED_ITEM) {
        event.setCancelled(true);
        
        if (event.getItemDrop().getItemStack().getType() == Material.PORTAL) {
            //event.getPlayer().getInventory().clear(event.getPlayer().getInventory().first(Material.PORTAL));
            event.getPlayer().getInventory().setItem(8, new ItemStack(Material.PORTAL));
            event.getPlayer().getInventory().getItem(8).setItemMeta(teams.getChampion(event.getPlayer()).portalMeta);
        }
    }
    
    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }
    
    
    //prevent zombies burning
    @EventHandler
    public void onEntityCombust(EntityCombustEvent event){
        if(event.getEntity() instanceof Zombie){
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        LivingEntity ent = (LivingEntity) event.getEntity();
        if (event.getDamager().getType() == EntityType.ZOMBIE) {
            ent.damage(12);
        }
        
        if (event.getDamager().getType() == EntityType.SKELETON) {
            ent.damage(23);
        }
    }
    
    //on click item in inventory
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Champion champ = teams.getChampion((Player) event.getWhoClicked());
        if (event.getSlot() < 4) {
            if (champ.points > 0) {
                switch (event.getSlot()) {
                    case 0: champ.levelQ();
                        break;
                    case 1: champ.levelW();
                        break;
                    case 2: champ.levelE();
                        break;
                    case 3: champ.levelR();
                        break;
                }
            }
            else {
                champ.player.sendMessage(ChatColor.RED + "You do not have any ability points! Level up for more points!");
            }
            event.setCancelled(true);
        }
        
        else if (event.getSlot() != 6 || event.getSlot() != 7 || event.getSlot() != 33 || event.getSlot() != 34 || event.getSlot() != 23 || event.getSlot() != 24) {
            event.setCancelled(true);
            champ.player.sendMessage(ChatColor.RED + "You cannot modify those slots!");
        }
    }
    
    //prevent items from breaking/
    /*
    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent event){
        Player player = event.getPlayer();
        player.getInventory().getItem(event.getNewSlot()).setDurability(player.getInventory().getItem(event.getNewSlot()).getType().getMaxDurability());
    }
    */
    
    //prevent items from breaking
    @EventHandler
    public void onItemBreak(PlayerItemBreakEvent event){
        event.getBrokenItem().setDurability(event.getBrokenItem().getType().getMaxDurability());
    }
    
    
    
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        double respawnTime = 5;
        int totalRespawnTime = 5;
        teams.getChampion(player).storeInventory();
        event.setKeepLevel(true);
        event.setKeepInventory(true);
        respawnTime += teams.getChampion(event.getEntity()).getLevel() * 2.5;
        if (arena.clock > 20 * 60) {
            respawnTime = respawnTime + ((respawnTime / 50) * ((arena.clock / 60) - 20));
            totalRespawnTime = (int) respawnTime;
        }
        else {
            totalRespawnTime = (int) respawnTime;
        }
        
        teams.getChampion(event.getEntity()).setRespawnTime(totalRespawnTime);
        //begin respawn timer
    }
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            ArrayList<Player> players = new ArrayList<Player>();
            for (Entity entity : event.getEntity().getNearbyEntities(12, 10, 12)) {
                if (entity instanceof Player) {
                    if (!teams.getTeam((Player) entity).equals(teams.getTeam(event.getEntity()))) {
                        players.add((Player) entity);
                    }
                }
            }
            for (Player player : players) {
                teams.getChampion(player).addXp(50 / players.size());
            }
        }
    }
    
    
    
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (arena.started) {
            //prevent sprinting
            if (event.getPlayer().isSprinting()) {
                event.getPlayer().setSprinting(false);
            }
            
            //check if champion is respawning and prevent them from leaving spawn.
            if (teams.getChampion(event.getPlayer()).getRespawnTime() > 0) {
                
                if (teams.getTeam(event.getPlayer()).equals("blue")) {
                    if (event.getTo().distance(arena.blueSpawn) > plugin.getConfig().getDouble("spawn-radius")) {
                            event.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[DEAD] " + ChatColor.GOLD + "You must wait " + ChatColor.GREEN + teams.getChampion(event.getPlayer()).getRespawnTime() + ChatColor.GOLD + " to exit Spawn! Go buy items!");
                            event.setTo(event.getFrom());
                    }
                }
                if (teams.getTeam(event.getPlayer()).equals("purple")) {
                    if (event.getTo().distance(arena.purpleSpawn) > plugin.getConfig().getDouble("spawn-radius")) {
                            event.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[DEAD] " + ChatColor.GOLD + "You must wait " + ChatColor.GREEN + teams.getChampion(event.getPlayer()).getRespawnTime() + ChatColor.GOLD + " to exit Spawn! Go buy items!");
                            event.setTo(event.getFrom());
                    }
                }
            }
            
            
            //prevent motion while recalling
            if (teams.getChampion(event.getPlayer()).getRecalling()) {
                if (event.getFrom().distance(event.getTo()) > 2) {
                //if ((event.getFrom().getX() - event.getTo().getX()) || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                    teams.getChampion(event.getPlayer()).setRecalling(false, false);
                    //event.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[WARNING]: " + ChatColor.GOLD + " Recalling Interrupted! Don't move at all to successfully recall");
                }
                else if (event.getFrom().distance(event.getTo()) > .02) {
                //if ((event.getFrom().getX() - event.getTo().getX()) || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                    teams.getChampion(event.getPlayer()).setRecalling(false, true);
                    //event.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[WARNING]: " + ChatColor.GOLD + " Recalling Interrupted! Don't move at all to successfully recall");
                }
                
            }
        }
    }
    
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (arena.started) {
            if (teams.getTeam(event.getPlayer()).equals("blue")) {
                event.setRespawnLocation(arena.blueSpawn);
            }
            else if (teams.getTeam(event.getPlayer()).equals("purple")) {
                event.setRespawnLocation(arena.purpleSpawn);
            }
            teams.getChampion(event.getPlayer()).returnInventory();
        }
        else {
            if (teams.getTeam(event.getPlayer()).equals("blue")) {
                event.setRespawnLocation(teams.blueLobby);
            }
            else if (teams.getTeam(event.getPlayer()).equals("purple")) {
                event.setRespawnLocation(teams.purpleLobby);
            }
        }
        
    }
    //########################################
    //METHODS USED IN onInteract()
    
    
    public void castSpell(Champion champ, PlayerInteractEvent event) {
            switch (player.getInventory().getHeldItemSlot()) {
                case 0: //Q
                    if (champ.qCooldown <= 0) {
                        champ.qSpell();
                    }
                    else {
                        champ.sendMessage(ChatColor.RED + "[SPELL]: " + ChatColor.GOLD + "You must wait " + ChatColor.GREEN + champ.qCooldown + ChatColor.GOLD + " seconds before you can cast this.");
                        event.setCancelled(true);
                    }
                    break;
                case 1: //W
                    if (champ.wCooldown <= 0) {
                        champ.wSpell();
                    }
                    else {
                        champ.sendMessage(ChatColor.RED + "[SPELL]: " + ChatColor.GOLD + "You must wait " + ChatColor.GREEN + champ.wCooldown + ChatColor.GOLD + " seconds before you can cast this.");
                        event.setCancelled(true);
                    }
                    break;
                case 2: //E
                    if (champ.eCooldown <= 0) {
                        champ.eSpell();
                    }
                    else {
                        champ.sendMessage(ChatColor.RED + "[SPELL]: " + ChatColor.GOLD + "You must wait " + ChatColor.GREEN + champ.eCooldown + ChatColor.GOLD + " seconds before you can cast this.");
                        event.setCancelled(true);
                    }
                    break;
                case 3: //R
                    if (champ.rCooldown <= 0) {
                        champ.rSpell();
                    }
                    else {
                        champ.sendMessage(ChatColor.RED + "[SPELL]: " + ChatColor.GOLD + "You must wait " + ChatColor.GREEN + champ.rCooldown + ChatColor.GOLD + " seconds before you can cast this.");
                        event.setCancelled(true);
                    }
                    break;
                case 4: //D
                    if (champ.qCooldown <= 0) {
                        champ.qSpell();
                    }
                    else {
                        champ.sendMessage(ChatColor.RED + "[SPELL]: " + ChatColor.GOLD + "You must wait " + ChatColor.GREEN + champ.qCooldown + ChatColor.GOLD + " seconds before you can cast this.");
                        event.setCancelled(true);
                    }
                    break;
                case 5: //F
                    if (champ.qCooldown <= 0) {
                        champ.qSpell();
                    }
                    else {
                        champ.sendMessage(ChatColor.RED + "[SPELL]: " + ChatColor.GOLD + "You must wait " + ChatColor.GREEN + champ.qCooldown + ChatColor.GOLD + " seconds before you can cast this.");
                        event.setCancelled(true);
                    }
                    break;
                case 6: //active item
                    
                    break;
                case 7: //active item
                    
                    break;
                case 8: //recall
                    arena.recall(champ.player);
                    break;
            }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //##########################################
    @EventHandler
    public boolean onInteract(PlayerInteractEvent event) {
        
        player = event.getPlayer();
        Champion champion = teams.getChampion(player);
        
        //_________
        //RIGHT CLICK ACTIONS
        
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
        //BLUE switcher clicked
            if (event.getClickedBlock().getLocation().getX() == blueSwitcher.getX() && event.getClickedBlock().getLocation().getY() == blueSwitcher.getY() && event.getClickedBlock().getLocation().getZ() == blueSwitcher.getZ()) {
                if (teams.getPurpleQueue().contains(player)) {
                    teams.removePurpleQueue(player);
                    player.sendMessage("You are no longer on the Join-Purple Queue!");
                }
                else {
                    if (teams.getTeamSize("purple") < 5) {
                        if (teams.blueChampions[teams.blueTeam.lastIndexOf(player)] != null) {
                                tempChampion = champion;
                                teams.removeChampion(player);
                                teams.removePlayer(player);
                                teams.addPurple(player);
                                teams.setChampion(player, tempChampion);
                            }
                            //dont't set champion because no champ selected
                            else {
                                teams.removePlayer(player);
                                teams.addPurple(player);
                            }
                            
                        if (!teams.getBlueQueue().isEmpty()) {
                            //set champions
                            if (teams.purpleChampions[teams.purpleTeam.lastIndexOf(teams.getBlueQueue(0))] != null) {
                                tempChampion = teams.getChampion(teams.getBlueQueue(0));
                                teams.removeChampion(teams.getBlueQueue(0));
                                teams.getBlueQueue(0).sendMessage("You have been taken off the Queue and moved to BLUE!");
                                teams.addBlue(teams.removeBlueQueue());
                                teams.setChampion(teams.getBlueQueue(0), tempChampion);
                            }
                            //dont't set champion because no champ selected
                            else {
                                teams.getBlueQueue(0).sendMessage("You have been taken off the Queue and moved to BLUE!");
                                teams.addBlue(teams.removeBlueQueue());
                            }
                            
                        }
                    }
                    else {
                        player.sendMessage("The PURPLE team is full! You have been added to the switch queue.");
                    }
                }
            }

            //purple switcher clicked
            if (event.getClickedBlock().getLocation().getX() == purpleSwitcher.getX() && event.getClickedBlock().getLocation().getY() == purpleSwitcher.getY() && event.getClickedBlock().getLocation().getZ() == purpleSwitcher.getZ()) {
                if (teams.getPurpleQueue().contains(player)) {
                    teams.removePurpleQueue(player);
                    player.sendMessage("You are no longer on the Join-Purple Queue!");
                }
                else {
                    if (teams.getTeamSize("blue") < 5) {
                        if (teams.purpleChampions[teams.purpleTeam.lastIndexOf(player)] != null) {
                                tempChampion = champion;
                                teams.removeChampion(player);
                                teams.removePlayer(player);
                                teams.addBlue(player);
                                teams.setChampion(player, tempChampion);
                            }
                            //dont't set champion because no champ selected
                            else {
                                teams.removePlayer(player);
                                teams.addBlue(player);
                            }
                            
                        if (!teams.getPurpleQueue().isEmpty()) {
                            //set champions
                            if (teams.blueChampions[teams.blueTeam.lastIndexOf(teams.getPurpleQueue(0))] != null) {
                                tempChampion = teams.getChampion(teams.getPurpleQueue(0));
                                teams.removeChampion(teams.getPurpleQueue(0));
                                teams.getPurpleQueue(0).sendMessage("You have been taken off the Queue and moved to PURPLE!");
                                teams.addPurple(teams.removePurpleQueue());
                                teams.setChampion(teams.getPurpleQueue(0), tempChampion);
                            }
                            //dont't set champion because no champ selected
                            else {
                                teams.getPurpleQueue(0).sendMessage("You have been taken off the Queue and moved to BLUE!");
                                teams.addPurple(teams.removePurpleQueue());
                            }
                        }
                    }
                    else {
                        player.sendMessage("The BLUE team is full! You have been added to the switch queue.");
                    }
                }
            }
            return true;
        }
        
        
        //_______
        //RIGHT CLICK AIR ACTIONS
        
        else if (event.getAction() == Action.RIGHT_CLICK_AIR && arena.started) {
            //activate ability/spell
            castSpell(champion, event);
            return true;
        }


        //_______
        //LEFT CLICK ACTIONS
        
        else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Turret turret = null;
            //boolean hitTurret = false;
            
            //--------------
            //check if tower is clicked
            if (arena.started && champion.basicAttack) {
                if (teams.getTeam(player).equals("blue")) {
                    turret = arena.isPurpleTurret(event.getClickedBlock().getLocation());
                    if (turret != null) {
                        if (champion != null) {
                            turret.hit(champion.getDamage());
                            tempLoc = event.getClickedBlock().getLocation().add(.5, 0, .5);
                            plugin.mainWorld.createExplosion(tempLoc, (float) 0.01, false);
                            //hitTurret = true;
                        }
                        player.sendMessage("YOU HIT THE TURRET");
                    }
                    if (arena.isBlueTurret(event.getClickedBlock().getLocation()) != null) {
                        player.sendMessage(ChatColor.GOLD + "That is a friendly turret! Attack " + ChatColor.LIGHT_PURPLE + "PURPLE " + ChatColor.GOLD + "towers!");
                    }
                }

                else if (teams.getTeam(player).equals("purple")) {
                    turret = arena.isBlueTurret(event.getClickedBlock().getLocation());
                    if (turret != null) {
                        if (champion != null) {
                            turret.hit(champion.getDamage());
                            tempLoc = event.getClickedBlock().getLocation().add(.5, 0, .5);
                            plugin.mainWorld.createExplosion(tempLoc, (float) 0.01, false);
                            //hitTurret = true;
                        }
                        player.sendMessage("YOU HIT THE TURRET");
                    }
                    if (arena.isPurpleTurret(event.getClickedBlock().getLocation()) != null) {
                        player.sendMessage(ChatColor.GOLD + "That is a friendly turret! Attack " + ChatColor.BLUE + "BLUE " + ChatColor.GOLD + "towers!");
                    }
                }

                //---------------
                else {
                    champion.basicAttack();
                    //regular attack
                }
            }
            else {
                event.setCancelled(true);
            }
        }
        
        else if (event.getAction() == Action.LEFT_CLICK_AIR && arena.started && champion.basicAttack) {
            champion.basicAttack();
            Entity ent = null;
            /*if (champion.championInstance.isMarksman()) {
                if (champion.getTeam().equals("blue")) {
                    BlockIterator iterator = new BlockIterator(player.getWorld(), player.getLocation().toVector(), player.getEyeLocation().getDirection(), 0, (int) champion.range + 1);
                    while (iterator.hasNext()) {
                        Block block = iterator.next();
                        Turret turret = arena.isPurpleTurret(block.getLocation());
                        if (turret != null) {
                            turret.hit(champion.getDamage());
                            tempLoc = event.getClickedBlock().getLocation().add(.5, 0, .5);
                            plugin.mainWorld.createExplosion(tempLoc, (float) 0.01, false);
                            player.sendMessage("YOU HIT THE TURRET");
                        }
                        if (arena.isBlueTurret(block.getLocation()) != null) {
                            player.sendMessage(ChatColor.GOLD + "That is a friendly turret! Attack " + ChatColor.LIGHT_PURPLE + "PURPLE " + ChatColor.GOLD + "towers!");
                        }
                        break;

                    } 
                }
                
                if (champion.getTeam().equals("purple")) {
                    BlockIterator iterator = new BlockIterator(player.getWorld(), player.getLocation().toVector(), player.getEyeLocation().getDirection(), 0, (int) champion.range + 1);
                    while (iterator.hasNext()) {
                        Block block = iterator.next();
                        Turret turret = arena.isBlueTurret(block.getLocation());
                        if (turret != null) {
                            turret.hit(champion.getDamage());
                            tempLoc = event.getClickedBlock().getLocation().add(.5, 0, .5);
                            plugin.mainWorld.createExplosion(tempLoc, (float) 0.01, false);
                            player.sendMessage("YOU HIT THE TURRET");
                        }
                        if (arena.isPurpleTurret(block.getLocation()) != null) {
                            player.sendMessage(ChatColor.GOLD + "That is a friendly turret! Attack " + ChatColor.BLUE + "BLUE " + ChatColor.GOLD + "towers!");
                        }
                        break;

                    } 
                }
                
                
            }*/
        }
        
        return false;
    }
}