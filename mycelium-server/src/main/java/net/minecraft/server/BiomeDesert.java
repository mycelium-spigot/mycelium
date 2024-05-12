package net.minecraft.server;

import java.util.Random;

public class BiomeDesert extends BiomeBase {

    public BiomeDesert(int i) {
        super(i);
        this.au.clear();
        this.ak = Blocks.SAND.getBlockData();
        this.al = Blocks.SAND.getBlockData();
        this.as.A = -999;
        this.as.D = 2;
        this.as.F = 50;
        this.as.G = 10;
        this.au.clear();
    }

    public void a(World world, Random random, BlockPosition blockposition) {
        super.a(world, random, blockposition);
    }
}
