package com.olympuspvp.caldabeast.CommandManager;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import com.olympuspvp.caldabeast.olySquad;

public class SetHome {
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
				
				Location loc = p.getLocation();
				int x = loc.getBlockX();
				int y = loc.getBlockY();
				int z = loc.getBlockZ();
				String world = loc.getWorld().getName();
				
				config.set("squads." + squad + ".home.x", x);
				config.set("squads." + squad + ".home.y", y);
				config.set("squads." + squad + ".home.z", z);
				config.set("squads." + squad + ".home.world", world);
				
				olySquad.squadCast(squad, name, " has set the squad home");
				
			}else{
				p.sendMessage(ChatColor.GRAY + "You are not the owner of this squad.");
			}
		}
		
	}
	
}
