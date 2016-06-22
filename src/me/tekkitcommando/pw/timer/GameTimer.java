package me.tekkitcommando.pw.timer;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import me.tekkitcommando.pw.PirateWars;
import me.tekkitcommando.pw.handlers.GameHandler;
import net.md_5.bungee.api.ChatColor;

public class GameTimer extends BukkitRunnable {

    PirateWars plugin;

    public GameTimer(PirateWars game) {
        plugin = game;
    }

    public static int gameTimer;

    public void run() {

        if (gameTimer == 0) {
        	if(!GameHandler.canStart()) {
        		Bukkit.broadcastMessage(ChatColor.RED +  "[Pirate Wars] Not enough players to start the game.");
        		Bukkit.broadcastMessage(ChatColor.RED + "[Pirate Wars] Restarting game timer...");
        		plugin.restartTimer();
        		return;
        	}
        	GameHandler.startGame();
        }

        if (gameTimer % 10 == 0 || gameTimer < 10) {
            Bukkit.getServer().broadcastMessage("Game starts in " + String.valueOf(gameTimer) + " seconds!");
        }
        gameTimer -= 1;
    }

}
