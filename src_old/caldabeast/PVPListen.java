package com.olympuspvp.caldabeast;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PVPListen implements Listener{

	@EventHandler
	public void onPlayerDamage(final EntityDamageByEntityEvent e){
		final Entity ent1 = e.getDamager();
		final Entity ent2 = e.getEntity();
		if((ent1 instanceof Player) && (ent2 instanceof Player)){
			final Player p1 = (Player) ent1;
			final Player p2 = (Player) ent2;
			final String s1 = olySquad.getSquad(p1.getName());
			final String s2 = olySquad.getSquad(p2.getName());
			if((s1 != null) && (s1 != null)){
				if(s1.equals(s2)){
					e.setCancelled(true);
					p1.sendMessage(ChatColor.GRAY + "You cannot damage members of your squad, you traitor!");
				}
			}
		}else if((ent1 instanceof Arrow) && (ent2 instanceof Player)){
			final Entity shooter = ((Arrow)ent1).getShooter();
			if(shooter instanceof Player){
				final Player p1 = (Player) shooter;
				final Player p2 = (Player) ent2;
				if(p1.equals(p2)){
					final String s1 = olySquad.getSquad(p1.getName());
					final String s2 = olySquad.getSquad(p2.getName());
					if((s1 != null) && (s1 != null)){
						if(s1.equals(s2)){
							e.setCancelled(true);
							p1.sendMessage(ChatColor.GRAY + "You cannot damage members of your squad, you traitor!");
						}
					}
				}
			}
		}
	}

}
