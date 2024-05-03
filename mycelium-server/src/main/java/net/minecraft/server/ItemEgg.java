package net.minecraft.server;

public class ItemEgg extends Item {

    public ItemEgg() {
        this.maxStackSize = 16;
        this.a(CreativeModeTab.l);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (!entityhuman.abilities.canInstantlyBuild) {
            --itemstack.count;
        }

        world.makeSound(entityhuman, "random.bow", 0.5F, 0.4F / (ItemEgg.g.nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
            world.addEntity(new EntityEgg(world, entityhuman));
        }

        entityhuman.b(StatisticList.USE_ITEM_COUNT[Item.getId(this)]);
        return itemstack;
    }
}
