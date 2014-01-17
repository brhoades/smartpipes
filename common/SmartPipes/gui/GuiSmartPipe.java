package SmartPipes.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiSmartPipe extends GuiScreen
{

  public static ResourceLocation texture = new ResourceLocation( "smartpipes", "textures/gui/smartPipeGui.png" );
  public final int xTextureSize = 176;  //The x value of the picture
  public final int yTextureSize = 88;  // The y value of the picture.
  public int xPos;
  public int yPos;

  public GuiSmartPipe()
  {
    super();
  }

  @Override
  public void initGui() {
    super.initGui();

    xPos = ( this.width - xTextureSize ) / 2;
    yPos = ( this.height - yTextureSize ) / 2;

    //id, x, y, width, height, text
    buttonList.add( new GuiButton( 1, this.width/2-25, this.height/2, 20, 20, "-" ) );
    buttonList.add( new GuiButton( 2, this.width/2+25, this.height/2, 20, 20, "+" ) );
  }

  public void drawScreen( int par1, int par2, float par3 )
  {
    this.mc.getTextureManager().bindTexture( texture );
    GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );

    // Draw GUI background
    this.drawTexturedModalRect( xPos, yPos, 0, 0, xTextureSize, yTextureSize );

    // Draw buttons
    super.drawScreen(par1, par2, par3);
  }

  public boolean doesGuiPauseGame()
  {
    return false;
  }

}
