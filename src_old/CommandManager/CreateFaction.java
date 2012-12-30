package com.olympuspvp.caldabeast.CommandManager;

import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import com.olympuspvp.caldabeast.olySquad;

public class CreateFaction {
	static FileConfiguration config = olySquad.getFileConfig();
	public static void run(final Player p, final String nameToMake){
		final String name = p.getName();
		final String currentSquad = olySquad.getSquad(name);
		if(currentSquad != null){
			p.sendMessage(ChatColor.GRAY + "You are already in a squad.");
			p.sendMessage(ChatColor.GRAY + "Type " + ChatColor.LIGHT_PURPLE + "/squad leave" + ChatColor.GRAY + " to leave your current squad.");
		}else{
			List<String> squads = olySquad.getSquads();
			if(squads == null){
				final String[] setting = {"LISTCREATED "};
				squads = Arrays.asList(setting);
				olySquad.setSquads(Arrays.asList(setting));
			}
			if(!squads.contains(nameToMake)){
				olySquad.setOwner(nameToMake, name);
				olySquad.addSquad(nameToMake);
				olySquad.addMember(nameToMake, name);
				olySquad.setSquad(name, nameToMake);
				p.sendMessage(ChatColor.GRAY + "You have created the squad " + ChatColor.LIGHT_PURPLE + nameToMake);
				p.sendMessage(ChatColor.GRAY + "Type " + ChatColor.LIGHT_PURPLE + "/squad invite <name>" + ChatColor.GRAY + " to invite other members!");
			}else{
				p.sendMessage(ChatColor.GRAY + "The squad " + ChatColor.LIGHT_PURPLE + nameToMake + ChatColor.GRAY + " already exists.");
			}
		}
	}
}
