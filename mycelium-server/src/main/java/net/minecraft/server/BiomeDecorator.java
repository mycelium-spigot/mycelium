package net.minecraft.server;

import java.util.Random;

public class BiomeDecorator {

    protected World a;
    protected Random b;
    protected BlockPosition c;
    protected CustomWorldSettingsFinal d;
    protected WorldGenerator f;
    protected WorldGenerator g;
    protected WorldGenerator w;
    protected WorldGenerator x;
    protected int z;
    protected int A;
    protected int B;
    protected int C;
    protected int D;
    protected int E;
    protected int F;
    protected int G;
    protected int H;
    protected int I;
    protected int J;
    protected int K;
    public boolean L;

    public BiomeDecorator() {
        this.f = new WorldGenSand(Blocks.SAND, 7);
        this.g = new WorldGenSand(Blocks.GRAVEL, 6);
        this.B = 2;
        this.C = 1;
        this.H = 1;
        this.I = 3;
        this.J = 1;
        this.L = true;
    }

    public void a(World world, Random random, BiomeBase biomebase, BlockPosition blockposition) {
        if (this.a != null) {
            throw new RuntimeException("Already decorating");
        } else {
            this.a = world;
            String s = world.getWorldData().getGeneratorOptions();

            if (s != null) {
                this.d = CustomWorldSettingsFinal.CustomWorldSettings.a(s).b();
            } else {
                this.d = CustomWorldSettingsFinal.CustomWorldSettings.a("").b();
            }

            this.b = random;
            this.c = blockposition;
            this.a(biomebase);
            this.a = null;
            this.b = null;
        }
    }

    protected void a(BiomeBase biomebase) {
        int i;
        int j;
        int k;

        for (i = 0; i < this.I; ++i) {
            j = this.b.nextInt(16) + 8;
            k = this.b.nextInt(16) + 8;
            this.f.generate(this.a, this.b, this.a.r(this.c.a(j, 0, k)));
        }

        for (i = 0; i < this.H; ++i) {
            j = this.b.nextInt(16) + 8;
            k = this.b.nextInt(16) + 8;
            this.g.generate(this.a, this.b, this.a.r(this.c.a(j, 0, k)));
        }

        i = this.A;
        if (this.b.nextInt(10) == 0) {
            ++i;
        }

        int l;
        BlockPosition blockposition;

        for (j = 0; j < i; ++j) {
            k = this.b.nextInt(16) + 8;
            l = this.b.nextInt(16) + 8;
            WorldGenTreeAbstract worldgentreeabstract = biomebase.a(this.b);

            worldgentreeabstract.e();
            blockposition = this.a.getHighestBlockYAt(this.c.a(k, 0, l));
            if (worldgentreeabstract.generate(this.a, this.b, blockposition)) {
                worldgentreeabstract.a(this.a, this.b, blockposition);
            }
        }
        
        BlockPosition blockposition1;
        int i1;
        int j1;

        for (j = 0; j < this.F; ++j) {
            k = this.b.nextInt(16) + 8;
            l = this.b.nextInt(16) + 8;
            i1 = this.a.getHighestBlockYAt(this.c.a(k, 0, l)).getY() * 2;
            if (i1 > 0) {
                j1 = this.b.nextInt(i1);
                this.w.generate(this.a, this.b, this.c.a(k, j1, l));
            }
        }

        for (j = 0; j < 10; ++j) {
            k = this.b.nextInt(16) + 8;
            l = this.b.nextInt(16) + 8;
            i1 = this.a.getHighestBlockYAt(this.c.a(k, 0, l)).getY() * 2;
            if (i1 > 0) {
                j1 = this.b.nextInt(i1);
                this.w.generate(this.a, this.b, this.c.a(k, j1, l));
            }
        }

        for (j = 0; j < this.G; ++j) {
            k = this.b.nextInt(16) + 8;
            l = this.b.nextInt(16) + 8;
            i1 = this.a.getHighestBlockYAt(this.c.a(k, 0, l)).getY() * 2;
            if (i1 > 0) {
                j1 = this.b.nextInt(i1);
                this.x.generate(this.a, this.b, this.c.a(k, j1, l));
            }
        }

        if (this.L) {
            for (j = 0; j < 50; ++j) {
                k = this.b.nextInt(16) + 8;
                l = this.b.nextInt(16) + 8;
                i1 = this.b.nextInt(248) + 8;
                if (i1 > 0) {
                    j1 = this.b.nextInt(i1);
                    blockposition1 = this.c.a(k, j1, l);
                    (new WorldGenLiquids(Blocks.FLOWING_WATER)).generate(this.a, this.b, blockposition1);
                }
            }

            for (j = 0; j < 20; ++j) {
                k = this.b.nextInt(16) + 8;
                l = this.b.nextInt(16) + 8;
                i1 = this.b.nextInt(this.b.nextInt(this.b.nextInt(240) + 8) + 8);
                blockposition = this.c.a(k, i1, l);
                (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(this.a, this.b, blockposition);
            }
        }

    }

    protected void a(int i, WorldGenerator worldgenerator, int j, int k) {
        int l;

        if (k < j) {
            l = j;
            j = k;
            k = l;
        } else if (k == j) {
            if (j < 255) {
                ++k;
            } else {
                --j;
            }
        }

        for (l = 0; l < i; ++l) {
            BlockPosition blockposition = this.c.a(this.b.nextInt(16), this.b.nextInt(k - j) + j, this.b.nextInt(16));

            worldgenerator.generate(this.a, this.b, blockposition);
        }

    }

    protected void b(int i, WorldGenerator worldgenerator, int j, int k) {
        for (int l = 0; l < i; ++l) {
            BlockPosition blockposition = this.c.a(this.b.nextInt(16), this.b.nextInt(k) + this.b.nextInt(k) + j - k, this.b.nextInt(16));

            worldgenerator.generate(this.a, this.b, blockposition);
        }

    }
}
