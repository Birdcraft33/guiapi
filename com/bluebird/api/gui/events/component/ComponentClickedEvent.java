package com.bluebird.api.gui.events.component;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;

import com.bluebird.api.gui.components.Component;

public class ComponentClickedEvent extends Event{

	
	private HumanEntity whoClicked;
	private Inventory inventory;
	private InventoryAction action;
	private int slot;
	private ClickType clickType;
	private Component component;
	
	
	public ComponentClickedEvent(HumanEntity whoClicked, Inventory inventory, InventoryAction action, int slot, ClickType clickType, Component component) {
		this.whoClicked = whoClicked;
		this.inventory = inventory;
		this.action = action;
		this.slot = slot;
		this.clickType = clickType;
		this.component = component;
	}
	
	
	public HumanEntity getWhoClicked() {
		return this.whoClicked;
	}
	
	public Inventory getClickedInventory() {
		return this.inventory;
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
	
	public Component getGuiComponent() {
		return this.component;
	}
	
	
	
	
	
	//<--------------------------------------------------------------------------------/*NEEDED PATH*/-------------------------------------------------------------->//
	
	public static HandlerList handlerList = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public static HandlerList getHandlerList() {
		return handlerList;
	}

}
