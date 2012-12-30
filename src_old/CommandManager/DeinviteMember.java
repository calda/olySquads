package com.olympuspvp.caldabeast.CommandManager;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.olympuspvp.caldabeast.olySquad;

public class DeinviteMember {

	public static void run(final Player p){
		final String name = p.getName();
		final String squad = olySquad.getSquad(name);
		String owner;
		if(squad == null){
			p.sendMessage(ChatColor.GRAY + "You are not currently in a squad.");
		}else{
			owner = olySquad.getOwner(squad);
			if (owner.equals(name)){

				final String recInvite = olySquad.getRecentInvite(squad);
				olySquad.setRecentInvite(squad, null);
				final List<Player> matches = Bukkit.matchPlayer(recInvite);
				for(final Player plr : matches){
					olySquad.setInvite(recInvite, null);
					p.sendMessage(ChatColor.GRAY + "Your invitation to "+ ChatColor.LIGHT_PURPLE + plr.getName() + ChatColor.GRAY + " has been revoked.");
					plr.sendMessage(ChatColor.GRAY + "Your invitation to join " + ChatColor.LIGHT_PURPLE + squad + ChatColor.GRAY + " has been revoked.");
				}
			}else{
				p.sendMessage(ChatColor.GRAY + "You are not the owner of this squad.");
			}
		}

	}

}
