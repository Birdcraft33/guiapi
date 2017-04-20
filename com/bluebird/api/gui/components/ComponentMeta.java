package com.bluebird.api.gui.components;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


/**
 * <h2>general</h2>
 * This class is for the Meta of every component. An instance of this class is a ComponentMeta.
 * 
 * <h2>What is a ComponentMeta</h2>
 * Every {@link Component} need a ComponentMeta. The ComponentMeta have all infos about the look of the Component(For example: The name, description, look...).
 * 
 * <h2>infos</h2>
 * PLEASE DIDN'T EXTENDS THIS CLASS. in this Version this class isn't optimated for an extends. I would also say, that you will not need an
 * new ComponentMeta.
 * 
 * @author Birdcraft33
 * @category ComponentMeta
 * @version 1.0
 * @since 1.0
 * @extends no
 * @initialize yes
 */
public class ComponentMeta {

	
	private String name;
	private ArrayList<String> description = new ArrayList<>();
	private ItemStack look;
	private HashMap<String, Component> savedComponents = new HashMap<>();
	private static ArrayList<ComponentMeta> metas = new ArrayList<>();
	
	/**
	 * Create a simple GuiComponent
	 * @param name The name of this component
	 * @param clickType The ClickType this Component do something(RIGHT_CLICK, LEFT_CLICK...)
	 * @param component The ItemStack this Component looks like
	 */
	public ComponentMeta(String name, ItemStack look) {
		setName(name);
		setLook(look);
		metas.add(this);
	}
	
	
	
	
	
	
	//<---------------------------------------------------------------------------------/*SIMPLE_METHODS*/--------------------------------------------------------------->//
	
	/*VOID*/
	/**
	 * Set the name of this Component
	 * @param name The name of this Component
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set the Look of this Component
	 * @param name The ItemStack of this Component
	 */
	public void setLook(ItemStack look) {
		this.look = look;
	}
	
	/**
	 * Set the Description of this Component
	 * @param description The description you want to set
	 */
	public void setDescription(ArrayList<String> description) {
		this.description = description;
	}
	
	/**
	 * Add a descriptionLine to the Component
	 * @param descriptionAdd The descriptionLine you want to add
	 */
	public void addDescription(String descriptionAdd) {
		this.description.add(descriptionAdd);
	}
	
	
	/*RETURN*/
	/**
	 * Get The name of this Component
	 * @return The name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Get The Look of this Component
	 * @return The Look
	 */
	public ItemStack getLook() {
		return this.look;
	}
	
	/**
	 * Get The Description of this Component
	 * @return The description
	 */
	public ArrayList<String> getDescription() {
		return this.description;
	}
	
	
	/**
	 * Save a {@link Component} in this {@link ComponentMeta}.
	 * @param key The Key under witch you save your {@link Component} in this meta. You will need it to get this saved {@link Component}
	 * @param component The {@link Component} you will save in the {@link ComponentMeta}
	 */
	public void saveComponentInMeta(String key, Component component) {
		this.savedComponents.put(key, component);
	}
	
	/**
	 * Get a saved GuiComponent from this GuiComponentMeta. You can save a GuiComponent in a Meta with "YOUR_META.saveComponentInMeta(...);"
	 * @param key The Key with witch you save your GuiComponent in the GuiComponentMeta
	 * @return The GuiComponent you saved
	 */
	public Component getSavedComponent(String key) {
		return this.savedComponents.get(key);
	}
	
	
	//<--------------------------------------------------BUILD_METHODS-------------------------------------------------->//
							
	
	protected ItemStack buildItem() {
		ItemStack item = this.look;
		ItemMeta meta = item.getItemMeta();
		
		//SET DISPLAY_NAME
		meta.setDisplayName(name);
		//SET DESCRIPTION
		meta.setLore(description);
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	
	
}
