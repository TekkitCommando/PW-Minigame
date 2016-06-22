package me.tekkitcommando.pw.handlers;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.google.common.collect.Lists;
import com.google.common.math.IntMath;

import me.tekkitcommando.pw.PirateWars;

public class GameHandler {
	
	public static PirateWars plugin = PirateWars.instance;
	public static ArrayList<Player> allPlayers = new ArrayList<Player>();
	public static Team red;
	public static Team blue;
	static int partitionSize = IntMath.divide(allPlayers.size(), 2, RoundingMode.UP);
	static List<List<Player>> partitions = Lists.partition(allPlayers, partitionSize);
	
	public static boolean canStart() {
		if(allPlayers.size() >= plugin.getConfig().getInt("minPlayers")) {
			return true;
		} else {
			return false;
		}
	}

	public static void startGame() {
		for(Player player : allPlayers) {
			createScoreboard();
			if(red.hasEntry(player.getName())) {
				player.teleport(new Location(Bukkit.getWorld("game"), 1, 1, 1));
				player.sendMessage(ChatColor.GREEN + "You have been teleported to the " + ChatColor.DARK_RED + "RED" 
						+ ChatColor.GREEN + " team spawn.");
			} else {
				player.teleport(new Location(Bukkit.getWorld("game"), 10, 10, 10));
				player.sendMessage(ChatColor.GREEN + "You have been teleported to the " + ChatColor.DARK_BLUE + "BLUE" 
						+ ChatColor.GREEN + " team spawn.");
			}
		}
	}
	
	public static void createScoreboard() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		red = board.registerNewTeam("red");
		blue = board.registerNewTeam("blue");
		for(Player player : partitions.get(1)) {
			red.addEntry(player.getName());
		}
		for(Player player : partitions.get(2)) {
			blue.addEntry(player.getName());
		}
	}
}