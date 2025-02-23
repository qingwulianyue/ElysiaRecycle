package com.elysia.elysiarecycle.command.subcommands;

import org.bukkit.command.CommandSender;

public class HelpCommand implements ISubCommand{
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String[] getAliases() {
        return new String[]{"help"};
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("/ElysiaRecycle help   -   获取帮助");
        sender.sendMessage("/ElysiaRecycle reload   -   重载插件");
        sender.sendMessage("/ElysiaRecycle open {player}   -   为玩家打开回收界面");
    }
}
