package com.bluebird.api.gui.components;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


/**
 * <h2>general</h2>
 * This is the baseclass of components. It have all methods and variables every component need (for example the long id or the Method finalizeComponent)
 * 
 * <h2>technical background</h2>
 * All Components of the GuiAPI are based on the Component class. This class initialize and finalize a component. It have also got a method witch
 * will started automatic if somebody clicked on this component.  
 * 
 * <h2>Own Component</h2>
 * If you want to create your own component you have to extends this class. Now you have to create a constructor with super(meta). After
 * this you have to implements the needed methods setGuiComponent(), setGuiComponentType() and componentClicked(...). <br>
 * The override method setGuiComponent() must return your guiComponent(this). The Method setGuiComponentType have to return a String. The Text
 * of this String now will be the name of your new kind of component(but you will not need this string; it is only for the API). The method
 * setGuiComponentType will be started every time if the component is clicked.<br><br>
 * 
 * @author Birdcraft33
 * @version 1.0
 * @since 1.0
 * @category component
 * @extends yes
 * @initialize no
 */
abstract public class Component{

	
	private ComponentMeta meta;
	private ItemStack look;
	private String componentType;
	private long id;
	private Runnable clickAction = null;
	private Runnable finalizedAction = null;
	private int place;
	
	//STRING=COMPONENT_KIND; ARRAY_LIST=COMPONENTS OF THIS KIND
	protected static HashMap<String, ArrayList<Component>> components = new HashMap<>();
	//ARRAY_LIST WITH KINDS
	protected static ArrayList<String> componentKinds = new ArrayList<>();
	//ARRAY_LIST OF ALL COMPONENTS
	protected static ArrayList<Component> componentArray = new ArrayList<>();
	
	/**
	 * Create a new GuiComponent
	 * @param meta Set the Meta of this Component
	 */
	public Component(ComponentMeta meta) {
		
			ItemStack look = meta.buildItem();
		
			this.meta = meta;
			this.look = look;
		
			/*SET GUI_TYPE AND COMPONENT IN LIST*/
			String type = setGuiComponentType();
			Component component = setGuiComponent();
			setNewGuiComponent(type, component);
			this.componentType = type;
		
			/*SET ID*/
			id = componentArray.size()-1;
			System.out.println("ComponentID of " + meta.getName() + ": " + id);
	}
	
	
	
	

	private void setNewGuiComponent(String type, Component component) {
		if(components.containsKey(type)) {
			/*COMPONENT_TYPE ALREADY SET*/
			ArrayList<Component> componentsOfType = components.get(type);
			componentsOfType.add(component);
			components.put(type, componentsOfType);
			
			/*SET IN COMPONENT_ARRAY*/
			componentArray.add(component);
		}else{
			/*COMPONENT_TYPE ISN'T SET*/
			ArrayList<Component> componentsOfType = new ArrayList<>();
			componentsOfType.add(component);
			components.put(type, componentsOfType);
			
			/*SET COMPONENT_KIND*/
			componentKinds.add(type);
			
			/*SET IN COMPONENT_ARRAY*/
			componentArray.add(component);
		}
	}
	
	
	
	/**
	 * ONLY FOR EVENT_REGISTRY!!!
	 */
	protected Component() {
		
	}
	
	public void finalize() {
		System.out.println("GuiComponent ZERSTÖRT!!!");
	}
	
	
	public ComponentMeta getGuiComponentMeta() {
		return this.meta;
	}
	
	public ItemStack getGuiItem() {
		return this.look;
	}
	
	public String getComponentType() {
		return this.componentType;
	}
	
