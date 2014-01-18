package SmartPipes.api;

import SmartPipes.Base.BaseSmartPipe;
import SmartPipes.items.ItemSmartPipe;
import SmartPipes.misc.CreativeTabSP;
import buildcraft.core.utils.Localization;
import buildcraft.transport.*;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import SmartPipes.SmartPipes;

import java.util.Map;

import static buildcraft.BuildCraftTransport.buildPipe;

public class BuildCraftHandle
{
  public static CreativeTabSP creativeTab;

  public BuildCraftHandle( )
  {
    this.registerLocalizations( );
    creativeTab = new CreativeTabSP( );
  }

  public void registerLocalizations( )
  {
    Localization.addLocalization( "/lang/smartpipes/", "en_US" );
  }

  public void registerPipes( Side side )
  {
    SmartPipes.SmartPipeBase = buildPipe( SmartPipes.SmartPipeBaseID, BaseSmartPipe.class, "Smart Pipe" );
    SmartPipes.SmartPipeBase.setTextureName( "buildcraft:textures/blocks/pipeItemsGold.png" );

  }

  public Item makePipe( int ID, Class<? extends Pipe> clas, String descr, Side side )
  {
    Item newPipe = registerPipe( ID, clas );
    newPipe.setCreativeTab( creativeTab );
    newPipe.setUnlocalizedName( clas.getSimpleName( ) );
    Pipe pipe = BlockGenericPipe.createPipe( newPipe.itemID );

    if( side.isClient( ) )
    {
      MinecraftForgeClient.registerItemRenderer( newPipe.itemID, TransportProxyClient.pipeItemRenderer );
    }

    return newPipe;
  }

  public ItemPipe registerPipe( int ID, Class<? extends Pipe> clas )
  {
    ItemPipe newPipe = new ItemSmartPipe( ID, clas );

    Map<Integer, Class<? extends Pipe>> pipes = null;

    try
    {
      pipes = BlockGenericPipe.pipes;
    } catch( NoSuchFieldError e )
    {
      try
      {
        pipes = (Map<Integer, Class<? extends Pipe>>) BlockGenericPipe.class.getDeclaredField( "pipes" ).get( null );
      } catch( Exception e2 )
      {
        return null;
      }
    }

    pipes.put( newPipe.itemID, clas );

    Pipe dummyPipe = BlockGenericPipe.createPipe( newPipe.itemID );
    if( dummyPipe != null )
    {
      newPipe.setPipeIconIndex( dummyPipe.getIconIndexForItem() );
      TransportProxy.proxy.setIconProviderFromPipe( newPipe, dummyPipe );
    }

    return newPipe;
  }

}
