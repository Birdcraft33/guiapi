package com.bluebird.api.gui.components;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.bluebird.api.gui.Gui;
import com.bluebird.api.gui.events.component.EventComponentClickedEvent;
import com.bluebird.api.gui.events.component.NoOptionClickEvent;
import com.bluebird.api.gui.events.component.YesOptionClickEvent;

/**
 * <h2>general</h2>
 * The {@link YesNoOption} is based on {@link Settings} and this is based on {@link Component}. 
 * 
 * @author Birdcraft33
 * @category Component
 * @version 1.0
 * @since 1.0
 * @extends no
 * @initialize yes
 * @basedOn {@link Component}
 */
public class YesNoOption extends Component implements Listener{

	private static final String type = "YesNoOption";
	private static ArrayList<EventComponent> yesComponents = new ArrayList<>();
	private static ArrayList<EventComponent> noComponents = new ArrayList<>();
	
	
	private ComponentMeta meta;
	private String yesNoOptionTitle = "Yes or No";
	
	private ComponentMeta yesOptionMeta = new ComponentMeta("Yes", new ItemStack(35, 1, (short) 0, (byte) 5));
	private EventComponent yesOption = new EventComponent(yesOptionMeta);
	
	private ComponentMeta noOptionMeta = new ComponentMeta("No", new ItemStack(Material.BARRIER));
	private EventComponent noOption = new EventComponent(noOptionMeta);
	
	private boolean isOpen = false;
	private OptionOpen open = OptionOpen.underInventory;
	
	
	/**
	 * Create a new GuiYesNoOption. If an Option of this component is clicked it will call the Event GuiYesOptionClickedEvent/GuiNoOptionClickedEvent
	 * @param meta The Meta of this yesNoOption
	 */
	public YesNoOption(ComponentMeta meta) {
		super(meta);
		
		this.meta = meta;
		
		/*SAVE THIS YES_NO_COMPONENT IN YES AND NO COMPONENTS TO FIND THIS YES_NO_COMPONENT OVER THE YES OR NO COMPONENT*/
		this.yesOptionMeta.saveComponentInMeta("yesNoComponent", this);
		this.noOptionMeta.saveComponentInMeta("yesNoComponent", this);
	}
	
	/**
	 * Create a new GuiYesNoOption. If an Option of this component is clicked it will call the Event GuiYesOptionClickedEvent/GuiNoOptionClickedEvent
	 * @param meta The Meta of this yesNoOption
	 * @param yesNoOptionTitle The Title of the YesNoTookInventory
	 * @param yesOptionMeta The Meta of the Item YesOption
	 * @param noOptionMeta The Meta of the NoOption
	 * @param open How the Yes/No Option will open
	 */
	public YesNoOption(ComponentMeta meta, String yesNoOptionTitle, ComponentMeta yesOptionMeta, ComponentMeta noOptionMeta, OptionOpen open) {
		super(meta);
		
		this.meta = meta;
		this.yesNoOptionTitle = yesNoOptionTitle;
		this.yesOptionMeta = yesOptionMeta;
		this.noOptionMeta = noOptionMeta;
		this.open = open;
		
		reloadYesOption();
		reloadNoOption();
		
		
		/*SAVE THIS YES_NO_COMPONENT IN YES AND NO COMPONENTS TO FIND THIS YES_NO_COMPONENT OVER THE YES OR NO COMPONENT*/
		this.yesOptionMeta.saveComponentInMeta("yesNoComponent", this);
		this.noOptionMeta.saveComponentInMeta("yesNoComponent", this);
	}
	
	//<-----------------------------------------------------------------------/*FINALIZE_METHOD*/------------------------------------------------------------>//
	@Override
	public void finalizeComponent() {
		super.finalizeComponent();
		
		this.noOption.finalizeComponent();
		this.yesOption.finalizeComponent();
	}
	
	//<-------------------------------------------------------------------------/*GET_METHODS*/-------------------------------------------------------------->//
	
	
	public ComponentMeta getComponentMeta() {
		return this.meta;
	}
	
	public String getYesNoOptionTitle() {
		return this.yesNoOptionTitle;
	}
	
	public OptionOpen getOpen() {
		return this.open;
	}
	
	public EventComponent getYesOption() {
		return this.yesOption;
	}
	
	public EventComponent getNoOption() {
		return this.noOption;
	}
	
	public boolean getIsOpened() {
		return this.isOpen;
	}
	//<-------------------------------------------------------------------------/*SET_METHODS*/------------------------------------------------------------->//
	
