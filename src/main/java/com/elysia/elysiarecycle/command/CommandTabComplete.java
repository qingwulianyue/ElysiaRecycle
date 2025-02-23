package com.elysia.elysiarecycle.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> subCommands = new ArrayList<>();
        if (strings.length == 1) {
            if (strings[0].startsWith("h"))
                subCommands.add("help");
            else if (strings[0].startsWith("r"))
                subCommands.add("reload");
            else if (strings[0].startsWith("p"))
                subCommands.add("open");
            else {
                subCommands.add("help");
                subCommands.add("reload");
                subCommands.add("open");
            }
        }
        else if (strings.length == 2){
            for (Player player : Bukkit.getOnlinePlayers())
                subCommands.add(player.getName());
        }
        return subCommands;
    }
}
