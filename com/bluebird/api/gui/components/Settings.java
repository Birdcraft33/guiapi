package com.bluebird.api.gui.components;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;

import org.bukkit.inventory.ItemStack;

import com.bluebird.api.gui.Gui;
import com.bluebird.api.gui.events.component.SettingsClickEvent;

/**
 * <h2>general</h2>
 * This is a component because it is based on {@link Component}. With this Item you can select different Options with a click on this Item. 
 * If you click ones on this item it will show the next option and so on.
 * 
 * <h2>technical background</h2>
 * If you click on this Component it will show a new {@link SettingOption}. With the Method getOption() you can get the option this {@link Settings}
 * have at this point. Because this {@link Settings} is based on {@link Component} here are more to explain: {@link Component}
 * 
 * @author Birdcraft33
 * @category Component
 * @version 1.0
 * @since 1.0
 * @extends no
 * @initialize yes
 * @basedOn {@link Component}
 */
public class Settings extends Component{

	
	private SettingOption[] option;
	private int activatedOptionIndex;
	private SettingOption activatedOption;
	
	
	
	public Settings(SettingOption[] option) {
		//SET FIRST LOOK//
		super(option[0].getMeta());
		
		this.activatedOption = option[0];
		this.activatedOptionIndex = 0;
		
		//SET OPTION_VARIABLE//
		this.option = option;
	}
	
	
	
	//<--------------------------------------------------------/*SIMPLE SET_METHODS*/--------------------------------------------->//
	public void setOptions(SettingOption[] option) {
		this.option = option;
	}
	public void setOption(int optionIndex, Gui gui, Player player) {
		activatedOptionIndex = optionIndex;
		activatedOption = option[optionIndex];
		setGuiComponentMeta(activatedOption.getMeta());
		gui.openGui(player);
	}
	//<--------------------------------------------------------/*SIMPLE GET_METHODS*/--------------------------------------------->//
	public SettingOption[] getOptions() {
		return option;
	}
	public SettingOption getOption() {
		return activatedOption;
	}
	public int getOptionIndex() {
		return activatedOptionIndex;
	}
	//<--------------------------------------------------------/*NEEDED METHODS*/------------------------------------------------->//
	
	private static final String type = "Settings";
	
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
		SettingOption clickedOption = getOption();
		if(activatedOptionIndex >= option.length-1) {
			/*ACTIVATED_OPTION_INDEX IS FINISHED == START FROM 0*/
			activatedOptionIndex = 0;	System.out.println("index set to 0");
		}else{
			/*ACTIVATED_OPTION_INDEX ISN'T FINISHED*/
			activatedOptionIndex++;	System.out.println("index ++");
		}

		
		Gui gui = Gui.getGuiFromInventory(clickedInventory);
		Player player = (Player) whoClicked;
		this.setOption(activatedOptionIndex, gui, player);
		
		Bukkit.getPluginManager().callEvent(new SettingsClickEvent(this, clickedOption, activatedOption, whoClicked, gui, action, slot, clickType));
	}

}
