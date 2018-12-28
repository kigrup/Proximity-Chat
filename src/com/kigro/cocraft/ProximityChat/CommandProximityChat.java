package com.kigro.cocraft.ProximityChat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandProximityChat implements CommandExecutor{

	ProximityChat main = JavaPlugin.getPlugin(ProximityChat.class);
	FileConfiguration config = main.getConfig();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		// If new range is specified
		if (args.length == 1) {
			// Reload if specified
			if (args[0].contains("reload")){
				main.reloadPlugin();
				if (sender instanceof Player) {
					sender.sendMessage("ProximityChat reloaded");
				}
			}
			// Change range value
			else {
				try {
					int newRange = Integer.parseInt(args[0]);
					// Update config
					config.set("voice-range", newRange);
					main.saveConfig();
					
					
					// Message that the range has been changed
					if (sender instanceof Player) {
						sender.sendMessage("Messages will be sent up to " + args[0] + " blocks after reloading plugin");
					}
				}
				catch(Exception e){
					sender.sendMessage(e.getMessage());
					return false;
				}
			}
		}
		// If no arguments are given
		else {
			// Message the current range
						if (sender instanceof Player) {
							sender.sendMessage("Messages are currently sent up to " + String.valueOf(config.getInt("voice-range")) + " blocks");
						}
		}
		return true;
	}
}
