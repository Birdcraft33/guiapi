package com.bluebird.api.gui.events.component;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

import com.bluebird.api.gui.Gui;
import com.bluebird.api.gui.components.SettingOption;
import com.bluebird.api.gui.components.Settings;
import com.bluebird.api.gui.components.YesNoSettings;

public class NoSettingChooseEvent extends Event{

	
	private YesNoSettings clickedYesNoSetting;
	private HumanEntity whoClicked;
	private Gui clickedGui;
	private InventoryAction action;
	private int slot;
	private ClickType clickType;
	private SettingOption clickedOption;

	public NoSettingChooseEvent(YesNoSettings clickedYesNoSetting, SettingOption clickedOption, HumanEntity whoClicked, Gui clickedGui, InventoryAction action, int slot, ClickType clickType) {
		
		this.clickedYesNoSetting = clickedYesNoSetting;
		this.whoClicked = whoClicked;
		this.clickedGui = clickedGui;
		this.action = action;
		this.slot = slot;
		this.clickType = clickType;
		this.clickedOption = clickedOption;
		
	}
	
	public YesNoSettings getClickedYesNoSettings() {
		return clickedYesNoSetting;
	}
	public HumanEntity getWhoClicked() {
		return whoClicked;
	}
	public Gui getClickedGui() {
		return clickedGui;
	}
	public InventoryAction getAction() {
		return action;
	}
	public int getSlot() {
		return slot;
	}
	public ClickType getClickType() {
		return clickType;
	}
	public SettingOption getClickedOption() {
		return clickedOption;
	}	
	//<-------------------------------------------/*NEEDED METHODS*/--------------------------------------------------------------->//
	
	public static HandlerList handerList = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return handerList;
	}
	
	public static HandlerList getHandlerList() {
		return handerList;
	}
}
