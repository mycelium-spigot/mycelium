package net.minecraft.server;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class AttributeModifiable implements AttributeInstance {
    private final AttributeMapBase a;

    private final IAttribute b;

    private final Map<Integer, Set<AttributeModifier>> c = Maps.newHashMap();

    private final Map<String, Set<AttributeModifier>> d = Maps.newHashMap();

    private final Map<UUID, AttributeModifier> e = Maps.newHashMap();

    private double f;

    private boolean g = true;

    private double h;

    public AttributeModifiable(AttributeMapBase paramAttributeMapBase, IAttribute paramIAttribute) {
        this.a = paramAttributeMapBase;
        this.b = paramIAttribute;
        this.f = paramIAttribute.b();
        for (byte b = 0; b < 3; b++)
            this.c.put(Integer.valueOf(b), Sets.newHashSet());
    }

    public IAttribute getAttribute() {
        return this.b;
    }

    public double b() {
        return this.f;
    }

    public void setValue(double paramDouble) {
        if (paramDouble == b())
            return;
        this.f = paramDouble;
        f();
    }

    public Collection<AttributeModifier> a(int paramInt) {
        return this.c.get(Integer.valueOf(paramInt));
    }

    public Collection<AttributeModifier> c() {
        HashSet<AttributeModifier> hashSet = Sets.newHashSet();
        for (byte b = 0; b < 3; b++)
            hashSet.addAll(a(b));
        return hashSet;
    }

    public AttributeModifier a(UUID paramUUID) {
        return this.e.get(paramUUID);
    }

    public boolean a(AttributeModifier paramAttributeModifier) {
        return (this.e.get(paramAttributeModifier.a()) != null);
    }

    public void b(AttributeModifier paramAttributeModifier) {
        if (a(paramAttributeModifier.a()) != null)
            throw new IllegalArgumentException("Modifier is already applied on this attribute!");
        Set<AttributeModifier> set = this.d.get(paramAttributeModifier.b());
        if (set == null) {
            set = Sets.newHashSet();
            this.d.put(paramAttributeModifier.b(), set);
        }
        ((Set<AttributeModifier>) this.c.get(Integer.valueOf(paramAttributeModifier.c()))).add(paramAttributeModifier);
        set.add(paramAttributeModifier);
        this.e.put(paramAttributeModifier.a(), paramAttributeModifier);
        f();
    }

    protected void f() {
        this.g = true;
        this.a.a(this);
    }

    public void c(AttributeModifier paramAttributeModifier) {
        for (byte b = 0; b < 3; b++) {
            Set set1 = this.c.get(Integer.valueOf(b));
            set1.remove(paramAttributeModifier);
        }
        Set set = this.d.get(paramAttributeModifier.b());
        if (set != null) {
            set.remove(paramAttributeModifier);
            if (set.isEmpty())
                this.d.remove(paramAttributeModifier.b());
        }
        this.e.remove(paramAttributeModifier.a());
        f();
    }

    public double getValue() {
        if (this.g) {
            this.h = g();
            this.g = false;
        }
        return this.h;
    }

    private double g() {
        double d1 = b();
        for (AttributeModifier attributeModifier : b(0))
            d1 += attributeModifier.d();
        double d2 = d1;
        for (AttributeModifier attributeModifier : b(1))
            d2 += d1 * attributeModifier.d();
        for (AttributeModifier attributeModifier : b(2))
            d2 *= 1.0D + attributeModifier.d();
        return this.b.a(d2);
    }

    private Collection<AttributeModifier> b(int paramInt) {
        HashSet<AttributeModifier> hashSet = Sets.newHashSet(a(paramInt));
        IAttribute iAttribute = this.b.d();

        while (iAttribute != null) {
            AttributeInstance attributeInstance = this.a.a(iAttribute);
            if (attributeInstance != null)
                hashSet.addAll(attributeInstance.a(paramInt));
            iAttribute = iAttribute.d();
        }
        
        return hashSet;
    }
}
