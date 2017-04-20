package com.bluebird.api.gui.components;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.bluebird.api.gui.events.GuiComponentClickedEvent;


/**
 * <h2>general</h2>
 * This class is only for starting an event of an Component and start the method in the Component class
 * 
 * <h2>infos</h2>
 * This class is only for the GuiAPI and not for outside plugins. PLEASE DIDN'T DO ANYTHING WITH THIS CLASS(didn't create an instance, start a method or extends this class)
 *  
 * @author Birdcraft33
 * @category ComponentEvents
 * @version 1.0
 * @since 1.0
 * @extends no
 * @initialize no
 */
@Deprecated
public class ComponentEvents implements Listener{

	
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(clickIsRight(event)) {
			//VARIABLES//
			Component component = Component.getComponentFromItemStack(event.getCurrentItem(), event.getSlot());	System.out.println("onClick Component: " + component.getGuiComponentMeta().getName());
			
			/*CALL EVENT*/
							
			/*VARIABLES*/
			//PLAYER//
			HumanEntity humanEntity = event.getWhoClicked();
			//GET_GUI//
			Inventory inventory = event.getClickedInventory();
			//GET_ACTION//
			InventoryAction action = event.getAction();
			//GET_SLOT//
			int slot = event.getSlot();
			//GET_CLICK_TYPE//
			ClickType clickType = event.getClick();
							
							
			component.componentClicked(humanEntity, inventory, action, slot, clickType);
			component.startClickAction();
							
			Bukkit.getPluginManager().callEvent(new GuiComponentClickedEvent(humanEntity, inventory, action, slot, clickType, component));
							
			event.setCancelled(true);
							
			System.out.println("onClickComponent");
		}
	}
	
	
	private static boolean clickIsRight(InventoryClickEvent event) {
		if(event.getCurrentItem() != null) {
			if(event.getSlot() == event.getRawSlot()) {
				if(event.getCurrentItem().hasItemMeta()) {
					if(Component.itemStackIsGuiComponent(event.getCurrentItem())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
}
