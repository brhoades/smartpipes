package SmartPipes.gui;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

;


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
  public void initGui()
  {
    int buttonHeight = 20;
    int buttonWidth = 50;

    int spacingFactorX = 10;
    int spacingFactorY = 15;

    String[][] buttons = new String[][]
    {
      { "Overflow", "Crafting" },
      { "Stock", "Storage"  }
    };

    int buttonX = buttons.length;
    int buttonY = buttons[0].length;

    //Calculate anticipated margins from the edges
    //Gives us our starting positions for each row and column, tabulating spaces between and button sizes.
    int xMargins = ( xTextureSize - (buttonX)*buttonWidth - (buttonX-1)*spacingFactorX )/2;
    int yMargins = ( yTextureSize - (buttonY)*buttonHeight - (buttonY-1)*spacingFactorY )/2;

    super.initGui();

    //This is done for the sake of simplification
    //spacingFactorX += buttonWidth;
    //spacingFactorY += buttonHeight;

    //Draw centered
    xPos = ( this.width - xTextureSize ) / 2;
    yPos = ( this.height - yTextureSize ) / 2;

    for( int i=0; i<buttonX; i++ )
    {
      for( int j=0; j<buttonY; j++ )
      {
        int myX = xPos + xMargins + spacingFactorX*i + buttonWidth*i;
        int myY = yPos + yMargins + spacingFactorY*j + buttonHeight*j;

        //Unique ID, X, Y, button height, button width, text
        buttonList.add( new GuiButton( (i*buttonX)+j+1, myX, myY, buttonWidth, buttonHeight, buttons[i][j] ) );
      }
    }
  }

  public void drawScreen( int par1, int par2, float par3 )
  {
    this.mc.getTextureManager().bindTexture( texture );
    GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );

    // Draw GUI background
    this.drawTexturedModalRect( xPos, yPos, 0, 0, xTextureSize, yTextureSize );

    // Draw buttons
    super.drawScreen( par1, par2, par3 );
  }

  protected void actionPerformed( GuiButton button )
  {
    switch( button.id )
    {
      case 1:
        sendTypePacket( (char) 1 );
      default:
    }

  }

  public void sendTypePacket( char type )
  {
    ByteArrayOutputStream bos = new ByteArrayOutputStream( 1 );
    DataOutputStream outputStream = new DataOutputStream( bos );
    try
    {
      outputStream.writeChar( type );
    } catch( Exception ex )
    {
      ex.printStackTrace();
    }

    Packet250CustomPayload packet = new Packet250CustomPayload();
    packet.channel = "SmartPipeType";
    packet.data = bos.toByteArray();
    packet.length = bos.size();

    Side side = FMLCommonHandler.instance().getEffectiveSide();
    if( side == Side.SERVER )
    {
      // We are on the server side.
    }
    else if( side == Side.CLIENT )
    {
      // We are on the client side.
    }
    else
    {
      // We are on the Bukkit server.
    }
  }

  public boolean doesGuiPauseGame()
  {
    return false;
  }

}
