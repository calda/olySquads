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
import com.olympuspvp.caldabeast.CommandManager.CommandManager;
import com.olympuspvp.caldabeast.CommandManager.DeleteFaction;


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
		final PVPListen pvp = new PVPListen();
		Bukkit.getServer().getPluginManager().registerEvents(pvp, this);
		System.out.println("[olySquads] olySquads is now enabled! Loading config file...");
		if(!configFile.exists()){
			configFile.mkdir();
			config = this.getConfig();
			config.set("configCreated", true);
			saveFileConfig();
		}loadFileConfig();
	}

	@Override
	public boolean onCommand(final CommandSender s, final Command cc, final String cl, final String[] args){
		CommandManager.onCommand(s, cl, args, this);
		return true;
	}

	//PLAYER METHODS
	//==============
	public static String getSquad(final String name){
		final String squad = config.getString("users." + name + ".squad");
		return squad;
	}public static void setSquad(final String name, final String newSquad){
		config.set("users." + name + ".squad", newSquad);
	}

	public static String getInvite(final String name){
		final String squad = config.getString("users." + name + ".invite");
		return squad;
	}public static void setInvite(final String name, final String invitedTo){
		config.set("users." + name + ".invite", invitedTo);
	}
	//SQUAD METHODS
	//=============
	public static void squadCast(final String squadName, final String sender, final String message){
		for(final String str : getMembers(squadName)){
			for(final Player p : Bukkit.matchPlayer(str)){
				p.sendMessage(ChatColor.LIGHT_PURPLE +"[" + squadName + "] " + ChatColor.DARK_PURPLE + sender + ChatColor.LIGHT_PURPLE + ": " + ChatColor.GREEN + message);
			}
		}
	}

	public static String getOwner(final String squadName){
		final String squadOwner = config.getString("squads." + squadName + ".owner");
		return squadOwner;
	}public static void setOwner(final String squadName, final String newOwner){
		config.set("squads." + squadName + ".owner", newOwner);
	}

	public static void addSquad(final String squadName){
		final List<String> squads = getSquads();
		squads.add(squadName);
		setSquads(squads);
	}public static void removeSquad(final String squadName){
		final List<String> squads = getSquads();
		squads.remove(squadName);
		setSquads(squads);
	}public static List<String> getSquads(){
		final List<String> squads = config.getStringList("squadsList");
		return squads;
	}public static void setSquads(final List<String> newSquads){
		config.set("squadsList", newSquads);
	}

	public static void addMember(final String squadName, final String nameToAdd){
		final List<String> players = getMembers(squadName);
		players.add(nameToAdd);
		setMembers(squadName, players);
	}public static void removeMember(final String squadName, final String nameToRemove){
		final List<String> players = getMembers(squadName);
		players.remove(nameToRemove);
		setMembers(squadName, players);
		if(players.isEmpty()){
			DeleteFaction.run(((Player) Bukkit.matchPlayer(nameToRemove)));
		}
		setSquad(nameToRemove, null);
	}public static List<String> getMembers(final String squadName){
		final List<String> players = config.getStringList("squads." + squadName + ".members");
		return players;
	}public static void setMembers(final String squadName, final List<String> players){
		config.set("squads." + squadName + ".members", players);
	}

	public static void setRecentInvite(final String squadName, final String recInvite){
		config.set("squads." + squadName + ".recentInvite", recInvite);
		for(final Player p : Bukkit.getOnlinePlayers()){
			final String name = p.getName();
			setInvite(name, squadName);
		}
	}public static String getRecentInvite(final String squadName){
		final String recInvite = config.getString("squads." + squadName + ".recentInvite");
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
		}catch(final Exception e){}
	}public static boolean isConfigLoaded(){
		return configIsLoaded;
	}

}
