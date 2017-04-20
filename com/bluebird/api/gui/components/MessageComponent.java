package com.bluebird.api.gui.components;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;

import com.bluebird.api.gui.Gui;
import com.bluebird.api.gui.events.GuiMessageComponentClickEvent;

/**
 * <h2>general</h2>
 * The MessageComponent is based on {@link Component}. If you click on this Component it will send this Player a message
 * 
 * <h2>technical background</h2>
 * The MessageComponent automatic close the {@link Gui} if somebody choose the messageComponent. More informations about {@link Component}s here: {@link Component}
 * 
 * @author Birdcraft33
 * @category Component
 * @version 1.0
 * @since 1.0
 * @extends no
 * @initialize yes
 * @extends no
 * @initialize yes
 * @basedOn {@link Component}
 */
public class MessageComponent extends Component{

	
	private String message;
	
	/**
	 * The Constructor of the MessageComponent
	 * @param meta The {@link ComponentMeta} of the MessageComponent
	 * @param message The Message of this MessageComponent
	 */
	public MessageComponent(ComponentMeta meta, String message) {
		super(meta);
		
		this.message = message;
	}
	
	/**
	 * Set the Message of this MessageComponent
	 * @param message The Message you will set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Get the Message of this MessageComponent
	 * @return The Message you set to this MessageComponent
	 */
	public String getMessage() {
		return this.message;
	}
	
	

	@Override
	public void componentClicked(HumanEntity whoClicked, Inventory clickedInventory, InventoryAction action, int slot, ClickType clickType) {
		Player player = (Player) whoClicked;
		String message = this.getMessage();
		player.sendMessage(message);
		Gui.closeGui(player);
		Bukkit.getPluginManager().callEvent(new GuiMessageComponentClickEvent(this, whoClicked, Gui.getGuiFromInventory(clickedInventory), action, slot, clickType));
	}
	
	//<------------------------------------------------------------------------/*NEEDED PATH*/-------------------------------------------------------->//
	
	
	private static final String TYPE = "messageComponent";
	
	@Override
	public Component setGuiComponent() {
		return this;
	}

	@Override
	public String setGuiComponentType() {
		return TYPE;
	}


}
