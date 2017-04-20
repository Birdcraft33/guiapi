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
import com.bluebird.api.gui.events.component.NoOptionClickEvent;

@Deprecated
public class GuiNoOptionClickCall implements Listener{

	
	@EventHandler
	public void onClickNo(EventComponentClickedEvent event) {
		if(YesNoOption.componentIsNoOption(event.getClickedComponent())) {
			/*COMPONENT IS NO_OPTION*/
			EventComponent component = event.getClickedComponent();
			YesNoOption yesNoOption = (YesNoOption) component.getGuiComponentMeta().getSavedComponent("yesNoComponent");
			Gui gui = event.getGui();
			HumanEntity whoClicked = event.getWhoClicked();
			InventoryAction action = event.getAction();
			int slot = event.getSlot();
			ClickType clickType = event.getClickType();
			
			Bukkit.getPluginManager().callEvent(new NoOptionClickEvent(yesNoOption, component, gui, whoClicked, action, slot, clickType));
			
		}
	}
	
	
}
