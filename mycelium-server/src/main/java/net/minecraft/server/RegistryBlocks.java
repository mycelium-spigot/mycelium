package net.minecraft.server;

import org.apache.commons.lang3.Validate;

public class RegistryBlocks<K, V> extends RegistryMaterials<K, V> {
    private final K d;

    private V e;

    public RegistryBlocks(K paramK) {
        this.d = paramK;
    }

    public void a(int paramInt, K paramK, V paramV) {
        if (this.d.equals(paramK))
            this.e = paramV;
        super.a(paramInt, paramK, paramV);
    }

    public void a() {
        Validate.notNull(this.d);
    }

    public V get(K paramK) {
        V v = super.get(paramK);
        return (v == null) ? this.e : v;
    }

    public V a(int paramInt) {
        V v = super.a(paramInt);
        return (v == null) ? this.e : v;
    }
}
