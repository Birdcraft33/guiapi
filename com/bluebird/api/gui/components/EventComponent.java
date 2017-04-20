package com.bluebird.api.gui.components;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;

import com.bluebird.api.gui.Gui;
import com.bluebird.api.gui.events.GuiEventComponentClickedEvent;

/**
 * <h2>general</h2>
 * This is a special component based on the class {@link Component}. If you will click this component it will fire the event {@link GuiEventComponentClickedEvent}.
 * 
 * <h2>infos</h2>
 * You can initialize an event component simple with the Constructor of this class. PLEASE DIDN'T EXTENDS THIS CLASS TO ONE OF YOUR CLASSES because it is
 * already a special Component
 * 
 * @author Birdcraft33
 * @category Component
 * @version 1.0
 * @since 1.0
 * @extends no
 * @initialize yes
 * @basedOn {@link Component}
 */
public class EventComponent extends Component{

	/**
	 * The GuiEventComponent is a component for a gui witch do only one thing. When this component is clicked it started the Event "GuiEventComponentClickedEvent". In this Event you can filter all components with the id.
	 * @param meta The Meta for this component
	 */
	public EventComponent(ComponentMeta meta) {
		super(meta);
	}

	
	
	
	
	
	
	//<------------------------------------------------------------------------/*GET_METHODS*/------------------------------------------------------------------------->//
	public static String getType() {
		return type;
	}
	
	
	//<------------------------------------------------------------------------/*NEEDED PATH*/------------------------------------------------------------------------->//
	
	private static final String type = "eventComponent";
	
	@Override
	public Component setGuiComponent() {
		return this;
	}

	@Override
	public String setGuiComponentType() {
		return type;
	}

	@Override
	public void componentClicked(HumanEntity whoClicked, Inventory clickedInventory, InventoryAction action, int slot, ClickType clickType) {
		System.out.println("EventComponent is clicked from player " + whoClicked.getName() + " in slot " + slot);
		Gui gui = Gui.getGuiFromInventory(clickedInventory);
		if(gui != null) {
			System.out.println("CallEvent");
			Bukkit.getPluginManager().callEvent(new GuiEventComponentClickedEvent(this, whoClicked, gui, action, slot, clickType));
		}
	}
	
	
}
