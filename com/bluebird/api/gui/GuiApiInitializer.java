package com.bluebird.api.gui;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.bluebird.api.gui.components.ComponentEvents;
import com.bluebird.api.gui.components.YesNoOption;
import com.bluebird.api.gui.components.YesNoSettings;
import com.bluebird.api.gui.events.call.GuiNoOptionClickCall;
import com.bluebird.api.gui.events.call.GuiYesOptionClickCall;
import com.bluebird.api.gui.events.call.YesNoSettingChooseCall;


/**
 * <h2>Infos</h2>
 * This class isn't for use outside the GuiAPI. In this class the guiAPI will be only inizialized. PLEASE DIDN'T USE THIS CLASS
 * @author Birdcraft33
 * @version 1.0
 * @since 1.0
 * @category intern management class of guiapi
 */
@Deprecated
public class GuiApiInitializer extends JavaPlugin{

	
	public static String prefix = "[GuiAPI] ";
	
	
	
	@Override
	public void onEnable() {
		registerEvents();
		
		Bukkit.broadcastMessage(prefix + "§4The GuiApi is initialized");
	}
	
	@Override
	public void onDisable() {
		Bukkit.broadcastMessage(prefix + "§4The GuiApi is down");
	}
	
	
	@SuppressWarnings("deprecation")
	private void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new ComponentEvents(), this);
		
		this.getServer().getPluginManager().registerEvents(new GuiEvents(), this);
		
		
		this.getServer().getPluginManager().registerEvents(new GuiYesOptionClickCall(), this);
		this.getServer().getPluginManager().registerEvents(new GuiNoOptionClickCall(), this);
		this.getServer().getPluginManager().registerEvents(new YesNoSettingChooseCall(), this);
	}
	
	

	
	
}
