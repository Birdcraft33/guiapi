package com.bluebird.api.gui.components;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.bluebird.api.gui.Gui;


/**
 * <h2>general</h2>
 * This class is based on the {@link Component} class. If you click on a FolderComponent you will be send to a new {@link Gui}.
 * 
 * <h2>technical background</h2>
 * If you click on the folder it will automatic send you to the new {@link Gui} and will also finalize the old {@link Gui}. More technical
 * background is in the class {@link Component}
 * 
 * @author Birdcraft33
 * @category Component
 * @version 1.0
 * @since 1.0
 * @extends no
 * @initialize yes
 * @basedOn {@link Component}
 */
public class Folder extends Component{
	
	
	private Gui newOpenGui;
	private static ArrayList<Folder> folders = new ArrayList<>();
	private static final String type = "folder";
	private boolean guiOpen = false;
	
	/**
	 * Create a new GuiFolder
	 * @param meta The Meta of this Folder
	 * @param newOpenGui The GuiWindow witch will open if you click on
	 */
	public Folder(ComponentMeta meta, Gui newOpenGui) {
		super(meta);
		
		this.newOpenGui = newOpenGui;
	}

	/**
	 * ONLY FOR EVENT_REGISTRY!!!
	 */
	protected Folder() {
		
	}

	@Override
	public Component setGuiComponent() {
		return this;
	}
	
	@Override
	public String setGuiComponentType() {
		return type;
	}
	
	
	/*SIMPLE_RETURN_METHODS*/
	public Gui getNewOpenGui() {
		return this.newOpenGui;
	}
	
	public ComponentMeta getMeta() {
		return this.getGuiComponentMeta();
	}
	
	//<---------------------------------------------------------------------/*FINALIZE COMPONENT*/------------------------------------------------------------>//
	@Override
	public void finalizeComponent() {
		super.finalizeComponent();
		
		
		if(!guiOpen) {	/*@Info hier wird als erstes geguckt ob das Gui geöffnet wurde. Wenn es nicht geöffnet wurde, wird es zerstört, da sonst die bereits für dieses Gui geladenen 
		 						Componenten überleben würden und das alles durcheinander bringen würde. Ist das Gui allerdings geöffnet wird es nicht zerstört, da es sonst die Funktion
		 						Des Guis verlieren würde. Das bedeutet das die Componenten in dem Gui nicht mehr funktionieren würden*/
			this.newOpenGui.finalizeGui();
		}
	}
	
	
	//<---------------------------------------------------------------------/*STATIC METHODES*/---------------------------------------------------------------->//
	/*PUBLIC METHODS*/
	
	/**
	 * You can check if an item is a {@link Folder}
	 * @param item The item you want to check
	 * @return true=this Item is an {@link Folder}; false=this item isn't an {@link Folder}
	 */
	public static boolean itemIsGuiFolder(ItemStack item) {
		for(Folder folder : folders) {
			//VARIABLES//
			ItemStack folderItem = folder.getGuiComponentMeta().getLook();
			
			if(folderItem.getItemMeta().equals(folderItem.getItemMeta())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * You can get a {@link Folder} from an normal Item if this Item is a {@link Folder}
	 * @param item The item from witch you want to get the {@link Folder}
	 * @return The Folder; null=the Item is no {@link Folder}
	 */
	public static Folder getFolderFromItem(ItemStack item) {
		for(Folder folder : folders) {
			//VARIABLES//
			ItemStack folderItem = folder.getGuiComponentMeta().getLook();
			
			if(item.hasItemMeta() && folderItem.hasItemMeta()) {
				if(folderItem.getItemMeta().equals(item.getItemMeta())) {
					return folder;
				}
			}
		}
		return null;
	}
	
	
	//<-------------------------------------------------------------------/*EVENT*/--------------------------------------------------------------------------------->//
	@Override
	public void componentClicked(HumanEntity whoClicked, Inventory clickedInventory, InventoryAction action, int slot, ClickType clickType) {
		Player player = (Player) whoClicked;
		
		/*@Info Diese Variable regelt, ob newOpenGui zerstört werden soll, oder nicht(Mehr dazu in Zeile 69). Deswegen muss diese Vereinbarung noch vor dem öffnen des Guis
		 * 		und gleichzeitig auch vernichten dieses Guis und der Componenten(inbegriffen dieses Folders) passieren*/
		this.guiOpen = true;
		
		System.out.println("com.bluebird.api.gui.GuiFolder.java: 116");
		this.newOpenGui.openGui(player);
		
	}
	
}
