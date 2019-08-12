package com.bodnerfamily.invisible_spectators;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import com.bodnerfamily.invisible_spectators.commands.SpectatorsVisible;

public final class InvisibleSpectators extends JavaPlugin implements StateToggle {


    private static final String SPECTATORS_INVISIBLE = "spectators_invisible";

    public InvisibleSpectators() {
    }

    private volatile boolean state = this.getConfig().getBoolean(SPECTATORS_INVISIBLE);

    private void tellConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    public void tellChat(String chatMessage) {
        getServer().broadcastMessage(chatMessage);
    }


    @Override
    public void setState(boolean state) {
        this.state = state;
        this.getConfig().set(SPECTATORS_INVISIBLE, this.state);
        this.saveConfig();
    }




    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        //tells the console the plugin is working
        tellConsole("Invisible spectators is now running.");
        //event scheduler
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (state) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getGameMode() != GameMode.SPECTATOR) {
                            for (Player pk : Bukkit.getOnlinePlayers()) {
                                pk.showPlayer(InvisibleSpectators.this, p);
                            }
                        }
                        if (p.getGameMode() == GameMode.SPECTATOR) {
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                pl.hidePlayer(InvisibleSpectators.this, p);
                            }
                        }
                    }
                } else {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        for (Player pk : Bukkit.getOnlinePlayers()) {
                            pk.showPlayer(InvisibleSpectators.this, p);
                        }

                    }

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
}
