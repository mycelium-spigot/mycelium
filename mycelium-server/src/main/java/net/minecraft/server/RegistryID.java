package net.minecraft.server;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

public class RegistryID<T> implements Registry<T> {
  private final IdentityHashMap<T, Integer> a = new IdentityHashMap<T, Integer>(512);
  
  private final List<T> b = Lists.newArrayList();
  
  public void a(T paramT, int paramInt) {
    this.a.put(paramT, Integer.valueOf(paramInt));
    while (this.b.size() <= paramInt)
      this.b.add(null); 
    this.b.set(paramInt, paramT);
  }
  
  public int b(T paramT) {
    Integer integer = this.a.get(paramT);
    return (integer == null) ? -1 : integer.intValue();
  }
  
  public final T a(int paramInt) {
    if (paramInt >= 0 && paramInt < this.b.size())
      return this.b.get(paramInt); 
    return null;
  }
  
  public Iterator<T> iterator() {
    return (Iterator<T>)Iterators.filter(this.b.iterator(), Predicates.notNull());
  }
}
