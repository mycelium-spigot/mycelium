package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;

public class BlockStateBoolean extends BlockState<Boolean> {
  private final ImmutableSet<Boolean> a;
  
  protected BlockStateBoolean(String paramString) {
    super(paramString, Boolean.class);
    this.a = ImmutableSet.of(Boolean.valueOf(true), Boolean.valueOf(false));
  }
  
  public Collection<Boolean> c() {
    return (Collection<Boolean>)this.a;
  }
  
  public static BlockStateBoolean of(String paramString) {
    return new BlockStateBoolean(paramString);
  }
  
  public String a(Boolean paramBoolean) {
    return paramBoolean.toString();
  }
}
