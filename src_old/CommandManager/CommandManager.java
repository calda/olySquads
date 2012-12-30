package com.olympuspvp.caldabeast.CommandManager;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.olympuspvp.caldabeast.olySquad;

public class CommandManager {
	public static void onCommand(final CommandSender s, final String cl, final String[] args, final olySquad os){
		if(cl.equalsIgnoreCase("s") || cl.equalsIgnoreCase("squad")){
			if(s instanceof Player){

				final Player p = (Player) s;
				if(args.length == 0){
					incorrectUsage(s);
				}else{
					//SQUAD CREATE
					if(args[0].equalsIgnoreCase("create")){
						if(args.length == 2){
							CreateFaction.run(p, args[1]);
							os.saveConfig();
						}else{
							incorrectUsage(s);
						}
					}

					//SQUAD HELP
					else if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")){
						CommandHelp.run(p, args);
					}

					//SQUAD DELETE
					else if(args[0].equalsIgnoreCase("disband") || args[0].equalsIgnoreCase("delete")){
						DeleteFaction.run(p);
						os.saveConfig();
					}

					//SQUAD INVITE
					else if(args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("add")){
						if(args.length == 2){
							InviteMember.run(p, args[1]);
							os.saveConfig();
						}else{
							incorrectUsage(s);
						}
					}

					//SQUAD DEINVITE
					else if(args[0].equalsIgnoreCase("deinvite") || args[0].equalsIgnoreCase("uninvite")){
						DeinviteMember.run(p);
						os.saveConfig();
					}

					//SQUAD ACCEPT
					else if(args[0].equalsIgnoreCase("accept")){
						AcceptInvite.run(p);
						os.saveConfig();
					}

					//SQUAD ACCEPT
					else if(args[0].equalsIgnoreCase("info")){
						final String squad = olySquad.getSquad(p.getName());
						if(squad == null){
							p.sendMessage(ChatColor.GRAY + "You are not in a squad");
						}else{
							p.sendMessage(ChatColor.DARK_GRAY + "Your current squad is " + ChatColor.LIGHT_PURPLE + squad);
							p.sendMessage(ChatColor.GRAY + "Current Members:");
							p.sendMessage(ChatColor.GRAY + "===============");
							final List<String> members = olySquad.getMembers(squad);
							for(final String str : members){
								final Player oMember = Bukkit.getPlayer(str);
								if(oMember != null){
									if(olySquad.getOwner(olySquad.getSquad(str)).equals(str)){
										p.sendMessage(ChatColor.DARK_PURPLE + str + ChatColor.LIGHT_PURPLE + ": " + ChatColor.GRAY + "Current Health: " +ChatColor.GREEN +  (oMember.getHealth()*5) + "%");
									}else{
										p.sendMessage(ChatColor.LIGHT_PURPLE + str + ChatColor.DARK_PURPLE + ": " + ChatColor.GRAY + "Current Health: " +ChatColor.GREEN +  (oMember.getHealth()*5) + "%");
									}
								}else{
									p.sendMessage(ChatColor.LIGHT_PURPLE + str + ChatColor.DARK_PURPLE + ": " + ChatColor.GRAY + "Currently Offline");
								}
							}
						}
					}

					//SQUAD LEAVE
					else if(args[0].equalsIgnoreCase("leave")){
						LeaveSquad.run(p);
						os.saveConfig();
					}

					//SQUADCAST
					else if(args[0].equalsIgnoreCase("message") || args[0].equalsIgnoreCase("m") ){
						final String squad = olySquad.getSquad(p.getName());
						if(squad == null){
							p.sendMessage(ChatColor.GRAY + "You are not currently in a squad.");
						}else{
							final StringBuilder sb = new StringBuilder();
							for(final String str : args){
								if(!str.equals("m") && !str.equals("message")){
									sb.append(str + " ");}
							}olySquad.squadCast(squad, p.getName(), sb.toString());
						}
					}

					//KICK MEMBER
					else if(args[0].equalsIgnoreCase("kick")){
						if(args.length == 2){
							KickMember.run(p, args[1]);
							os.saveFileConfig();
						}else{
							incorrectUsage(s);
						}
					}

					//LIST SQUADS
					else if(args[0].equalsIgnoreCase("list")){
						ListSquads.run(p);
					}

					else if(args[0].equalsIgnoreCase("home")){
						GoHome.run(p);
					}

					else if(args[0].equalsIgnoreCase("sethome")){
						SetHome.run(p);
						os.saveConfig();
					}

					else if(args[0].equalsIgnoreCase("setrally")){
						SetRally.run(p);
						os.saveConfig();
					}

					else if(args[0].equalsIgnoreCase("rally")){
						GoRally.run(p);
					}

					else{
						incorrectUsage(s);
					}
				}
			}else{
				s.sendMessage("This must be ran from ingame.");
			}
		}
	}

	private static void incorrectUsage(final CommandSender p){
		p.sendMessage(ChatColor.GRAY + "Incorrect command usage. \nType /squad help for usage guide.");
	}
}
