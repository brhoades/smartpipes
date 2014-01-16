package SmartPipes.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
  // returns an instance of the Container
  @Override
  public Object getServerGuiElement( int id, EntityPlayer player, World world, int x, int y, int z )
  {
    return null;
  }

  // returns an instance of the Gui
  @Override
  public Object getClientGuiElement( int id, EntityPlayer player, World world, int x, int y, int z )
  {
    return new GuiSmartPipe();
  }
}
