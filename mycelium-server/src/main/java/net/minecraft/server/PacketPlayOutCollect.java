package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutCollect implements Packet<PacketListenerPlayOut> {
  private int a;
  
  private int b;
  
  public PacketPlayOutCollect() {}
  
  public PacketPlayOutCollect(int paramInt1, int paramInt2) {
    this.a = paramInt1;
    this.b = paramInt2;
  }
  
  public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
    this.a = paramPacketDataSerializer.e();
    this.b = paramPacketDataSerializer.e();
  }
  
  public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
    paramPacketDataSerializer.b(this.a);
    paramPacketDataSerializer.b(this.b);
  }
  
  public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
    paramPacketListenerPlayOut.a(this);
  }
}