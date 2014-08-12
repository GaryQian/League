package com.meloncraft.league.Arena.Minions;

import com.meloncraft.league.League;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 *
 * @author Gary
 */
public class MinionSpawnWaveTask extends BukkitRunnable{
    static League plugin;
    public static List<Entity> blueMinions, purpleMinions;
    
    public MinionSpawnWaveTask(League plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public void run() {
        BukkitTask spawnMinion1 = new MinionSpawnTask(this.plugin).runTask(this.plugin);
        BukkitTask spawnMinon2 = new MinionSpawnTask(this.plugin).runTaskLater(this.plugin, 20);
        BukkitTask spawnMinon3 = new MinionSpawnTask(this.plugin).runTaskLater(this.plugin, 40);
        BukkitTask spawnMinon4 = new MinionSpawnTask(this.plugin).runTaskLater(this.plugin, 60);
        BukkitTask spawnMinon5 = new MinionSpawnTask(this.plugin).runTaskLater(this.plugin, 80);
        BukkitTask spawnMinon6 = new MinionSpawnTask(this.plugin).runTaskLater(this.plugin, 100);
    }
}