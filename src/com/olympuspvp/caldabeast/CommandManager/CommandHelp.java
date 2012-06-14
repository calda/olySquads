package com.olympuspvp.caldabeast.CommandManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandHelp {
	
	public static void run(Player p, String[] args){
		page1(p);
		page2(p);
	}
	
	public static void page1(Player p){
		p.sendMessage("Help Page for " + ChatColor.LIGHT_PURPLE + "olySquads");
		p.sendMessage("All commands start with /squad or /s");
		p.sendMessage(ChatColor.LIGHT_PURPLE + "info" + ChatColor.GRAY + " Gives information on your current squad");
		p.sendMessage(ChatColor.LIGHT_PURPLE + "list" + ChatColor.GRAY + " Lists all teams on the server");
		p.sendMessage(ChatColor.LIGHT_PURPLE + "create <name>" + ChatColor.GRAY + " Create a new squad");
		p.sendMessage(ChatColor.LIGHT_PURPLE + "disband" + ChatColor.GRAY + " Disband your squad");
		p.sendMessage(ChatColor.LIGHT_PURPLE + "invite <player>" + ChatColor.GRAY + " Invite a player to your squad");
		p.sendMessage(ChatColor.LIGHT_PURPLE + "uninvite" + ChatColor.GRAY + " Cancel any outgoing invitation");
		p.sendMessage(ChatColor.LIGHT_PURPLE + "accept" + ChatColor.GRAY + " Accept an invitation");
	}
	
	public static void page2(Player p){
		p.sendMessage(ChatColor.LIGHT_PURPLE + "home" + ChatColor.GRAY + " Goes to your squad's home");
		p.sendMessage(ChatColor.LIGHT_PURPLE + "rally" + ChatColor.GRAY + " Goes to your squad's rally");
		p.sendMessage(ChatColor.LIGHT_PURPLE + "sethome" + ChatColor.GRAY + " Sets your team's home");
		p.sendMessage(ChatColor.LIGHT_PURPLE + "setrally" + ChatColor.GRAY + " Sets your team's rally");
	}
	
}
