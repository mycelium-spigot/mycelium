package net.minecraft.server;

public class PathfinderGoalPassengerCarrotStick extends PathfinderGoal {

    private final EntityInsentient a;
    private final float b;
    private float c;
    private boolean d;
    private int e;
    private int f;

    public PathfinderGoalPassengerCarrotStick(EntityInsentient entityinsentient, float f) {
        this.a = entityinsentient;
        this.b = f;
        this.a(7);
    }

    public void c() {
        this.c = 0.0F;
    }

    public void d() {
        this.d = false;
        this.c = 0.0F;
    }

    public boolean a() {
        return this.a.isAlive() && this.a.passenger != null && this.a.passenger instanceof EntityHuman && (this.d || this.a.bW());
    }

    public void e() {
        EntityHuman entityhuman = (EntityHuman) this.a.passenger;
        EntityCreature entitycreature = (EntityCreature) this.a;
        float f = MathHelper.g(entityhuman.yaw - this.a.yaw) * 0.5F;

        if (f > 5.0F) {
            f = 5.0F;
        }

        if (f < -5.0F) {
            f = -5.0F;
        }

        this.a.yaw = MathHelper.g(this.a.yaw + f);
        if (this.c < this.b) {
            this.c += (this.b - this.c) * 0.01F;
        }

        if (this.c > this.b) {
            this.c = this.b;
        }

        int i = MathHelper.floor(this.a.locX);
        int j = MathHelper.floor(this.a.locY);
        int k = MathHelper.floor(this.a.locZ);
        float f1 = this.c;

        if (this.d) {
            if (this.e++ > this.f) {
                this.d = false;
            }

            f1 += f1 * 1.15F * MathHelper.sin((float) this.e / (float) this.f * 3.1415927F);
        }

        float f2 = 0.91F;

        if (this.a.onGround) {
            f2 = this.a.world.getType(new BlockPosition(MathHelper.d((float) i), MathHelper.d((float) j) - 1, MathHelper.d((float) k))).getBlock().frictionFactor * 0.91F;
        }

        float f3 = 0.16277136F / (f2 * f2 * f2);
        float f4 = MathHelper.sin(entitycreature.yaw * 3.1415927F / 180.0F);
        float f5 = MathHelper.cos(entitycreature.yaw * 3.1415927F / 180.0F);
        float f6 = entitycreature.bI() * f3;
        float f7 = Math.max(f1, 1.0F);

        f7 = f6 / f7;
        float f8 = f1 * f7;
        float f9 = -(f8 * f4);
        float f10 = f8 * f5;

        if (MathHelper.e(f9) > MathHelper.e(f10)) {
            if (f9 < 0.0F) {
                f9 -= this.a.width / 2.0F;
            }

            if (f9 > 0.0F) {
                f9 += this.a.width / 2.0F;
            }

            f10 = 0.0F;
        } else {
            f9 = 0.0F;
            if (f10 < 0.0F) {
                f10 -= this.a.width / 2.0F;
            }

            if (f10 > 0.0F) {
                f10 += this.a.width / 2.0F;
            }
        }

        int l = MathHelper.floor(this.a.locX + (double) f9);
        int i1 = MathHelper.floor(this.a.locZ + (double) f10);
        int j1 = MathHelper.d(this.a.width + 1.0F);
        int k1 = MathHelper.d(this.a.length + entityhuman.length + 1.0F);
        int l1 = MathHelper.d(this.a.width + 1.0F);

        if (i != l || k != i1) {
            Block block = this.a.world.getType(new BlockPosition(i, j, k)).getBlock();
            boolean flag = !this.a(block) && (block.getMaterial() != Material.AIR || !this.a(this.a.world.getType(new BlockPosition(i, j - 1, k)).getBlock()));

            if (flag && 0 == PathfinderNormal.a(this.a.world, this.a, l, j, i1, j1, k1, l1, false, false, true) && 1 == PathfinderNormal.a(this.a.world, this.a, i, j + 1, k, j1, k1, l1, false, false, true) && 1 == PathfinderNormal.a(this.a.world, this.a, l, j + 1, i1, j1, k1, l1, false, false, true)) {
                entitycreature.getControllerJump().a();
            }
        }

        if (!entityhuman.abilities.canInstantlyBuild && this.c >= this.b * 0.5F && this.a.bc().nextFloat() < 0.006F && !this.d) {
            ItemStack itemstack = entityhuman.bA();

            if (itemstack != null && itemstack.getItem() == Items.CARROT_ON_A_STICK) {
                itemstack.damage(1, entityhuman);
                if (itemstack.count == 0) {
                    ItemStack itemstack1 = new ItemStack(Items.FISHING_ROD);

                    itemstack1.setTag(itemstack.getTag());
                    entityhuman.inventory.items[entityhuman.inventory.itemInHandIndex] = itemstack1;
                }
            }
        }

        this.a.g(0.0F, f1);
    }

    private boolean a(Block block) {
        return block instanceof BlockStairs || block instanceof BlockStepAbstract;
    }

    public boolean f() {
        return this.d;
    }

    public void g() {
        this.d = true;
        this.e = 0;
        this.f = this.a.bc().nextInt(841) + 140;
    }

    public boolean h() {
        return !this.f() && this.c > this.b * 0.3F;
    }
}
