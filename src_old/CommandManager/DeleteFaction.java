package com.olympuspvp.caldabeast.CommandManager;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import com.olympuspvp.caldabeast.olySquad;

public class DeleteFaction {
	static FileConfiguration config = olySquad.getFileConfig();
	public static void run(final Player p){
		final String name = p.getName();
		final String squad = olySquad.getSquad(name);
		String owner;
		if(squad == null){
			p.sendMessage(ChatColor.GRAY + "You are not currently in a squad.");
		}else{
			owner = olySquad.getOwner(squad);
			if (owner.equals(name)){

				//OWNER
				olySquad.squadCast(squad, name, " has disbanded your squad.");
				olySquad.removeSquad(squad);
				olySquad.setOwner(squad, null);
				final List<String> members = olySquad.getMembers(squad);
				for(final String str : members){olySquad.setSquad(str, null);}
				olySquad.setMembers(squad, null);
				olySquad.setRecentInvite(squad, null);
				config.set("squads." + squad + ".rally.x", null);
				config.set("squads." + squad + ".rally.y", null);
				config.set("squads." + squad + ".rally.z", null);
				config.set("squads." + squad + ".rally.world", null);
				config.set("squads." + squad + ".home.x", null);
				config.set("squads." + squad + ".home.y", null);
				config.set("squads." + squad + ".home.z", null);
				config.set("squads." + squad + ".home.world", null);

			}else{
				p.sendMessage(ChatColor.GRAY + "You are not the owner of this squad.");
			}
		}

	}

}
