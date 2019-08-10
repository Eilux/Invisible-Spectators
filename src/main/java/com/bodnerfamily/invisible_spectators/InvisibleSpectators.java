package com.bodnerfamily.invisible_spectators;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import com.bodnerfamily.invisible_spectators.commands.SpectatorsVisible;

public final class InvisibleSpectators extends JavaPlugin implements StateToggle {

    public InvisibleSpectators() {
    }

    private volatile boolean stateDidChange = false;
    private volatile boolean state = false;

    private void tellConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    public void tellChat(String chatMessage) {
        getServer().broadcastMessage(chatMessage);
    }

    @Override
    public void onEnable() {
        //tells the console the plugin is working
        tellConsole("Invisible spectators is now running.");

        //event scheduler
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	if (stateDidChange) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.getGameMode() == GameMode.SPECTATOR) {
							for (Player pl : Bukkit.getOnlinePlayers()) {
								if (state) {
									pl.showPlayer(InvisibleSpectators.this, p);
								} else {
									pl.hidePlayer(InvisibleSpectators.this, p);
								}
							}
						}
					}
					stateDidChange = false;
				}
            }
        }, 0L, 1L);
        //registers command
        this.getCommand("spectatorsvisible").setExecutor(new SpectatorsVisible(this));
    }


    @Override
    public void onDisable() {
        tellConsole("Invisible spectators disabled.");
    }

	@Override
	public void setState(boolean state) {
		this.state = state;
		this.stateDidChange = true;
	}
}
