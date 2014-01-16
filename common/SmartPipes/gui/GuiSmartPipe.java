package SmartPipes.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiSmartPipe extends GuiScreen
{

  public static ResourceLocation texture = new ResourceLocation( "smartpipes", "textures/gui/smartPipeGui.png" );
  public final int xTextureSize = 175;  //The x value of the picture
  public final int yTextureSize = 87;  // The y value of the picture.
  public final int xPos = ( this.width - xTextureSize ) / 2;
  public final int yPos = ( this.height - yTextureSize ) / 2;

  public GuiSmartPipe()
  {
    super();
  }

  public boolean doesGuiPauseGame()
  {
    return false;
  }

  public void drawScreen( int par1, int par2, float par3 )
  {
    this.mc.getTextureManager().bindTexture( texture );
    GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
    this.drawTexturedModalRect( xPos, yPos, 0, 0, xTextureSize, yTextureSize );
  }

}
