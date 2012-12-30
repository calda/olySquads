package com.olympuspvp.caldabeast.CommandManager;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import com.olympuspvp.caldabeast.olySquad;

public class InviteMember {
	static FileConfiguration config = olySquad.getFileConfig();
	public static void run(final Player p, final String nameToInvite){
		final String name = p.getName();
		final String squad = olySquad.getSquad(name);
		String owner;
		if(squad == null){
			p.sendMessage(ChatColor.GRAY + "You are not currently in a squad.");
		}else{
			owner = olySquad.getOwner(squad);
			if (owner.equals(name)){
				if(olySquad.getMembers(squad).size() != 5){
					olySquad.setRecentInvite(squad, nameToInvite);
					final List<Player> matches = Bukkit.matchPlayer(nameToInvite);
					for(final Player plr : matches){
						olySquad.setInvite(nameToInvite, squad);
						plr.sendMessage(ChatColor.GRAY + "You have been invited to join " + ChatColor.LIGHT_PURPLE + squad);
						p.sendMessage(ChatColor.GRAY + "You have been invited " + ChatColor.LIGHT_PURPLE + plr.getName() + ChatColor.GRAY + " to your squad.");
						plr.sendMessage(ChatColor.GRAY + "Type "+ ChatColor.LIGHT_PURPLE + "/squad accept " + ChatColor.GRAY + "to join the squad!");
					}
				}else{
					p.sendMessage(ChatColor.GRAY + "Your squad is currently full.");
				}
			}else{
				p.sendMessage(ChatColor.GRAY + "You are not the owner of this squad.");
			}
		}

	}

}
