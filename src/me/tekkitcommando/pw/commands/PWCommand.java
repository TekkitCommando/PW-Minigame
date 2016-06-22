package me.tekkitcommando.pw.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tekkitcommando.pw.handlers.GameHandler;

public class PWCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("pw")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.GREEN + "Please type /pw join to join the game");
				return true;
			} else if(args.length > 0 && args[0] == "join") {
				if(GameHandler.canJoin() == true) {
					if(sender instanceof Player) {
						Player player = (Player) sender;
						GameHandler.addPlayer(player);
						GameHandler.tpToLobby(player);
						player.sendMessage(ChatColor.GREEN + "Added you to the game!");
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Only a player can do that command!");
					}
				}
			} else if(args.length > 0 && args[0] == "leave") {
				if(sender instanceof Player) {
					Player player = (Player) sender;
					GameHandler.removePlayer(player);
					player.sendMessage(ChatColor.GREEN + "You have left the game!");
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "Only a player can do that command!");
				}
			}
		}
		return true;
	}
}
