package net.minecraft.server;

public class BiomeTheEndDecorator extends BiomeDecorator {

    public BiomeTheEndDecorator() {}

    protected void a(BiomeBase biomebase) {

        if (this.c.getX() == 0 && this.c.getZ() == 0) {
            EntityEnderDragon entityenderdragon = new EntityEnderDragon(this.a);

            entityenderdragon.setPositionRotation(0.0D, 128.0D, 0.0D, this.b.nextFloat() * 360.0F, 0.0F);
            this.a.addEntity(entityenderdragon, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.CHUNK_GEN); // CraftBukkit - add SpawnReason
        }
    }
}