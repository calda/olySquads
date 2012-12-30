package com.olympuspvp.squads;

import java.util.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Squad{

	private final String name;
	private final String owner;
	private List<String> members;
	private Location home;
	private Location rally;
	private olySquads oly;

	public Squad(String name, String owner, List<String> members, Location home, Location rally, olySquads oly){
		this.name = name;
		this.owner = owner;
		this.members = members;
		this.home = home;
		this.oly = oly;
	}

	public String getName(){
		return name;
	}

	public String getOwner(){
		return owner;
	}

	public List<String> getMembers(){
		return members;
	}

	public Location getHome(){
		return home;
	}
	
	public void setHome(Location loc){
		this.home = loc;
	}

	public Location getRally(){
		return rally;
	}
	
	public void setRally(Location loc){
		this.rally = loc;
	}

	public List<String> addMember(String member){
		members.add("member");
		return members;
	}

	public List<String> removeMember(String member){
		if(members.contains(member)) members.remove(member);
		return members;
	}

	public boolean contains(String member){
		return members.contains(member);
	}

	public void save(){
		FileConfiguration config = oly.getConfig();
		config.set("Squads." + name + ".Owner", owner);
		config.set("Squads." + name + ".Members", members);
		config.set("Squads." + name + ".Home", oly.locToString(home));
		config.set("Squads." + name + ".Rally", oly.locToString(rally));
		oly.saveConfig();
	}

	public void delete(){
		FileConfiguration config = oly.getConfig();
		config.set("Squads." + name + ".Owner", null);
		config.set("Squads." + name + ".Members", null);
		config.set("Squads." + name + ".Home", null);
		config.set("Squads." + name + ".Rally", null);
		for(String s : members){
			oly.setPlayerSquad(s, name, false);
		}oly.saveConfig();
	}

	public void squadcast(String player, String message, boolean getColors){
		String name = player;
		player = ChatColor.GRAY + name;
		String sepColor = ChatColor.DARK_GRAY + "";
		if(getColors){
			player = oly.getChat().getName(name);
			sepColor = oly.getChat().getSeparatorColor(name);
			player = ChatColor.translateAlternateColorCodes('&', player);
			sepColor = ChatColor.translateAlternateColorCodes('&', sepColor);
		}if(name.equals(owner)) player = "*" + player;
		for(String s : members){
			Player p = Bukkit.getPlayerExact(s);
			if(p != null) p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + name + ChatColor.GOLD + "] " + ChatColor.DARK_GRAY + player + sepColor + ": " + ChatColor.GRAY + message);
		}
	}

}
