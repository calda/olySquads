package com.olympuspvp.caldabeast.CommandManager;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import com.olympuspvp.caldabeast.olySquad;

public class LeaveSquad {
	static FileConfiguration config = olySquad.getFileConfig();
	public static void run(Player p){
		String name = p.getName();
		String squad = olySquad.getSquad(name);
		String owner;
		if(squad == null){
			p.sendMessage(ChatColor.GRAY + "You are not currently in a squad.");
		}else{
			owner = olySquad.getOwner(squad);
			if (owner.equals(name)){
				p.sendMessage(ChatColor.GRAY + "You cannot leave. You must disband the squad.");
			}else{
				olySquad.squadCast(squad, name, "has left the squad.");
				olySquad.setSquad(name, null);
				olySquad.removeMember(squad, name);
			}
		}
		
	}
	
}
