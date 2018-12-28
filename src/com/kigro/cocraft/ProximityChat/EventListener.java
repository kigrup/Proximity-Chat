package com.kigro.cocraft.ProximityChat;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EventListener implements Listener {

	ProximityChat main = JavaPlugin.getPlugin(ProximityChat.class);
	double voiceRange = main.getConfig().getInt("voice-range");
	char yellChar = '!';
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		
		// If message isn't yelling
		if (event.getMessage().charAt(0) != yellChar) {
			
			// New Set
			Set<Player> newRecipients = new HashSet<Player>();
			
			// Add players within the range to new array
			for (Player p : main.getServer().getOnlinePlayers()) {
				if (event.getPlayer().getLocation().distance(p.getLocation()) < voiceRange) {
					
					newRecipients.add(p);
				}
			}
			
			// Apply changes
			event.getRecipients().clear();
			event.getRecipients().addAll(newRecipients);
		}
	}
}
