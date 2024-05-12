package net.minecraft.server;

import java.util.Random;

public class BiomeIcePlains extends BiomeBase {

    public BiomeIcePlains(int i, boolean flag) {
        super(i);

        if (flag) {
            this.ak = Blocks.SNOW.getBlockData();
        }

        this.au.clear();
    }

    public WorldGenTreeAbstract a(Random random) {
        return new WorldGenTaiga2(false);
    }

    protected BiomeBase d(int i) {
        BiomeBase biomebase = (new BiomeIcePlains(i, true)).a(13828095, true).a(this.ah + " Spikes").c().a(0.0F, 0.5F).a(new BiomeBase.BiomeTemperature(this.an + 0.1F, this.ao + 0.1F));

        biomebase.an = this.an + 0.3F;
        biomebase.ao = this.ao + 0.4F;
        return biomebase;
    }
}
