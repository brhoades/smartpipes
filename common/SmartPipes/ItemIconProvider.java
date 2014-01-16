package SmartPipes;

import buildcraft.api.core.IIconProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class ItemIconProvider implements IIconProvider
{
  public static enum Items
  {
    PipeSmartBasic( 0, "buildcraft:pipeItemsGold" ),
    PipeSmartCap( 1, "buildcraft:pipeItemsGold" ),
    MAX( 2, "" );

    private int index;
    private String file;

    private Items( int index, String file )
    {
      this.index = index;
      this.file = file;
    }

    public int i()
    {
      return this.index;
    }

    public String icon()
    {
      return this.file;
    }
  }

  @SideOnly(Side.CLIENT)
  private Icon[] _icons;

  @Override
  @SideOnly(Side.CLIENT)
  public Icon getIcon( int i )
  {
    return _icons[i];
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons( IconRegister iconRegister )
  {
    _icons = new Icon[Items.MAX.i()];

    for( Items item : Items.values() )
    {
      if( item.i() == Items.MAX.i() )
      {
        continue;
      }

      _icons[item.i()] = iconRegister.registerIcon( item.icon() );
    }
  }

}
