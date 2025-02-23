package com.elysia.elysiarecycle.command.subcommands;

import com.elysia.elysiarecycle.ElysiaRecycle;
import org.bukkit.command.CommandSender;

public class OpenCommand implements ISubCommand{
    @Override
    public String getName() {
        return "open";
    }

    @Override
    public String[] getAliases() {
        return new String[]{"o"};
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 2) {
            String playerName = args[1];
            ElysiaRecycle.getGuiManager().openGui(playerName);
        }
    }
}
