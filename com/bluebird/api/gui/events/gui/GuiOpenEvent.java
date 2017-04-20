package com.bluebird.api.gui.events.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.bluebird.api.gui.Gui;

public class GuiOpenEvent extends Event{

	
	private Gui openedGui;
	private Player whoLooked;
	
	
	public GuiOpenEvent(Gui openedGui, Player whoLooked) {
		this.openedGui = openedGui;
		this.whoLooked = whoLooked;
	}
	
	public Gui getOpenedGui() {
		return openedGui;
	}
	
	public Player getWhoLooked() {
		return whoLooked;
	}
	
	
	
	//<--------------------------------------------------------NEEDED METHODS----------------------------------------------------->//
	
	public static HandlerList handlerList = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}

	
	public static HandlerList getHandlerList() {
		return handlerList;
	}
	
}
