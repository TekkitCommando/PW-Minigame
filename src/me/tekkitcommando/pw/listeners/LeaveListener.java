package me.tekkitcommando.pw.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.tekkitcommando.pw.handlers.GameHandler;

public class LeaveListener implements Listener {
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if(GameHandler.allPlayers.contains(player)) {
			GameHandler.removePlayer(player);
			player.sendMessage(ChatColor.GREEN + "Goodbye!");
		}
	}

}
