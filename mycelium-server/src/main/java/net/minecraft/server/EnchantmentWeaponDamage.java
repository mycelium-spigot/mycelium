package net.minecraft.server;

public class EnchantmentWeaponDamage extends Enchantment {

    private static final String[] E = new String[] { "all", "undead", "arthropods"};
    private static final int[] F = new int[] { 1, 5, 5};
    private static final int[] G = new int[] { 11, 8, 8};
    private static final int[] H = new int[] { 20, 20, 20};
    public final int a;

    public EnchantmentWeaponDamage(int i, MinecraftKey minecraftkey, int j, int k) {
        super(i, minecraftkey, j, EnchantmentSlotType.WEAPON);
        this.a = k;
    }

    public int a(int i) {
        return EnchantmentWeaponDamage.F[this.a] + (i - 1) * EnchantmentWeaponDamage.G[this.a];
    }

    public int b(int i) {
        return this.a(i) + EnchantmentWeaponDamage.H[this.a];
    }

    public int getMaxLevel() {
        return 5;
    }

    public float a(int i, EnumMonsterType enummonstertype) {
        return this.a == 0 ? (float) i * 1.25F : (this.a == 1 && enummonstertype == EnumMonsterType.UNDEAD ? (float) i * 2.5F : (this.a == 2 && enummonstertype == EnumMonsterType.ARTHROPOD ? (float) i * 2.5F : 0.0F));
    }

    public String a() {
        return "enchantment.damage." + EnchantmentWeaponDamage.E[this.a];
    }

    public boolean a(Enchantment enchantment) {
        return !(enchantment instanceof EnchantmentWeaponDamage);
    }

    public boolean canEnchant(ItemStack itemstack) {
        return itemstack.getItem() instanceof ItemAxe ? true : super.canEnchant(itemstack);
    }

    public void a(EntityLiving entityliving, Entity entity, int i) {
        if (entity instanceof EntityLiving) {
            EntityLiving entityliving1 = (EntityLiving) entity;

            if (this.a == 2 && entityliving1.getMonsterType() == EnumMonsterType.ARTHROPOD) {
                int j = 20 + entityliving.bc().nextInt(10 * i);

                entityliving1.addEffect(new MobEffect(MobEffectList.SLOWER_MOVEMENT.id, j, 3));
            }
        }

    }
}
