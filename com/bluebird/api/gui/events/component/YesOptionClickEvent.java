package com.bluebird.api.gui.events.component;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.bluebird.api.gui.Gui;
import com.bluebird.api.gui.components.Component;
import com.bluebird.api.gui.components.EventComponent;
import com.bluebird.api.gui.components.YesNoOption;

public class YesOptionClickEvent extends Event{

	
	private Component component;
	private EventComponent yesOptionComponent;
	private Gui gui;
	private HumanEntity whoClicked;
	private InventoryAction action;
	private int slot;
	private ClickType clickType;
	
	
	public YesOptionClickEvent(YesNoOption component, EventComponent yesOptionComponent, Gui gui, HumanEntity whoClicked, InventoryAction action, int slot, ClickType clickType) {
		
		this.component = component;
		this.yesOptionComponent = yesOptionComponent;
		this.gui = gui;
		this.whoClicked = whoClicked;
		this.action = action;
		this.slot = slot;
		this.clickType = clickType;
		
	}
	
	
	public Component getComponent() {
		return this.component;
	}
	
	public EventComponent getYesOptionComponent() {
		return this.yesOptionComponent;
	}
	
	public Gui getGui() {
		return this.gui;
	}
	
	public HumanEntity getWhoClicked() {
		return this.whoClicked;
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
	
	
	
	
	
	
	
	
	
	
	
	//<-------------------------------------------------------------------------/*NEEDED PATH*/-------------------------------------------------------------->//
	
	public static HandlerList handlerList = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public static HandlerList getHandlerList() {
		return handlerList;
	}

}
