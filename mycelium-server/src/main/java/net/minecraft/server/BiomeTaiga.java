package net.minecraft.server;

import java.util.Random;

public class BiomeTaiga extends BiomeBase {

    private static final WorldGenTaiga1 aD = new WorldGenTaiga1();
    private static final WorldGenTaiga2 aE = new WorldGenTaiga2(false);
    private static final WorldGenMegaTree aF = new WorldGenMegaTree(false, false);
    private static final WorldGenMegaTree aG = new WorldGenMegaTree(false, true);
    private int aI;

    public BiomeTaiga(int i, int j) {
        super(i);
        this.aI = j;
        this.au.add(new BiomeBase.BiomeMeta(EntityWolf.class, 8, 4, 4));
        this.as.A = 10;
        if (j != 1 && j != 2) {
            this.as.C = 1;
            this.as.E = 1;
        } else {
            this.as.C = 7;
            this.as.D = 1;
            this.as.E = 3;
        }

    }

    public WorldGenTreeAbstract a(Random random) {
        return (WorldGenTreeAbstract) ((this.aI == 1 || this.aI == 2) && random.nextInt(3) == 0 ? (this.aI != 2 && random.nextInt(13) != 0 ? BiomeTaiga.aF : BiomeTaiga.aG) : (random.nextInt(3) == 0 ? BiomeTaiga.aD : BiomeTaiga.aE));
    }
    
    public void a(World world, Random random, BlockPosition blockposition) {
        super.a(world, random, blockposition);
    }

    public void a(World world, Random random, ChunkSnapshot chunksnapshot, int i, int j, double d0) {
        if (this.aI == 1 || this.aI == 2) {
            this.ak = Blocks.GRASS.getBlockData();
            this.al = Blocks.DIRT.getBlockData();
            if (d0 > 1.75D) {
                this.ak = Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, BlockDirt.EnumDirtVariant.COARSE_DIRT);
            } else if (d0 > -0.95D) {
                this.ak = Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, BlockDirt.EnumDirtVariant.PODZOL);
            }
        }

        this.b(world, random, chunksnapshot, i, j, d0);
    }

    protected BiomeBase d(int i) {
        return this.id == BiomeBase.MEGA_TAIGA.id ? (new BiomeTaiga(i, 2)).a(5858897, true).a("Mega Spruce Taiga").a(5159473).a(0.25F, 0.8F).a(new BiomeBase.BiomeTemperature(this.an, this.ao)) : super.d(i);
    }
}
