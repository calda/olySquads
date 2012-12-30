package com.olympuspvp.squads;

import java.util.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager{

	olySquads squad;
	String squads = ChatColor.GOLD + "[" + ChatColor.YELLOW + "olySquads" + ChatColor.GOLD + "] " + ChatColor.GRAY;
	
	protected CommandManager(olySquads squad){
		this.squad = squad;
	}
	
	protected void squadCommand(CommandSender s, String[] args){
		
	}
	
	public boolean playerIsInSquad(Player p, boolean message){
		boolean test = squad.getPlayerSquad(p.getName()) == null;
		if(!test && message) p.sendMessage(squad + "You have to be in a squad to do that.");
		return test;
	}
	
	public boolean playerIsSquadOwner(Player p, boolean message){
		Squad s = squad.getPlayerSquad(p.getName());
		boolean test = s.getOwner().equals(p.getName());
		if(!test) p.sendMessage(squads + "You have to be the owner of the squad to do that.");
		return test;
	}
	
	
	
	/*  /s create [name]   */
	protected void createSquadCommand(Player p, String[] args){
		if(playerIsInSquad(p, false)){
			p.sendMessage(squad + "You have to leave your current squad to do that.");
			return;
		}
		if(args.length == 2){
			String name = args[1];
			Squad s = new Squad(name, p.getName(), new ArrayList<String>(), null, null, squad);
			s.save();
			squad.loadSquad(name, s);
			p.sendMessage(squads + "You created the squad " + ChatColor.DARK_GRAY + name);
			p.sendMessage(squads + "Invite people to it using the command " + ChatColor.DARK_GRAY + "/s invite [name]");
			Bukkit.getServer().broadcastMessage(squads + "The squad " + ChatColor.DARK_GRAY + name + ChatColor.GRAY + "has been established by " + ChatColor.DARK_GRAY + p.getName());
		}else p.sendMessage(squads + "Incorrect Usage. /s create [name]");
	}
	
	/*  /s delete      */
	private List<String> deleteRequests = new ArrayList<String>();
	protected void deleteSquadCommand(Player p){
		if(!playerIsInSquad(p, true)) return;
		if(!playerIsSquadOwner(p, true)) return;
		if(!deleteRequests.contains(p.getName())){
			p.sendMessage(squad + "This operation cannot be undone.");
			p.sendMessage(squad + "Are you sure you would like to delete your squad?");
			p.sendMessage(squad + "Retype the command to confirm.");
			deleteRequests.add(p.getName());
		}else{
			Squad s = squad.getPlayerSquad(p.getName());
			s.squadcast("olySquads", "The squad has been disbanded by " + ChatColor.DARK_GRAY + s.getOwner(), false);
			s.delete();
			deleteRequests.remove(p.getName());
		}
	}
	
	/*  /s invite [name]   */
	private HashMap<String, String> invites = new HashMap<String, String>();
	protected void invitePlayerCommand(Player p, String[] args){
		if(!playerIsInSquad(p, true)) return;
		if(!playerIsSquadOwner(p, true)) return;
		if(args.length != 2){
			p.sendMessage(squads + "Incorrect Usage. /s invite [name]");
			return;
		}List<Player> matches = Bukkit.matchPlayer(args[1]); 
		if(matches.size() < 1){
			p.sendMessage(squads + "Found multiple people with that in their name. Be more specific.");
			return;
		}if(matches.size() == 0){
			p.sendMessage(squads + "Could not find that player.");
			return;
		}Player match = matches.get(1);
		Squad s = squad.getPlayerSquad(p.getName());
		match.sendMessage(squads + ChatColor.DARK_GRAY + p.getName() + ChatColor.GRAY + " has invited you to join " + s.getName());
		match.sendMessage(squads + ChatColor.DARK_GRAY + "/s accept" + ChatColor.GRAY + " to accept the invite.");
		invites.put(match.getName(), s.getName());
		s.squadcast("olyChat", ChatColor.DARK_GRAY + match.getName() + ChatColor.GRAY + " has been invited to the squad.", false);
	}
	
	/*   /s accept   */
	protected void acceptInviteCommand(Player p){
		if(!invites.containsKey(p.getName())){
			p.sendMessage(squads + "You do not have a pending request.");
			return;
		}if(playerIsInSquad(p,false)){
			p.sendMessage(squads + "You must leave your current squad to join another.");
			return;
		}String squadName = invites.get(p.getName());
		invites.remove(p.getName());
		Squad s = squad.getSquad(squadName);
		s.addMember(p.getName());
		squad.setPlayerSquad(p.getName(), squadName, true);
		s.squadcast("olyChat", ChatColor.DARK_GRAY + p.getName() + ChatColor.GRAY + " has been joined the squad.", false);
	}
	
	/*   /s sethome   */
	protected void setHomeCommand(Player p){
		if(!playerIsInSquad(p, true)) return;
		if(!playerIsSquadOwner(p, true)) return;
		Squad s = squad.getPlayerSquad(p.getName());
		s.setHome(p.getLocation());
		s.save();
		s.squadcast("olySquads", ChatColor.DARK_GRAY + p.getName() + ChatColor.GRAY + " set the squad home.", false);
		s.squadcast("olySquads", ChatColor.DARK_GRAY + "/s home" + ChatColor.GRAY + " to go there.", false);
	}
	
	/*   /s setrally  */
	protected void setRallyCommand(Player p){
		if(!playerIsInSquad(p, true)) return;
		Squad s = squad.getPlayerSquad(p.getName());
		s.setRally(p.getLocation());
		s.save();
		s.squadcast("olySquads", ChatColor.DARK_GRAY + p.getName() + ChatColor.GRAY + " set the squad's rally point.", false);
		s.squadcast("olySquads", ChatColor.DARK_GRAY + "/s rally" + ChatColor.GRAY + " to go there.", false);
	}

	/*   /s home   */
	protected void homeCommand(Player p){
		if(!playerIsInSquad(p, true)) return;
		Squad s = squad.getPlayerSquad(p.getName());
		Location home = s.getHome();
	}
	
	
	
}
