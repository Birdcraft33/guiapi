package com.bluebird.api.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * A class for all Events of a gui witch needs an EventHandler of Bukkit
 * @author Birdcraft33
 *
 */
class GuiEvents implements Listener{

	
	/*CLOSE GUI*/
	@EventHandler
	public void onCloseGui(InventoryCloseEvent event) {
		if(Gui.inventoryIsGui(event.getInventory())) {
			/*->INVENTORY IS GUI<-*/
			/*VARIABLES*/
			Gui gui = Gui.getGuiFromInventory(event.getInventory());
			
			gui.finalizeGui();
		}
	}
	
	
}
