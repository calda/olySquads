package com.olympuspvp.caldabeast.CommandManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.olympuspvp.caldabeast.olySquad;

public class AcceptInvite {

	public static void run(Player p){
		String name = p.getName();
		String checkSquad = olySquad.getInvite(name);
		if(checkSquad != null){
			String currentSquad = olySquad.getSquad(name);
			if(currentSquad == null){
				String squadName = olySquad.getInvite(name);
				if(olySquad.getMembers(squadName).size() != 5){
					olySquad.addMember(squadName, name);
					olySquad.setSquad(name, squadName);
					olySquad.squadCast(squadName,name," has joined the squad!");
					olySquad.setInvite(name, null);
				}else{
					p.sendMessage(ChatColor.GRAY + "The squad " + ChatColor.LIGHT_PURPLE + ChatColor.GRAY + " is currently full.");
				}
			}else{
				p.sendMessage(ChatColor.GRAY + "You are already in a squad.");
				p.sendMessage(ChatColor.GRAY + "Type " + ChatColor.LIGHT_PURPLE + "/squad leave" + ChatColor.GRAY + " to leave your current squad.");
			}
		}else{
			p.sendMessage(ChatColor.GRAY + "You are no longer invited to this squad.");
		}
	}
}