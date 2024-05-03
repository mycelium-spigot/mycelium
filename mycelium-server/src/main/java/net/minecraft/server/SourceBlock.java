package net.minecraft.server;

public class SourceBlock implements ISourceBlock {
    private final World a;

    private final BlockPosition b;

    public SourceBlock(World paramWorld, BlockPosition paramBlockPosition) {
        this.a = paramWorld;
        this.b = paramBlockPosition;
    }

    public World getWorld() {
        return this.a;
    }

    public double getX() {
        return this.b.getX() + 0.5D;
    }

    public double getY() {
        return this.b.getY() + 0.5D;
    }

    public double getZ() {
        return this.b.getZ() + 0.5D;
    }

    public BlockPosition getBlockPosition() {
        return this.b;
    }

    public int f() {
        IBlockData iBlockData = this.a.getType(this.b);
        return iBlockData.getBlock().toLegacyData(iBlockData);
    }

    public TileEntity getTileEntity() {
        return this.a.getTileEntity(this.b);
    }
}