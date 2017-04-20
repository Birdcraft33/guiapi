package com.bluebird.api.gui.events.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.bluebird.api.gui.Gui;

public class GuiCloseEvent extends Event{

	private Gui closedGui;
	private Player whoClosed;
	
	
	public GuiCloseEvent(Gui closedGui, Player whoClosed) {
		this.closedGui = closedGui;
		this.whoClosed = whoClosed;
	}
	
	public Gui getClosedGui() {
		return closedGui;
	}
	
	public Player getWhoClosed() {
		return whoClosed;
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
