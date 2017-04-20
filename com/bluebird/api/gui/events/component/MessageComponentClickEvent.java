package com.bluebird.api.gui.events.component;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

import com.bluebird.api.gui.Gui;
import com.bluebird.api.gui.components.EventComponent;
import com.bluebird.api.gui.components.MessageComponent;

public class MessageComponentClickEvent extends Event{

	
	
	private MessageComponent clickedComponent;
	private HumanEntity whoClicked;
	private Gui gui;
	private InventoryAction action;
	private int slot;
	private ClickType clickType;
	
	
	public MessageComponentClickEvent(MessageComponent clickedComponent, HumanEntity whoClicked, Gui gui, InventoryAction action, int slot, ClickType clickType) {
		
		this.clickedComponent = clickedComponent;
		this.whoClicked = whoClicked;
		this.gui = gui;
		this.action = action;
		this.slot = slot;
		this.clickType = clickType;
		
	}
	
	
	
	public MessageComponent getClickedComponent() {
		return this.clickedComponent;
	}
	
	public HumanEntity getWhoClicked() {
		return this.whoClicked;
	}
	
	public Gui getGui() {
		return this.gui;
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
	
	
	
	//<--------------------------------------------------------------------/*NEEDED PATH*/------------------------------------------------------------->//
	
	public static HandlerList handlerList = new HandlerList();
	
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public static HandlerList getHandlerList() {
		return handlerList;
	}

}
