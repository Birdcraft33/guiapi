package com.bluebird.api.gui.components;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;

import com.bluebird.api.gui.Gui;
import com.bluebird.api.gui.events.component.StaticComponentClickedEvent;

public class StaticComponent extends Component{

	private ComponentMeta meta;
	
	public StaticComponent(ComponentMeta meta) {
		super(meta);
		this.meta = meta;
	}
	
	
	
	
	
	//<--------------------------------------------------/*NEEDED METHODS*/------------------------------------------------------>//
	
	private static final String TYPE = "staticComponent";
	
	@Override
	public Component setGuiComponent() {
		return this;
	}

	@Override
	public String setGuiComponentType() {
		return TYPE;
	}

	@Override
	public void componentClicked(HumanEntity whoClicked, Inventory clickedInventory, InventoryAction action, int slot, ClickType clickType) {
		Bukkit.getPluginManager().callEvent(new StaticComponentClickedEvent(this, whoClicked, Gui.getGuiFromInventory(clickedInventory), action, slot, clickType));
	}

}
