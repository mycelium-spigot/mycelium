package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class ChunkProviderFlat implements IChunkProvider {

    private World world;
    private Random random;
    private final IBlockData[] c = new IBlockData[256];

    public ChunkProviderFlat(World world, long i, boolean flag, String s) {
        this.world = world;
        this.random = new Random(i);

        c[0] = Blocks.BEDROCK.getBlockData();
        c[1] = Blocks.DIRT.getBlockData();
        c[2] = Blocks.DIRT.getBlockData();
        c[3] = Blocks.GRASS.getBlockData();
    }

    public Chunk getOrCreateChunk(int i, int j) {
        ChunkSnapshot chunkSnapshot = new ChunkSnapshot();
        int chunkLengthCache = this.c.length;
        
        // Iterate over the chunk data
        for (int y = 0; y < chunkLengthCache; ++y) {
            IBlockData blockData = this.c[y];
        
            if (blockData != null) {
                for (int x = 0; x < 16; ++x) {
                    for (int z = 0; z < 16; ++z) {
                        chunkSnapshot.a(x, y, z, blockData);
                    }
                }
            }
        }
        
        // Initialize the chunk
        Chunk chunk = new Chunk(this.world, chunkSnapshot, i, j);
        BiomeBase[] biomes = this.world.getWorldChunkManager().getBiomeBlock(null, i * 16, j * 16, 16, 16);
        byte[] biomeIndices = chunk.getBiomeIndex();
        
        // Assign biome indices
        for (int index = 0; index < biomeIndices.length; ++index) {
            biomeIndices[index] = (byte) biomes[index].id;
        }
        
        chunk.initLighting();

        return chunk;
    }

    public boolean isChunkLoaded(int i, int j) {
        return true;
    }

    public void getChunkAt(IChunkProvider ichunkprovider, int i, int j) {
        int chunkX = i * 16;
        int chunkZ = j * 16;
        
        this.random.setSeed(this.world.getSeed());
        
        long offsetX = this.random.nextLong() / 2L * 2L + 1L;
        long offsetZ = this.random.nextLong() / 2L * 2L + 1L;
        
        this.random.setSeed((long) chunkX * offsetX + (long) chunkZ * offsetZ ^ this.world.getSeed());        
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
        BiomeBase biomebase = this.world.getBiome(blockposition);

        return biomebase.getMobs(enumcreaturetype);
    }

    public int getLoadedChunks() {
        return 0;
    }

    public Chunk getChunkAt(BlockPosition blockposition) {
        return this.getOrCreateChunk(blockposition.getX() >> 4, blockposition.getZ() >> 4);
    }
}