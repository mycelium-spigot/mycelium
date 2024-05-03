package net.minecraft.server;

import com.google.gson.JsonObject;
import java.io.File;
import java.net.SocketAddress;

public class IpBanList extends JsonList<String, IpBanEntry> {
  public IpBanList(File paramFile) {
    super(paramFile);
  }
  
  protected JsonListEntry<String> a(JsonObject paramJsonObject) {
    return new IpBanEntry(paramJsonObject);
  }
  
  public boolean isBanned(SocketAddress paramSocketAddress) {
    String str = c(paramSocketAddress);
    return d(str);
  }
  
  public IpBanEntry get(SocketAddress paramSocketAddress) {
    String str = c(paramSocketAddress);
    return get(str);
  }
  
  private String c(SocketAddress paramSocketAddress) {
    String str = paramSocketAddress.toString();
    if (str.contains("/"))
      str = str.substring(str.indexOf('/') + 1); 
    if (str.contains(":"))
      str = str.substring(0, str.indexOf(':')); 
    return str;
  }
}
