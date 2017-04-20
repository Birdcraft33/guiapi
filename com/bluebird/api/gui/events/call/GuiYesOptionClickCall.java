package com.bluebird.api.gui.events.call;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

import com.bluebird.api.gui.Gui;
import com.bluebird.api.gui.components.Component;
import com.bluebird.api.gui.components.EventComponent;
import com.bluebird.api.gui.components.YesNoOption;
import com.bluebird.api.gui.events.component.EventComponentClickedEvent;
import com.bluebird.api.gui.events.component.YesOptionClickEvent;

@Deprecated
public class GuiYesOptionClickCall implements Listener{

	
	
	@EventHandler
	public void onClickYesOption(EventComponentClickedEvent event) {
			

		EventComponent component = (EventComponent) event.getClickedComponent();
			
		/*COMPONENT IS YES*/
		if(YesNoOption.componentIsYesOption(component)) {
			
			/*->CALL EVENT<-*/
				
			/*VARIABLES*/
			Gui gui = event.getGui();
			YesNoOption yesNoOption = (YesNoOption) component.getGuiComponentMeta().getSavedComponent("yesNoComponent");
			HumanEntity whoClicked = event.getWhoClicked();
			InventoryAction action = event.getAction();
			int slot = event.getSlot();
			ClickType clickType = event.getClickType();
			
			
			Bukkit.getPluginManager().callEvent(new YesOptionClickEvent(yesNoOption, component, gui, whoClicked, action, slot, clickType));
		
		}
	}
	
	
}
