package SmartPipes.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{

  @Override
  public void onPacketData( INetworkManager manager,
                            Packet250CustomPayload packet, Player player )
  {

    if( packet.channel.equals( "SmartPipeType" ) )
    {
      handleSmartPipeType( packet );
    }
  }

  private void handleSmartPipeType( Packet250CustomPayload packet )
  {
    DataInputStream inputStream = new DataInputStream( new ByteArrayInputStream( packet.data ) );

    int type;

    try
    {
      type = inputStream.readChar();
    } catch( IOException e )
    {
      e.printStackTrace();
      return;
    }

  }

}
