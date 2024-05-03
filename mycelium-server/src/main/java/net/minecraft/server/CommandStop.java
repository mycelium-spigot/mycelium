package net.minecraft.server;

public class CommandStop extends CommandAbstract {

    public CommandStop() {}

    public String getCommand() {
        return "stop";
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.stop.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (MinecraftServer.getServer().worldServer != null) {
            a(icommandlistener, this, "commands.stop.start", new Object[0]);
        }

        MinecraftServer.getServer().safeShutdown();
    }
}
