package com.olympuspvp.caldabeast.CommandManager;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.olympuspvp.caldabeast.olySquad;

public class ListSquads {

	public static void run(final Player p){
		final List<String> squads = olySquad.getSquads();
		final int numberOfSquads = squads.size();
		if(numberOfSquads != 0){
			p.sendMessage(ChatColor.GRAY + "There are currently " + ChatColor.LIGHT_PURPLE + numberOfSquads + ChatColor.GRAY + " squads on this server:");
			for(final String str : squads){
				final int members = olySquad.getMembers(str).size();
				final String owner = olySquad.getOwner(str);
				p.sendMessage(ChatColor.LIGHT_PURPLE + str + "" + ChatColor.DARK_GRAY + "(" + members + "/" + "5) " + ChatColor.GRAY + "Owned by " + ChatColor.DARK_PURPLE + owner);
			}
		}else{
			p.sendMessage(ChatColor.GRAY + "There are currently " + ChatColor.LIGHT_PURPLE + "0" + ChatColor.GRAY + " squads on this server. :(");
		}
	}
}
