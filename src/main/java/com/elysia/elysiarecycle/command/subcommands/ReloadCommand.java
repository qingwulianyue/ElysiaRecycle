package com.elysia.elysiarecycle.command.subcommands;

import com.elysia.elysiarecycle.ElysiaRecycle;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements ISubCommand{
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String[] getAliases() {
        return new String[]{"reload"};
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ElysiaRecycle.getConfigManager().load();
        sender.sendMessage("重载成功");
    }
}
