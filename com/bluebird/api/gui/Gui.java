package com.bluebird.api.gui;

import java.util.ArrayList;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.bluebird.api.gui.components.Component;
import com.bluebird.api.gui.components.ComponentMeta;
import com.bluebird.api.gui.components.StaticComponent;

/**
 * <h2>general</h2>
 * This is the GuiClass. An instance of this class is a full gui. In this gui you can now put {@link Component}s.
 * 
 * <h2>technical background</h2>
 * A gui is a simple inventory you can also create very simple with bukkit/spigot. But in a gui you can put one or more {@link Component}/s. 
 * If you create a simple inventory this isn't possible. A gui instance will manage all the {@link Component}s in it. For example: If you will
 * close a gui (with a method or with E ingame) this gui instance will finalize all {@link Component}s in the gui automatic.
 * 
 * <h2>instance</h2>
 * If you want to get a gui instance you can do this simple with the constructor of the class {@link Gui}. More infos in the desciption of the comstructor.
 * 
 * <h2>infos</h2>
 * This class isn't program to extends it. If you extends this class it can give you many exceptions.<br><br>
 * 
 * @author Birdcraft33
 * @version 1.0
 * @category Gui
 * @since 1.0
 */
public class Gui{

	
	private String title;
	private ArrayList<Integer> position = new ArrayList<>();
	private ArrayList<Component> components = new ArrayList<>();
	private Inventory inventory;
	private int size;
	private boolean fillEmptyPlaces = false;
	private ItemStack fillItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0, (byte) 7);
	private boolean useSize = false;
	private boolean isFinalized = false;
	private static final String isFinalizedMessage = GuiApiInitializer.prefix + "-> GUI: Gui is already finalized.";
	
	private static ArrayList<Gui> guis = new ArrayList<>();
	
	
	public Gui(String title) {
		this.title = title;
	}
	
	/**
	 * ONLY TO REGISTER THIS CLASS FOR EVENTS//
	 */
	protected Gui() {
		
	}
	
	
	public void finalize() {
		System.out.println("Gui ZERSTÖRT!!!");
	}
	
	
	//<-------------------------------------------------------------------------/*COMPONENT METHODES*/--------------------------------------------------------->//
	
	/*FOLDER*/
	/**
	 * Add a Component in a free space in the Gui
	 * @param component The component you want to add
	 */
	public void addComponent(Component component) {
		if(!isFinalized) {
			components.add(component);
			position.add(components.size()-1);
		}else{
			//IS FINALIZED//
			System.err.println(isFinalizedMessage);
		}
	}
	
	/**
	 * Set a Component in a space you want
	 * @param component The Component you want to add
	 * @param position The Position this component is located
	 */
	public void setComponent(Component component, int position) {
		if(!isFinalized) {
			components.add(component);
			this.position.add(position);
		}else{
			//IS FINALIZED//
			System.err.println(isFinalizedMessage);
		}
	}
	
	/**
	 * Remove a Component form a position
	 * @param position The Position of the Component you will remove
	 */
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void useSize(boolean useSize) {
		this.useSize = useSize;
	}
	
	
	public ArrayList<Component> getComponents() {
		return this.components;
	}
	
	/**
	 * true = All slots without a component become a fillItem; false = No fill
	 * @param fillEmptyPlaces
	 */
	public void setFillEmptyPlaces(boolean fillEmptyPlaces) {
		this.fillEmptyPlaces = fillEmptyPlaces;
	}
	
	public boolean isFillEmptyPlaces() {
		return fillEmptyPlaces;
	}
	
	public void setFillItem(ItemStack fillItem) {
		this.fillItem = fillItem;
	}
	
	public ItemStack getFillItem() {
		return fillItem;
	}
	
	
	//<-------------------------------------------------------------------------/*CREATE_GUI*/----------------------------------------------------------------->//
	
	public void openGui(Player player) {											System.out.println("OpenGuiStart");
	if(!isFinalized) {
		int slots;
		if(useSize) {
			slots = size;
		}else{
			slots = getSlotCount(getHighestPos());								
		}
		
		setFiller(slots);
		
		if(slots != 0 || slots != -1) {	
			/*CREATE INVENTORY*/
			Inventory inventory = Bukkit.createInventory(player, slots, title);		
			
			this.inventory = inventory;
			guis.add(this);
			
			int i = 0;																
			for(Component component : components) {								
				ItemStack item = component.getGuiItem();							
				int position = this.position.get(i);
				
				if(position < slots) {
					inventory.setItem(position, item);
					component.setPlace(position);
				}
				
				i++;																
			}
			player.openInventory(inventory);	
			
			this.useSize = false;
																					System.out.println("OpenGuiFinish");	
		}else{
			if(slots == -1) {
				System.err.println(GuiApiInitializer.prefix + "-> GUI: Problems with open Gui because there are to many components for one Gui");
				System.err.println(GuiApiInitializer.prefix + "-> GUI: Max Components == 54");
			}else{
				System.err.println(GuiApiInitializer.prefix + "-> GUI: Problems with open Gui without any component set");
			}
		}
	}else{
		//GUI IS FINALIZED//
		System.err.println(isFinalizedMessage);
	}
	}
	
	private int getSlotCount(int highestPos) {
		int count = 0;
		
		if(highestPos >= 0 && highestPos <= 8) {
			count = 9;
		}
		if(highestPos >= 9 && highestPos <= 17) {
			count = 18;
		}
		if(highestPos >= 18 && highestPos <= 26) {
			count = 27;
		}
		if(highestPos >= 27 && highestPos <= 35) {
			count = 36;
		}
		if(highestPos >= 36 && highestPos <= 44) {
			count = 45;
		}
		if(highestPos >= 45 && highestPos <= 53) {
			count = 54;
		}
		if(highestPos >= 54 || highestPos <= -1) {
			System.err.println(GuiApiInitializer.prefix + "Problems with setting size of the inventory of the gui " + title);
			return -1;
		}
		
		return count;
	}
	
	private int getHighestPos() {
		int hightestPos = 0;
		for(int position : this.position) {
			if(position > hightestPos) {
				hightestPos = position;
			}
		}
		System.out.println("hightestPos: " + hightestPos);
		return hightestPos;
	}
	
	private void setFiller(int slotSize) {
		
		if(fillEmptyPlaces) {													System.out.println("Fill item");
			/*FILL EMPTY SLOTS*/
			for(int i = 0; i < slotSize; i++) {									System.out.println("slot " + i);System.out.println("  test if slot has Item");
				if(!position.contains(i)) {										System.out.println("  no item at slot");
					ComponentMeta fillerMeta = new ComponentMeta(" ", fillItem);System.out.println("  create componentMeta with material " + fillItem.getType().toString());
					StaticComponent filler = new StaticComponent(fillerMeta);	System.out.println("  create staticComponent");
					this.setComponent(filler, i);								System.out.println("  set component in gui at pos " + i);
				}
			}																	System.out.println("finished");
		}
	}
	
	
	
	//<-------------------------------------------------------------------------/*CLOSE_GUI*/------------------------------------------------------------------>//
	
	public static void closeGui(Player player) {
		if(inventoryIsGui(player.getOpenInventory().getTopInventory())) {
			/*PLAYER HAS OPENED A GUI*/
			
			Gui gui = getGuiFromInventory(player.getOpenInventory().getTopInventory());
			
			gui.finalizeGui();
			
			player.closeInventory();
		}
	}
	
	public void finalizeGui() {
		System.out.println("Finalizing Gui " + title + "...");
		/*FINALIZE COMPONENTS OF GUI*/
		for(Component component : this.components) {
			component.finalizeComponent();
		}
		/*REMOVE GUI FROM GUI_ARRAY*/
		Gui.guis.remove(this);
		this.isFinalized = true;
		System.out.println("Gui " + title + " finalized");
	}
	
	
	//<-------------------------------------------------------------------------/*SIMPLE METHODS*/-------------------------------------------------------------->//
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public boolean isFinalized() {
		return this.isFinalized;
	}
	
	//<--------------------------------------------------------------------------/*STATIC METHODS*/------------------------------------------------------------->//
	public static Gui getGuiFromInventory(Inventory inventory) {
		if(inventoryIsGui(inventory)) {
			for(Gui gui : guis) {
				/*FOR EVERY GUI*/
				if(gui.getInventory().equals(inventory)) {
					return gui;
				}
			}
		}
		return null;
	}
	
	public static boolean inventoryIsGui(Inventory inventory) {
		for(Gui gui : guis) {
			/*FOR EVERY GUI*/
			if(gui.getInventory() != null) {
				if(gui.getInventory().equals(inventory)) {
					return true;
				}
			}
		}
		return false;
	}
	

	//<--------------------------------------------------------------------------/*EVENTS*/--------------------------------------------------------------------->//
	
	
}
