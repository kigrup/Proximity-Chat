package com.kigro.cocraft.ProximityChat;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ProximityChat extends JavaPlugin{
	
	PluginManager pluginManager = Bukkit.getServer().getPluginManager();
	public FileConfiguration config = getConfig();
	
	@Override
	public void onEnable() {
		
		// Config variables
		config.addDefault("voice-range", 40);
		
		// Save config
		config.options().copyDefaults(true);
		saveConfig();
		
		// Register commands
		this.getCommand("proximitychat").setExecutor(new CommandProximityChat());
		
		// Register event listener
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		
	}
	
	public void reloadPlugin() {
		pluginManager.disablePlugin(this);
		pluginManager.enablePlugin(this);
	}
}