	/**
	 * Set the Title of the Yes/No inventory
	 * @param title
	 */
	public void setYesNoOptionTitle(String title) {
		this.yesNoOptionTitle = title;
	}
	
	/**
	 * Set the Meta of the yesOption Component
	 * @param meta
	 */
	public void setYesOptionMeta(ComponentMeta meta) {
		this.yesOptionMeta = meta;
	}
	
	/**
	 * Set the Meta of the noOption Component
	 * @param meta
	 */
	public void setNoOptionMeta(ComponentMeta meta) {
		this.noOptionMeta = meta;
	}
	
	/**
	 * Set how the Yes/No Option will open
	 * @param open OptionOpen
	 */
	public void setOpen(OptionOpen open) {
		this.open = open;
	}
	
	public void setIsOpened(boolean isOpened) {
		this.isOpen = isOpened;
	}
	
	
	//<-------------------------------------------------------------------------/*OVERRIDE_METHODS*/-------------------------------------------------------->//
	
	

	@Override
	public Component setGuiComponent() {
		return this;
	}
	
	@Override
	public String setGuiComponentType() {
		return type;
	}
	
	//<---------------------------------------------------------------------------------/*PRIVATE_METHODS*/--------------------------------------------------->//
	
	private void reloadYesOption() {
		this.yesOption = new EventComponent(yesOptionMeta);
	}
	
	private void reloadNoOption() {
		this.noOption = new EventComponent(noOptionMeta);
	}
	
	//<----------------------------------------------------------------------------------/*STATIC_METHODS*/--------------------------------------------------->//
	
	public static boolean componentIsYesOption(EventComponent testedComponent) {
		for(EventComponent component : yesComponents) {
			if(component.equals(testedComponent)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean componentIsNoOption(EventComponent testedComponent) {
		for(EventComponent component : noComponents) {
			if(component.equals(testedComponent)) {
				return true;
			}
		}
		return false;
	}
	
	public static String getType() {
		return type;
	}
	
	//<------------------------------------------------------------------------------------/*EVENTS*/--------------------------------------------------------->//
	
	@Override
	public void componentClicked(HumanEntity whoClicked, Inventory clickedInventory, InventoryAction action, int slot, ClickType clickType) {
		if(open == OptionOpen.underInventory) {
			componentClickedUnderInv(whoClicked, clickedInventory, action, slot, clickType);
		}else{
			componentClickedNewGui(whoClicked, clickedInventory, action, slot, clickType);
		}
	}
	
	
	public void componentClickedNewGui(HumanEntity whoClicked, Inventory clickedInventory, InventoryAction action, int slot, ClickType clickType) {	
		Gui gui = new Gui(this.yesNoOptionTitle);
		
		/*CREATE NEW YES/NO OBJECTS*/
		EventComponent yesOption = new EventComponent(yesOptionMeta);				
		EventComponent noOption = new EventComponent(noOptionMeta);				
		
		/*SAVE YES AND NO COMPONENTS IN THE ARRAY_LIST*/
		this.yesComponents.add(yesOption);
		this.noComponents.add(noOption);
		
		/*SET YES AND NO IN GUI*/
		gui.setComponent(yesOption, 3);													
		gui.setComponent(noOption, 4);	
		
		/*OPEN GUI*/
		gui.openGui((Player) whoClicked);												
		
																						
	}
	
	public void componentClickedUnderInv(HumanEntity whoClicked, Inventory clickedInventory, InventoryAction action, int slot, ClickType clickType) {
		if(!isOpen) {
			/*OPEN*/
			Gui gui = Gui.getGuiFromInventory(clickedInventory);
			int size = clickedInventory.getSize();
		
			/*CREATE NEW YES/NO OBJECTS*/
			EventComponent yesOption = new EventComponent(yesOptionMeta);
			EventComponent noOption = new EventComponent(noOptionMeta);
			
			/*SAVE YES AND NO COMPONENTS IN THE ARRAY_LIST*/
			this.yesComponents.add(yesOption);
			this.noComponents.add(noOption);
		
			/*SET YES AND NO IN GUI*/
			gui.setComponent(yesOption, size+3);
			gui.setComponent(noOption, size+5);
			
			/*OPEN GUI*/
			gui.openGui((Player)whoClicked);
		
			isOpen = true;
		}else{
			/*CLOSE*/
			Gui gui = Gui.getGuiFromInventory(clickedInventory);
			int size = clickedInventory.getSize();
			
			gui.setSize(size-9);
			gui.useSize(true);
			
			gui.openGui((Player)whoClicked);
			
			isOpen = false;
		}
	}
	
	
	
}
