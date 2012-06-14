package com.olympuspvp.caldabeast.CommandManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.olympuspvp.caldabeast.olySquad;

public class GoHome {
	static FileConfiguration config = olySquad.getFileConfig();
	public static void run(Player p){
		String name = p.getName();
		String squad = olySquad.getSquad(name);
		if(squad == null){
			p.sendMessage(ChatColor.GRAY + "You are not currently in a squad.");
		}else{

			int x = config.getInt("squads." + squad + ".home.x");
			int y = config.getInt("squads." + squad + ".home.y");
			int z = config.getInt("squads." + squad + ".home.z");
			String worldName = config.getString("squads." + squad + ".home.world");
			if(worldName == null){
				p.sendMessage(ChatColor.GRAY + "Your squad's owner has not yet set a home.");
			}else{
				World world = Bukkit.getWorld(worldName);
				Location home = new Location(world, x, y, z);
				p.teleport(home, TeleportCause.COMMAND);
				p.teleport(home, TeleportCause.COMMAND);
			}
		}
	}
}
