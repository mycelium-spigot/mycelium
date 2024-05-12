package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ChunkProviderFlat implements IChunkProvider {

    private World a;
    private Random b;
    private final IBlockData[] c = new IBlockData[256];
    private final WorldGenFlatInfo d;
    private final boolean f;
    private WorldGenLakes h;
    private WorldGenLakes i;

    public ChunkProviderFlat(World world, long i, boolean flag, String s) {
        this.a = world;
        this.b = new Random(i);
        this.d = WorldGenFlatInfo.a(s);

        if (this.d.b().containsKey("lake")) {
            this.h = new WorldGenLakes(Blocks.WATER);
        }

        if (this.d.b().containsKey("lava_lake")) {
            this.i = new WorldGenLakes(Blocks.LAVA);
        }

        int j = 0;
        int k = 0;
        boolean flag1 = true;
        Iterator iterator = this.d.c().iterator();

        while (iterator.hasNext()) {
            WorldGenFlatLayerInfo worldgenflatlayerinfo = (WorldGenFlatLayerInfo) iterator.next();

            for (int l = worldgenflatlayerinfo.d(); l < worldgenflatlayerinfo.d() + worldgenflatlayerinfo.b(); ++l) {
                IBlockData iblockdata = worldgenflatlayerinfo.c();

                if (iblockdata.getBlock() != Blocks.AIR) {
                    flag1 = false;
                    this.c[l] = iblockdata;
                }
            }

            if (worldgenflatlayerinfo.c().getBlock() == Blocks.AIR) {
                k += worldgenflatlayerinfo.b();
            } else {
                j += worldgenflatlayerinfo.b() + k;
                k = 0;
            }
        }

        world.b(j);
        this.f = flag1 ? false : this.d.b().containsKey("decoration");
    }

    public Chunk getOrCreateChunk(int i, int j) {
        ChunkSnapshot chunksnapshot = new ChunkSnapshot();

        int k;

        for (int l = 0; l < this.c.length; ++l) {
            IBlockData iblockdata = this.c[l];

            if (iblockdata != null) {
                for (int i1 = 0; i1 < 16; ++i1) {
                    for (k = 0; k < 16; ++k) {
                        chunksnapshot.a(i1, l, k, iblockdata);
                    }
                }
            }
        }

        Chunk chunk = new Chunk(this.a, chunksnapshot, i, j);
        BiomeBase[] abiomebase = this.a.getWorldChunkManager().getBiomeBlock((BiomeBase[]) null, i * 16, j * 16, 16, 16);
        byte[] abyte = chunk.getBiomeIndex();

        for (k = 0; k < abyte.length; ++k) {
            abyte[k] = (byte) abiomebase[k].id;
        }

        chunk.initLighting();
        return chunk;
    }

    public boolean isChunkLoaded(int i, int j) {
        return true;
    }

    public void getChunkAt(IChunkProvider ichunkprovider, int i, int j) {
        int k = i * 16;
        int l = j * 16;
        BlockPosition blockposition = new BlockPosition(k, 0, l);
        BiomeBase biomebase = this.a.getBiome(new BlockPosition(k + 16, 0, l + 16));
        boolean flag = false;

        this.b.setSeed(this.a.getSeed());
        long i1 = this.b.nextLong() / 2L * 2L + 1L;
        long j1 = this.b.nextLong() / 2L * 2L + 1L;

        this.b.setSeed((long) i * i1 + (long) j * j1 ^ this.a.getSeed());

        if (this.h != null && !flag && this.b.nextInt(4) == 0) {
            this.h.generate(this.a, this.b, blockposition.a(this.b.nextInt(16) + 8, this.b.nextInt(256), this.b.nextInt(16) + 8));
        }

        if (this.i != null && !flag && this.b.nextInt(8) == 0) {
            BlockPosition blockposition1 = blockposition.a(this.b.nextInt(16) + 8, this.b.nextInt(this.b.nextInt(248) + 8), this.b.nextInt(16) + 8);

            if (blockposition1.getY() < this.a.F() || this.b.nextInt(10) == 0) {
                this.i.generate(this.a, this.b, blockposition1);
            }
        }

        if (this.f) {
            biomebase.a(this.a, this.b, blockposition);
        }

    }

    public boolean a(IChunkProvider ichunkprovider, Chunk chunk, int i, int j) {
        return false;
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
        return true;
    }

    public void c() {}

    public boolean unloadChunks() {
        return false;
    }

    public boolean canSave() {
        return true;
    }

    public String getName() {
        return "FlatLevelSource";
    }

    public List<BiomeBase.BiomeMeta> getMobsFor(EnumCreatureType enumcreaturetype, BlockPosition blockposition) {
        BiomeBase biomebase = this.a.getBiome(blockposition);

        return biomebase.getMobs(enumcreaturetype);
    }

    public int getLoadedChunks() {
        return 0;
    }

    public Chunk getChunkAt(BlockPosition blockposition) {
        return this.getOrCreateChunk(blockposition.getX() >> 4, blockposition.getZ() >> 4);
    }
}
