package net.minecraft.server;

public class EnchantmentFire extends Enchantment {

    protected EnchantmentFire(int i, MinecraftKey minecraftkey, int j) {
        super(i, minecraftkey, j, EnchantmentSlotType.WEAPON);
        this.c("fire");
    }

    public int a(int i) {
        return 10 + 20 * (i - 1);
    }

    public int b(int i) {
        return super.a(i) + 50;
    }

    public int getMaxLevel() {
        return 2;
    }
}
