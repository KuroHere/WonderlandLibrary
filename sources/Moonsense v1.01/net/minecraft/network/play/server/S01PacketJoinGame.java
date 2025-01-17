// 
// Decompiled by Procyon v0.5.36
// 

package net.minecraft.network.play.server;

import net.minecraft.network.INetHandler;
import net.minecraft.network.play.INetHandlerPlayClient;
import java.io.IOException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.WorldType;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldSettings;
import net.minecraft.network.Packet;

public class S01PacketJoinGame implements Packet
{
    private int field_149206_a;
    private boolean field_149204_b;
    private WorldSettings.GameType field_149205_c;
    private int field_149202_d;
    private EnumDifficulty field_149203_e;
    private int field_149200_f;
    private WorldType field_149201_g;
    private boolean field_179745_h;
    private static final String __OBFID = "CL_00001310";
    
    public S01PacketJoinGame() {
    }
    
    public S01PacketJoinGame(final int p_i45976_1_, final WorldSettings.GameType p_i45976_2_, final boolean p_i45976_3_, final int p_i45976_4_, final EnumDifficulty p_i45976_5_, final int p_i45976_6_, final WorldType p_i45976_7_, final boolean p_i45976_8_) {
        this.field_149206_a = p_i45976_1_;
        this.field_149202_d = p_i45976_4_;
        this.field_149203_e = p_i45976_5_;
        this.field_149205_c = p_i45976_2_;
        this.field_149200_f = p_i45976_6_;
        this.field_149204_b = p_i45976_3_;
        this.field_149201_g = p_i45976_7_;
        this.field_179745_h = p_i45976_8_;
    }
    
    @Override
    public void readPacketData(final PacketBuffer data) throws IOException {
        this.field_149206_a = data.readInt();
        final short var2 = data.readUnsignedByte();
        this.field_149204_b = ((var2 & 0x8) == 0x8);
        final int var3 = var2 & 0xFFFFFFF7;
        this.field_149205_c = WorldSettings.GameType.getByID(var3);
        this.field_149202_d = data.readByte();
        this.field_149203_e = EnumDifficulty.getDifficultyEnum(data.readUnsignedByte());
        this.field_149200_f = data.readUnsignedByte();
        this.field_149201_g = WorldType.parseWorldType(data.readStringFromBuffer(16));
        if (this.field_149201_g == null) {
            this.field_149201_g = WorldType.DEFAULT;
        }
        this.field_179745_h = data.readBoolean();
    }
    
    @Override
    public void writePacketData(final PacketBuffer data) throws IOException {
        data.writeInt(this.field_149206_a);
        int var2 = this.field_149205_c.getID();
        if (this.field_149204_b) {
            var2 |= 0x8;
        }
        data.writeByte(var2);
        data.writeByte(this.field_149202_d);
        data.writeByte(this.field_149203_e.getDifficultyId());
        data.writeByte(this.field_149200_f);
        data.writeString(this.field_149201_g.getWorldTypeName());
        data.writeBoolean(this.field_179745_h);
    }
    
    public void processPacket(final INetHandlerPlayClient handler) {
        handler.handleJoinGame(this);
    }
    
    public int func_149197_c() {
        return this.field_149206_a;
    }
    
    public boolean func_149195_d() {
        return this.field_149204_b;
    }
    
    public WorldSettings.GameType func_149198_e() {
        return this.field_149205_c;
    }
    
    public int func_149194_f() {
        return this.field_149202_d;
    }
    
    public EnumDifficulty func_149192_g() {
        return this.field_149203_e;
    }
    
    public int func_149193_h() {
        return this.field_149200_f;
    }
    
    public WorldType func_149196_i() {
        return this.field_149201_g;
    }
    
    public boolean func_179744_h() {
        return this.field_179745_h;
    }
    
    @Override
    public void processPacket(final INetHandler handler) {
        this.processPacket((INetHandlerPlayClient)handler);
    }
}
