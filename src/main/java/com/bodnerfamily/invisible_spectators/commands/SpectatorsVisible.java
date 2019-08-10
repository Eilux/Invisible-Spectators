package com.bodnerfamily.invisible_spectators.commands;

import com.bodnerfamily.invisible_spectators.StateToggle;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class SpectatorsVisible implements CommandExecutor {

    private final StateToggle toggle;

    public SpectatorsVisible(StateToggle toggle) {
        this.toggle = toggle;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("true")) {
            sender.sendMessage(ChatColor.AQUA + "Spectators are now visible.");
            this.toggle.setState(true);
            return true;
        } else if (args[0].equalsIgnoreCase("false")) {
            sender.sendMessage(ChatColor.AQUA + "Spectators are now invisible.");
            this.toggle.setState(false);
            return true;
        } else {
			sender.sendMessage(ChatColor.RED + "Invalid argument " + args[0] + ". Only 'true' and 'false' are valid.");
			return false;
        }
    }

}