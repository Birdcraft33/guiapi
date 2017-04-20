package com.bluebird.api.gui.events.call;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.bluebird.api.gui.components.YesNoSettings;
import com.bluebird.api.gui.events.component.NoSettingChooseEvent;
import com.bluebird.api.gui.events.component.SettingsClickEvent;
import com.bluebird.api.gui.events.component.YesNoSettingClickedEvent;
import com.bluebird.api.gui.events.component.YesSettingChooseEvent;

@Deprecated
public class YesNoSettingChooseCall implements Listener{

	
	@EventHandler
	public void onYesNoSettingClick(SettingsClickEvent event) {
		for(YesNoSettings setting: YesNoSettings.getYesNoSettingsList()) {
			
			Bukkit.getPluginManager().callEvent(new YesNoSettingClickedEvent(setting, event.getWhoClicked(), event.getClickedGui(), 
					event.getAction(), event.getSlot(), event.getClickType()));
			
			if(event.getClickedOption().equals(setting.getYesOption())) {
				/*OPTIONS_NO*/
				setting.setIsNo(true);
				setting.setIsYes(false);
				
				Bukkit.getPluginManager().callEvent(new YesSettingChooseEvent(setting, event.getClickedOption(), 
						event.getWhoClicked(), event.getClickedGui(), event.getAction(), event.getSlot(), event.getClickType()));
				
			}
			if(event.getClickedOption().equals(setting.getNoOption())) {
				/*OPTION_YES*/
				setting.setIsNo(false);
				setting.setIsYes(true);
				
				Bukkit.getPluginManager().callEvent(new NoSettingChooseEvent(setting, event.getClickedOption(), 
						event.getWhoClicked(), event.getClickedGui(), event.getAction(), event.getSlot(), event.getClickType()));
				
			}
		}
	}
	
	
}
