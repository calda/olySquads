package com.olympuspvp.caldabeast.CommandManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.olympuspvp.caldabeast.olySquad;

public class GoRally {
	static FileConfiguration config = olySquad.getFileConfig();
	public static void run(Player p){
		String name = p.getName();
		String squad = olySquad.getSquad(name);
		if(squad == null){
			p.sendMessage(ChatColor.GRAY + "You are not currently in a squad.");
		}else{

			int x = config.getInt("squads." + squad + ".rally.x");
			int y = config.getInt("squads." + squad + ".rally.y");
			int z = config.getInt("squads." + squad + ".rally.z");
			String worldName = config.getString("squads." + squad + ".rally.world");
			if(worldName == null){
				p.sendMessage(ChatColor.GRAY + "Your squad does not have a defined rally.");
			}else{
				World world = Bukkit.getWorld(worldName);
				Location rally = new Location(world, x, y, z);
				p.teleport(rally, TeleportCause.COMMAND);
				p.teleport(rally, TeleportCause.COMMAND);
			}
		}
	}
}
