package com.elysia.elysiarecycle.command;

import com.elysia.elysiarecycle.command.subcommands.HelpCommand;
import com.elysia.elysiarecycle.command.subcommands.ISubCommand;
import com.elysia.elysiarecycle.command.subcommands.SubCommandManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0){
            ISubCommand iSubCommand = SubCommandManager.get(HelpCommand.class);
            iSubCommand.execute(commandSender, strings);
            return true;
        }
        String subCommand = strings[0];
        ISubCommand iSubCommand = SubCommandManager.get(subCommand);
        if (iSubCommand == null)
            iSubCommand = SubCommandManager.get(HelpCommand.class);
        iSubCommand.execute(commandSender, strings);
        return true;
    }
}
