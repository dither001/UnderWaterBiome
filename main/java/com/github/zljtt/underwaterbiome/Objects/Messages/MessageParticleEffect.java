package com.github.zljtt.underwaterbiome.Objects.Messages;

import java.util.Random;
import java.util.function.Supplier;

import com.github.zljtt.underwaterbiome.Capabilities.Provider.CapabilityPlayerDataProvider;
import com.github.zljtt.underwaterbiome.Utils.Interface.IPlayerData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.ParticleStatus;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class MessageParticleEffect extends MessageBase
{
	Random ran = new Random();
	private final String data;
	Minecraft mc = Minecraft.getInstance();

	public MessageParticleEffect(PacketBuffer buf) 
	{
        this.data = buf.readString();
        
    }

	public MessageParticleEffect(String data) 
	{
        this.data = data;
    }

    public void encode(PacketBuffer buf) 
    {
        buf.writeString(data);
    }
    
	@SuppressWarnings("unchecked")
	public void handle(Supplier<Context> context) 
	{
		context.get().enqueueWork(() -> 
 	    {    	
			System.out.print(data);
	 	    PlayerEntity sender = context.get().getSender(); 

 	    	BlockPos pos = sender.getPosition();
 	    	
		    BasicParticleType type;
		    switch(data)
		    {
		    case "enchant":type = ParticleTypes.ENCHANT; break;
		    default: type = ParticleTypes.ENCHANT;
		    }
	 	    for (int i = 0; i<20;i++)
			{
	 	    	double d0 = (double)pos.getX()-0.5+ran.nextDouble()*2;
			    double d1 = (double)pos.getY()+ran.nextDouble()*2;
			    double d2 = (double)pos.getZ()-0.5+ran.nextDouble()*2;
	 		   sender.getEntityWorld().addParticle(type, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
	 	    
 	    });
    	context.get().setPacketHandled(true);
	}

}
