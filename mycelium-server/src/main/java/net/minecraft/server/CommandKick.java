package net.minecraft.server;

import java.util.List;

public class CommandKick extends CommandAbstract {

    public CommandKick() {}

    public String getCommand() {
        return "kick";
    }

    public int a() {
        return 3;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.kick.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length > 0 && astring[0].length() > 1) {
            EntityPlayer entityplayer = MinecraftServer.getServer().getPlayerList().getPlayer(astring[0]);
            String s = "Kicked by an operator.";
            boolean flag = false;

            if (entityplayer == null) {
                throw new ExceptionPlayerNotFound();
            } else {
                if (astring.length >= 2) {
                    s = a(icommandlistener, astring, 1).c();
                    flag = true;
                }

                entityplayer.playerConnection.disconnect(s);
                if (flag) {
                    a(icommandlistener, this, "commands.kick.success.reason", new Object[] { entityplayer.getName(), s});
                } else {
                    a(icommandlistener, this, "commands.kick.success", new Object[] { entityplayer.getName()});
                }

            }
        } else {
            throw new ExceptionUsage("commands.kick.usage", new Object[0]);
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length >= 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }
}
