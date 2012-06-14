package com.olympuspvp.caldabeast;
import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.olympuspvp.caldabeast.CommandManager.*;


public class olySquad extends JavaPlugin{

	private static FileConfiguration config;
	private static File configFile = new File("plugns" + File.separator + "olySquads" + File.separator + "config.yml");
	private static boolean configIsLoaded = false;
	
	@Override
	public void onDisable(){
		System.out.println("[olySquads] olySquads has been disabled");
		saveFileConfig();
	}@Override
	public void onEnable(){
		PVPListen pvp = new PVPListen();
		Bukkit.getServer().getPluginManager().registerEvents(pvp, this);
		System.out.println("[olySquads] olySquads is now enabled! Loading config file...");
		if(!configFile.exists()){
			configFile.mkdir();
			config = this.getConfig();
			config.set("configCreated", true);
			saveFileConfig();
		}loadFileConfig();
	}
	
	public boolean onCommand(CommandSender s, Command cc, String cl, String[] args){
		CommandManager.onCommand(s, cl, args, this);
		return true;
	}
	
	//PLAYER METHODS
	//==============
	public static String getSquad(String name){
		String squad = config.getString("users." + name + ".squad");
		return squad;
	}public static void setSquad(String name, String newSquad){
		config.set("users." + name + ".squad", newSquad);
	}
	
	public static String getInvite(String name){
		String squad = config.getString("users." + name + ".invite");
		return squad;
	}public static void setInvite(String name, String invitedTo){
		config.set("users." + name + ".invite", invitedTo);
	}
	//SQUAD METHODS
	//=============
	public static void squadCast(String squadName, String sender, String message){
		for(String str : getMembers(squadName)){
			 for(Player p : Bukkit.matchPlayer(str)){
				 p.sendMessage(ChatColor.LIGHT_PURPLE +"[" + squadName + "] " + ChatColor.DARK_PURPLE + sender + ChatColor.LIGHT_PURPLE + ": " + ChatColor.GREEN + message);
			 }
		}
	}
	
	public static String getOwner(String squadName){
		String squadOwner = config.getString("squads." + squadName + ".owner");
		return squadOwner;
	}public static void setOwner(String squadName, String newOwner){
		config.set("squads." + squadName + ".owner", newOwner);
	}
	
	public static void addSquad(String squadName){
		List<String> squads = getSquads();
		squads.add(squadName);
		setSquads(squads);
	}public static void removeSquad(String squadName){
		List<String> squads = getSquads();
		squads.remove(squadName);
		setSquads(squads);
	}public static List<String> getSquads(){
		List<String> squads = config.getStringList("squadsList");
		return squads;
	}public static void setSquads(List<String> newSquads){
		config.set("squadsList", newSquads);
	}
	
	public static void addMember(String squadName, String nameToAdd){
		List<String> players = getMembers(squadName);
		players.add(nameToAdd);
		setMembers(squadName, players);
	}public static void removeMember(String squadName, String nameToRemove){
		List<String> players = getMembers(squadName);
		players.remove(nameToRemove);
		setMembers(squadName, players);
		if(players.isEmpty()){
			DeleteFaction.run(((Player) Bukkit.matchPlayer(nameToRemove)));
		}
		setSquad(nameToRemove, null);
	}public static List<String> getMembers(String squadName){
		List<String> players = config.getStringList("squads." + squadName + ".members");
		return players;
	}public static void setMembers(String squadName, List<String> players){
		config.set("squads." + squadName + ".members", players);
	}
	
	public static void setRecentInvite(String squadName, String recInvite){
		config.set("squads." + squadName + ".recentInvite", recInvite);
		for(Player p : Bukkit.getOnlinePlayers()){
			String name = p.getName();
			setInvite(name, squadName);
		}
	}public static String getRecentInvite(String squadName){
		String recInvite = config.getString("squads." + squadName + ".recentInvite");
		return recInvite;
	}
	
	//File Configuration Methods
	public static FileConfiguration getFileConfig(){
		return config;
	}public void saveFileConfig(){
		saveConfig();
	}public static void loadFileConfig(){
		try{
			config.load(configFile);
			configIsLoaded = true;
		}catch(Exception e){}
	}public static boolean isConfigLoaded(){
		return configIsLoaded;
	}
	
}
