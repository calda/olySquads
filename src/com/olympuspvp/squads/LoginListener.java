package com.olympuspvp.squads;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener{

	final olySquads squads;
	
	protected LoginListener(olySquads olysquads){
		squads = olysquads;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerLoginEvent e){
		String name = e.getPlayer().getName();
		squads.loadData(name);
		System.out.println("[olySquads] Loaded olySquads configuration for user " + name);
	}
	
}
