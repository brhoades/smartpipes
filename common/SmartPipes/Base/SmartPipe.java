package SmartPipes.Base;

import buildcraft.BuildCraftTransport;
import buildcraft.api.core.IIconProvider;
import buildcraft.core.utils.MathUtils;
import buildcraft.transport.*;
import buildcraft.transport.pipes.events.PipeEventItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
    return BuildCraftTransport.instance.pipeIconProvider;
  }

  @Override
  public int getIconIndex( ForgeDirection direction )
  {
    return PipeIconProvider.TYPE.PipeItemsGold.ordinal();
  }

  public void eventHandler( PipeEventItem.AdjustSpeed event )
  {
    event.handled = true;
    TravelingItem item = event.item;
    item.setSpeed( MathUtils.clamp( item.getSpeed() * 4F, TransportConstants.PIPE_NORMAL_SPEED * 4F,
                                    TransportConstants.PIPE_NORMAL_SPEED * 20F ) );
  }
}
