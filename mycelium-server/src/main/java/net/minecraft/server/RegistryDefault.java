package net.minecraft.server;

public class RegistryDefault<K, V> extends RegistrySimple<K, V> {
    private final V a;

    public RegistryDefault(V paramV) {
        this.a = paramV;
    }

    public V get(K paramK) {
        V v = super.get(paramK);
        return (v == null) ? this.a : v;
    }
}
