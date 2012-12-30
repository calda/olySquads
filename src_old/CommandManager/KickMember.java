package com.olympuspvp.caldabeast.CommandManager;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.olympuspvp.caldabeast.olySquad;

public class KickMember {

	public static void run(final Player p, final String toKick){
		final String name = p.getName();
		final String squad = olySquad.getSquad(name);
		if(squad == null){
			p.sendMessage(ChatColor.GRAY + "You are not currently in a squad.");
		}else{
			final String owner = olySquad.getOwner(squad);
			if (owner.equals(name)){

				final List<String> members = olySquad.getMembers(squad);
				String match = null;
				for(final String str : members){
					if(str.contains(toKick)){
						match = str;
						break;
					}
				}

				if(match != null){
					olySquad.squadCast(squad, name, " has kicked member " + ChatColor.LIGHT_PURPLE + match);
					olySquad.setSquad(match, null);
					olySquad.removeMember(squad, match);
				}else{
					p.sendMessage(ChatColor.GRAY + "The player " + ChatColor.LIGHT_PURPLE + toKick + ChatColor.GRAY + " is not in your squad.");
				}

			}else{
				p.sendMessage(ChatColor.GRAY + "You are not the owner of this squad.");
			}
		}

	}

}
