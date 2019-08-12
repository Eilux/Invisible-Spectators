package com.bodnerfamily.invisible_spectators.commands;

import com.bodnerfamily.invisible_spectators.StateToggle;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SpectatorsVisible implements CommandExecutor {

    private final StateToggle toggle;

    public SpectatorsVisible(StateToggle toggle) {
        this.toggle = toggle;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && !sender.isOp()){
            sender.sendMessage(ChatColor.RED + "You don't have permission. Must be OP");
            return false;
        }

        else {
            if (args == null || args.length == 0) {
                sender.sendMessage(ChatColor.RED + "No argument provided. Only 'true' and 'false' are valid.");
                return false;
            }
            if (args[0].equalsIgnoreCase("true")) {
                sender.sendMessage(ChatColor.AQUA + "Spectators are now visible.");
                this.toggle.setState(false);

                return true;
            } else if (args[0].equalsIgnoreCase("false")) {
                sender.sendMessage(ChatColor.AQUA + "Spectators are now invisible.");
                this.toggle.setState(true);
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid argument " + args[0] + ". Only 'true' and 'false' are valid.");
                return false;
            }
        }
    }

}