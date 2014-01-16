package SmartPipes.Base;

import SmartPipes.ItemIconProvider;
import SmartPipes.SmartPipes;
import buildcraft.api.core.IIconProvider;
import buildcraft.core.utils.MathUtils;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportItems;
import buildcraft.transport.TransportConstants;
import buildcraft.transport.TravelingItem;
import buildcraft.transport.pipes.events.PipeEventItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;

public class SmartPipe extends Pipe
{
  public SmartPipe( int itemID )
  {
    super( new PipeTransportItems(), itemID );
  }

  @Override
  @SideOnly(Side.CLIENT)
  public IIconProvider getIconProvider()
  {
    return SmartPipes.instance.itemIconProvider;
  }

  @Override
  public int getIconIndex( ForgeDirection direction )
  {
    return ItemIconProvider.Items.PipeSmartBasic.i();
  }

  public void eventHandler( PipeEventItem.AdjustSpeed event )
  {
    event.handled = true;
    TravelingItem item = event.item;
    item.setSpeed( MathUtils.clamp( item.getSpeed() * 4F, TransportConstants.PIPE_NORMAL_SPEED * 4F,
                                    TransportConstants.PIPE_NORMAL_SPEED * 20F ) );
  }

  @Override
  public boolean blockActivated( EntityPlayer entityplayer )
  {
    if( entityplayer.isSneaking() )
    {
      return false;
    }
    entityplayer.openGui( SmartPipes.instance, 0, entityplayer.getEntityWorld(), (int) entityplayer.posX, (int) entityplayer.posY, (int) entityplayer.posZ );
    return true;
  }

}
