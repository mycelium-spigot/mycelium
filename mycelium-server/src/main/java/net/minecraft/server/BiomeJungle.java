package net.minecraft.server;

import java.util.Random;

public class BiomeJungle extends BiomeBase {

    private boolean aD;
    private static final IBlockData aE = Blocks.LOG.getBlockData().set(BlockLog1.VARIANT, BlockWood.EnumLogVariant.JUNGLE);
    private static final IBlockData aF = Blocks.LEAVES.getBlockData().set(BlockLeaves1.VARIANT, BlockWood.EnumLogVariant.JUNGLE).set(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    private static final IBlockData aG = Blocks.LEAVES.getBlockData().set(BlockLeaves1.VARIANT, BlockWood.EnumLogVariant.OAK).set(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));

    public BiomeJungle(int i, boolean flag) {
        super(i);
        this.aD = flag;
        if (flag) {
            this.as.A = 2;
        } else {
            this.as.A = 50;
        }

        this.as.C = 25;
        this.as.B = 4;
        if (!flag) {
            this.at.add(new BiomeBase.BiomeMeta(EntityOcelot.class, 2, 1, 1));
        }

        this.au.add(new BiomeBase.BiomeMeta(EntityChicken.class, 10, 4, 4));
    }

    public WorldGenTreeAbstract a(Random random) {
        if (random.nextInt(10) == 0) {
            return (WorldGenTreeAbstract) this.aB;
        } else {
            if (!this.aD && random.nextInt(3) == 0) {
                return new WorldGenJungleTree(false, 10, 20, BiomeJungle.aE, BiomeJungle.aF);
            } else {
                return new WorldGenTrees(false, 4 + random.nextInt(7), BiomeJungle.aE, BiomeJungle.aF, true);
            }
        }
    }

    public void a(World world, Random random, BlockPosition blockposition) {
        super.a(world, random, blockposition);
    }
}