	public long getID() {
		return this.id;
	}
	
	
	public void setGuiComponentMeta(ComponentMeta meta) {	System.out.println("Set ComponentMeta of " + this.getGuiComponentMeta().getName() + "  to CompnentMeta" + meta.getName() + "...");
		this.meta = meta;
		/*SET NEW ITEM_META*/
		look = new ItemStack(meta.getLook());
		ItemMeta itemMeta = look.getItemMeta();
		itemMeta.setDisplayName(meta.getName());
		if(!meta.getDescription().isEmpty() && meta.getDescription() != null) {
			itemMeta.setLore(meta.getDescription());
		}
		look.setItemMeta(itemMeta);
		meta.setLook(look);
		if(look == null) {System.out.println("Look == null");}	System.out.println("ComponentMeta of " + this.getGuiComponentMeta().getName() + " is set");
	}
	
	
	public void setPlace(int place) {
		this.place = place;
	}
	
	public int getPlace() {
		return this.place;
	}
	
	
	//<-----------------------------------------------------------------/*FINALIZE_COMPONENT*/-------------------------------------------------------->//
	public void finalizeComponent() {
		System.out.println("finalizeComponent: " + this.getID() + "...");
		Component.componentArray.remove(this);
		Component.components.get(this.getComponentType()).remove(this);
		startFinalizedAction();
	}
	
	
	//<-----------------------------------------------------------------/*CLICK_ACTION*/--------------------------------------------------------->//
	public void setClickAction(Runnable action) {
		this.clickAction = action;
	}
	
	protected void startClickAction() {
		if(clickAction != null) {
			clickAction.run();
		}
	}
	
	
	//<------------------------------------------------------------------/*FINALIZE_ACTION*/---------------------------------------------->>//
	
	public void setFinalizedAction(Runnable finalizedAction) {
		this.finalizedAction = finalizedAction;
	}
	
	protected void startFinalizedAction() {
		if(this.finalizedAction != null) {
			finalizedAction.run();
		}
	}
	
	
	//<-----------------------------------------------------------------/*STATIC METHODS*/------------------------------------------------------------->//
	
	
	protected static ArrayList<Component> getGuiComponents(String type) {
		return components.get(type);
	}
	
	
	public static Component getComponentFromItemStack(ItemStack item, int place) {				System.out.println("Start method getComponentFromItemStack");
		if(item.hasItemMeta()) {												
			for(Component component : componentArray) {							
				/*FOR EVERY COMPONENT WITCH IS REGISTRATED*/
				ItemStack componentItem = component.getGuiComponentMeta().getLook();
																						
				if(componentItem.equals(item) && place == component.getPlace()) {			
					return component;													
				}																		
			}																			
		}																				
		return null;																	
	}																					
	
	public static boolean itemStackIsGuiComponent(ItemStack item) {
		if(item.hasItemMeta()) {
			for(Component component : componentArray) {
				/*FOR EVERY COMPONENT WITCH IS REGISTRATED*/
				ItemStack componentItem = component.getGuiComponentMeta().getLook();
				
				if(componentItem.equals(item)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean componentIsSpecialType(Component guiComponent, String type) {	System.out.println("Start Method: componentIsSpecialType");
		if(typeContains(type)) {															System.out.println("  131");
			for(Component component : getComponentsOfType(type)) {						System.out.println("  132");
				if(component.getID() == guiComponent.getID()) { System.out.println("  133");
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean typeContains(String type) {
		if(componentKinds.contains(type)) {
			return true;
		}
		return false;
	}
	
	public static ArrayList<Component> getComponentsOfType(String type) {
		return components.get(type);
	}
	
	//<-----------------------------------------------------------------/*ABSTRACT METHODS*/---------------------------------------------------------->//
	
	/**
	 * Set the GuiComponent into the Plugin
	 * @return The HashMap: String = Key for your Component_Kind(you will need this Key to find your Component_Kind in all other kinds); GuiComponent = The new Component of this Kind(this if you are in the Class of this new Component)
	 */
	public abstract Component setGuiComponent();
	
	public abstract String setGuiComponentType();
	
	public abstract void componentClicked(HumanEntity whoClicked, Inventory clickedInventory, InventoryAction action, int slot, ClickType clickType);
	
}
