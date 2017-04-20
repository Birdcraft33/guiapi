package com.bluebird.api.gui.components;

import org.bukkit.inventory.ItemStack;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion.Setting;

/**
 * <h2>general</h2>
 * This is one option you can set in the {@link Settings}Component.
 * 
 * <h2>technical background</h2>
 * Actually this is only a class witch has one meta in it, but it is easier so to understand what the {@link Settings}Component do
 *
 * @author Birdcraft33
 * @category SettingsType
 * @version 1.0
 * @since 1.0
 * @extends no
 * @initialize yes
 * @basedOn {@link Component}
 */
public class SettingOption {

	
	private ComponentMeta meta;
	
	
	public SettingOption(ComponentMeta meta) {
		this.meta = meta;
	}
	
	
	
		
	
	
	//<-------------------------------------------------/*SIMPLE SET_METHODS*/------------------------------------------------->//
	public void setMeta(ComponentMeta meta) {
		this.meta = meta;
	}
	//<-------------------------------------------------/*SIMPLE GET_METHODS*/------------------------------------------------->//
	public ComponentMeta getMeta() {
		return meta;
	}
}
