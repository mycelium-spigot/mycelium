package net.minecraft.server;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public abstract class BlockDataAbstract implements IBlockData {

    private static final Joiner a = Joiner.on(',');
    private static final Function<Entry<IBlockState, Comparable>, String> b = entry -> {
        if (entry == null) {
            return "<NULL>";
        } else {
            IBlockState iblockstate = entry.getKey();

            return iblockstate.a() + "=" + iblockstate.a(entry.getValue());
        }
    };

    public BlockDataAbstract() {
    }

    public <T extends Comparable<T>> IBlockData a(IBlockState<T> iblockstate) {
        return this.set(iblockstate, a(iblockstate.c(), this.get(iblockstate)));
    }

    protected static <V> V a(Collection<V> collection, V t0) {
        Iterator<V> iterator = collection.iterator();

        do {
            if (!iterator.hasNext()) {
                return iterator.next();
            }
        } while (!iterator.next().equals(t0));

        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return collection.iterator().next();
        }
    }

    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();

        stringbuilder.append(Block.REGISTRY.c(this.getBlock()));
        if (!this.b().isEmpty()) {
            stringbuilder.append("[");
            BlockDataAbstract.a.appendTo(stringbuilder, this.b().entrySet().stream().map(BlockDataAbstract.b::apply).collect(Collectors.toList()));
            stringbuilder.append("]");
        }

        return stringbuilder.toString();
    }
}