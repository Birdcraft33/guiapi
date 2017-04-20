package com.bluebird.api.gui.events.component;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;

import com.bluebird.api.gui.Gui;
import com.bluebird.api.gui.components.Component;
import com.bluebird.api.gui.components.StaticComponent;

public class StaticComponentClickedEvent extends Event{

	
	private HumanEntity whoClicked;
	private Gui clickedGui;
	private InventoryAction action;
	private int slot;
	private ClickType clickType;
	private StaticComponent clickedStaticComponent;
	
	
	public StaticComponentClickedEvent(StaticComponent clickedStaticComponent, HumanEntity whoClicked, Gui clickedGui, InventoryAction action, int slot, ClickType clickType) {
		this.whoClicked = whoClicked;
		this.clickedGui = clickedGui;
		this.action = action;
		this.slot = slot;
		this.clickType = clickType;
		this.clickedStaticComponent = clickedStaticComponent;
	}
	
	
	public HumanEntity getWhoClicked() {
		return this.whoClicked;
	}
	
	public Gui getClickedGui() {
		return this.clickedGui;
	}
	
	public InventoryAction getAction() {
		return this.action;
	}
	
	public int getSlot() {
		return this.slot;
	}
	
	public ClickType getClickType() {
		return this.clickType;
	}
	
	public StaticComponent getClickedStaticComponent() {
		return clickedStaticComponent;
	}
	
	
	
	
	//<-------------------------------------------------------------/*NEEDED METHODS*/------------------------------------------------------------->//
	
	public static HandlerList handlerList = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public static HandlerList getHandlerList() {
		return handlerList;
	}

}
