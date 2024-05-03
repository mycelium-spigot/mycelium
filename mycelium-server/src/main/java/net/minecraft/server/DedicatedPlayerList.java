package net.minecraft.server;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DedicatedPlayerList extends PlayerList {

    private static final Logger f = LogManager.getLogger();

    public DedicatedPlayerList(DedicatedServer dedicatedserver) {
        super(dedicatedserver);
        this.a(dedicatedserver.a("view-distance", 10));
        this.maxPlayers = dedicatedserver.a("max-players", 20);

        this.A();
        this.B();

    }

    public void addOp(GameProfile gameprofile) {
        super.addOp(gameprofile);
        this.B();
    }

    public void removeOp(GameProfile gameprofile) {
        super.removeOp(gameprofile);
        this.B();
    }

    private void A() {
        try {
            this.getOPs().load();
        } catch (Exception exception) {
            DedicatedPlayerList.f.warn("Failed to load operators list: ", exception);
        }

    }

    private void B() {
        try {
            this.getOPs().save();
        } catch (Exception exception) {
            DedicatedPlayerList.f.warn("Failed to save operators list: ", exception);
        }

    }

    public DedicatedServer getServer() {
        return (DedicatedServer) super.getServer();
    }

    public boolean f(GameProfile gameprofile) {
        return this.getOPs().b(gameprofile);
    }
}
