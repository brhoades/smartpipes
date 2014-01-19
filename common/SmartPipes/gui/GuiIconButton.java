package SmartPipes.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiIconButton extends GuiButton
{
  /**
   * The icon displayed on the button.
   */
  public ResourceLocation icon;

  public GuiIconButton( int id, int xPosition, int yPosition, int width, int height, ResourceLocation icon )
  {
    super( id, xPosition, yPosition, width, height, "" );
    this.icon = icon;
  }

  @Override
  public void drawButton( Minecraft minecraft, int par2, int par3 )
  {
    super.drawButton( minecraft, par2, par3 );

    GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
    minecraft.getTextureManager().bindTexture( icon );

    drawTexturedModalRect( this.xPosition + 2, this.yPosition + 2, 0, 0, 16, 16 );
  }
}
