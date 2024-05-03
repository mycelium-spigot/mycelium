package net.minecraft.server;

import java.util.Collection;
import java.util.Random;

public class WeightedRandom {
  public static int a(Collection<? extends WeightedRandomChoice> paramCollection) {
    int i = 0;
    for (WeightedRandomChoice weightedRandomChoice : paramCollection)
      i += weightedRandomChoice.a; 
    return i;
  }
  
  public static <T extends WeightedRandomChoice> T a(Random paramRandom, Collection<T> paramCollection, int paramInt) {
    if (paramInt <= 0)
      throw new IllegalArgumentException(); 
    int i = paramRandom.nextInt(paramInt);
    return a(paramCollection, i);
  }
  
  public static <T extends WeightedRandomChoice> T a(Collection<T> paramCollection, int paramInt) {
    for (WeightedRandomChoice weightedRandomChoice : paramCollection) {
      paramInt -= weightedRandomChoice.a;
      if (paramInt < 0)
        return (T)weightedRandomChoice; 
    } 
    return null;
  }
  
  public static <T extends WeightedRandomChoice> T a(Random paramRandom, Collection<T> paramCollection) {
    return a(paramRandom, paramCollection, a(paramCollection));
  }
  
  public static class WeightedRandomChoice {
    protected int a;
    
    public WeightedRandomChoice(int param1Int) {
      this.a = param1Int;
    }
  }
}
