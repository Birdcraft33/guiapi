package com.bluebird.api.gui.events.component;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

import com.bluebird.api.gui.Gui;
import com.bluebird.api.gui.components.SettingOption;
import com.bluebird.api.gui.components.Settings;

public class SettingsClickEvent extends Event{

	
	private Settings clickedSettings;
	private HumanEntity whoClicked;
	private Gui clickedGui;
	private InventoryAction action;
	private int slot;
	private ClickType clickType;
	private SettingOption clickedOption;
	private SettingOption newSetting;

	public SettingsClickEvent(Settings clickedSettings, SettingOption clickedOption, SettingOption newSetting, HumanEntity whoClicked, Gui clickedGui, InventoryAction action, int slot, ClickType clickType) {
		
		this.clickedSettings = clickedSettings;
		this.whoClicked = whoClicked;
		this.clickedGui = clickedGui;
		this.action = action;
		this.slot = slot;
		this.clickType = clickType;
		this.clickedOption = clickedOption;
		this.newSetting = newSetting;
		
	}
	
	public Settings getClickedSettings() {
		return clickedSettings;
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
	public SettingOption getNewSetting() {
		return newSetting;
	}
	
	
	
	//<-------------------------------------------------------------/*NEEDED METHODS*/--------------------------------------------------->//
	
	public static HandlerList handlerList = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public static HandlerList getHandlerList() {
		return handlerList;
	}
	
}
