package net.minecraft.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketPrepender extends MessageToByteEncoder<ByteBuf> {

    public PacketPrepender() {}

    protected void a(ChannelHandlerContext channelhandlercontext, ByteBuf bytebuf, ByteBuf bytebuf1) throws Exception {
        int i = bytebuf.readableBytes();
        int j = PacketDataSerializer.a(i);

        if (j > 3) {
            throw new IllegalArgumentException("unable to fit " + i + " into " + 3);
        } else {
            PacketDataSerializer packetdataserializer = new PacketDataSerializer(bytebuf1);

            packetdataserializer.ensureWritable(j + i);
            packetdataserializer.b(i);
            packetdataserializer.writeBytes(bytebuf, bytebuf.readerIndex(), i);
        }
    }

    protected void encode(ChannelHandlerContext channelhandlercontext, ByteBuf object, ByteBuf bytebuf) throws Exception {
        this.a(channelhandlercontext, (ByteBuf) object, bytebuf);
    }
}
