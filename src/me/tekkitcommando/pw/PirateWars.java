/**
 * When I push to Github sometimes it is an ass
 * and says invalid-email-address as my username
 * so just know that that account is me as well.
 */

package me.tekkitcommando.pw;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.tekkitcommando.pw.commands.PWCommand;
import me.tekkitcommando.pw.listeners.JoinListener;
import me.tekkitcommando.pw.listeners.LeaveListener;
import me.tekkitcommando.pw.stages.GameState;
import me.tekkitcommando.pw.timer.GameTimer;

public class PirateWars extends JavaPlugin {
	
	public static int gameTimerId;
	public static PirateWars instance;
	
	public void onEnable() {
		Bukkit.getServer().getLogger().info("Starting game timer...");
		startGameTimer();
		Bukkit.getServer().getLogger().info("Game timer started!");
		GameState.setState(GameState.LOBBY_STATE);
		Bukkit.getServer().getLogger().info("Set GameState to Lobby State");
		Bukkit.getServer().getLogger().info("Registering commands...");
		initCommands();
		Bukkit.getServer().getLogger().info("Commands registered!");
		Bukkit.getServer().getLogger().info("Registering event handlers...");
		registerEvents();
		Bukkit.getServer().getLogger().info("Event handlers registered!");
	}
	
	public void onDisable() {
		
	}
	
	public void initCommands() {
		getCommand("pw").setExecutor(new PWCommand());
	}
	
	public void registerEvents() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new LeaveListener(), this);
	}
	
    @SuppressWarnings("deprecation")
	public void startGameTimer() {
        GameTimer.gameTimer = 30;
        gameTimerId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new GameTimer(this), 20l, 20l);
    }

    public void stopGameTimer() {
        getServer().getScheduler().cancelTask(gameTimerId);
    }

    public void restartTimer() {
        stopGameTimer();
        startGameTimer();
    }
}
