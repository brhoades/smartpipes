package SmartPipes.misc;

import SmartPipes.SmartPipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabSP extends CreativeTabs
{
  public CreativeTabSP( )
  {
    super( "Smart Pipes" );
  }

  @Override
  @SideOnly( Side.CLIENT )
  public int getTabIconItemIndex( )
  {
    return SmartPipes.SmartPipeBaseID;
  }
}
