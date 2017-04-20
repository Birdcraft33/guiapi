package com.bluebird.api.gui.components;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.bluebird.api.gui.Gui;
import com.bluebird.api.gui.events.component.NoSettingChooseEvent;
import com.bluebird.api.gui.events.component.SettingsClickEvent;
import com.bluebird.api.gui.events.component.YesSettingChooseEvent;

public class YesNoSettings implements Listener{

	
	private Settings settings;
	private String option1String = "Yes";
	private String option2String = "No";
	private SettingOption[] options;
	private ItemStack option1Item = new ItemStack(351, 1, (short)0, (byte)10);
	private ItemStack option2Item = new ItemStack(351, 1, (short)0, (byte)8);
	private boolean isYes = true;
	private boolean isNo = false;
	
	protected static ArrayList<YesNoSettings> yesNoSettings = new ArrayList<>();
	
	
	public YesNoSettings() {
		
		options = new SettingOption[] {
				new SettingOption(new ComponentMeta(option1String, option1Item)), 
				new SettingOption(new ComponentMeta(option2String, option2Item))
		};
		
		settings = new Settings(options);
		settings.setFinalizedAction(finalizedAction);
		yesNoSettings.add(this);
	}

		
	public YesNoSettings(String option1String, String option2String, ItemStack option1Item, ItemStack option2Item) {
		
		this.option1String = option1String;
		this.option2String = option2String;
		this.option1Item = option1Item;
		this.option2Item = option2Item;
		
		options = new SettingOption[] {
			new SettingOption(new ComponentMeta(option1String, option1Item)), 
			new SettingOption(new ComponentMeta(option2String, option2Item))
		};
		
		settings = new Settings(options);
		settings.setFinalizedAction(finalizedAction);
		yesNoSettings.add(this);
	}
	
	
	
	public Settings getComponent() {
		return settings;
	}
	
	public SettingOption getYesOption(){
		return options[0];
	}
	public SettingOption getNoOption() {
		return options[1];
	}
	public SettingOption[] getOptions() {
		return options;
	}
	
	public void setIsYes(boolean isYes) {
		this.isYes = isYes;
	}
	public void setIsNo(boolean isNo) {
		this.isNo = isNo;
	}
	
	public boolean isNo() {
		return isNo;
	}
	public boolean isYes() {
		return isYes;
	}
	
	
	public void setYes(Gui gui, Player player) {
		this.settings.setOption(0, gui, player);
	}
	
	public void setNo(Gui gui, Player player) {
		this.settings.setOption(1, gui, player);
	}
	
	//<----------------------------------------------------/*EXTRA TO FINALIZE*/----------------------------------------------------->//
	
	
	Runnable finalizedAction = new Runnable() {
		
		@Override
		public void run() {
			removeYesNoSetting();
		}
	};
	
	private void removeYesNoSetting() {
		yesNoSettings.remove(this);
	}
	
	
	//<------------------------------------------------------/*STATIC METHODS*/------------------------------------------------------->//
	public static ArrayList<YesNoSettings> getYesNoSettingsList() {
		return yesNoSettings;
	}
	
}
