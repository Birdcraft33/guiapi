package com.bluebird.api.gui.events.component;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

import com.bluebird.api.gui.Gui;
import com.bluebird.api.gui.components.YesNoSettings;

public class YesNoSettingClickedEvent extends Event{

	private YesNoSettings clickedYesNoSetting;
	private HumanEntity whoClicked;
	private Gui clickedGui;
	private InventoryAction action;
	private int slot;
	private ClickType clickType;

	public YesNoSettingClickedEvent(YesNoSettings clickedYesNoSetting, HumanEntity whoClicked, Gui clickedGui, InventoryAction action, int slot, ClickType clickType) {
		
		this.clickedYesNoSetting = clickedYesNoSetting;
		this.whoClicked = whoClicked;
		this.clickedGui = clickedGui;
		this.action = action;
		this.slot = slot;
		this.clickType = clickType;
		
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
	
	
	
	
	//<---------------------------------------------------/*NEEDED METHODS*/------------------------------------------------------->//
	
	public static HandlerList handlerList = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public static HandlerList getHandlerList() {
		return handlerList;
	}
	
}
