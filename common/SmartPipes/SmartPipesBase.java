package SmartPipes;

import SmartPipes.Base.SmartPipe;
import SmartPipes.blocks.BlockRecipeEncoder;
import SmartPipes.gui.GuiHandler;
import SmartPipes.network.PacketHandler;
import buildcraft.BuildCraftCore;
import buildcraft.core.utils.Localization;
import buildcraft.transport.blueprints.BptItemPipeEmerald;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;

import static buildcraft.BuildCraftTransport.buildPipe;


@Mod(modid = SmartPipesBase.modid, name = "Smart Pipes", version = "0.0.1a",
     dependencies = "required-after:Forge@7.7.2.964,);required-after:BuildCraft|Transport;required-after:BuildCraft|Energy")
@NetworkMod(clientSideRequired=true, serverSideRequired=false,
            channels={"GenericRandom"}, packetHandler = PacketHandler.class)

public class SmartPipesBase
{
  public static final String modid = "SmartPipes";

  public ItemIconProvider itemIconProvider = new ItemIconProvider();

  @Mod.Instance(modid)
  public static SmartPipesBase instance;

  public static Item SmartPipeBase;
  public static int SmartPipeBaseID = 3900;

  public static Block blockRecipeEncoder;

  @Mod.EventHandler
  public void preInitialize( FMLPreInitializationEvent event )
  {
    SmartPipeBase = buildPipe( SmartPipeBaseID, SmartPipe.class, "Smart Pipe" );
    SmartPipeBase.setTextureName( "buildcraft:textures/blocks/pipeItemsGold.png" );

    blockRecipeEncoder = new BlockRecipeEncoder( 3901 );
    GameRegistry.registerBlock( blockRecipeEncoder, "blockRecipeEncoder" );
    LanguageRegistry.addName( blockRecipeEncoder, "Recipe Encoder" );

    BuildCraftCore.itemBptProps[SmartPipeBaseID] = new BptItemPipeEmerald();

    NetworkRegistry.instance().registerGuiHandler( this, new GuiHandler() );
  }

  @ForgeSubscribe
  @SideOnly(Side.CLIENT)
  public void textureHook( TextureStitchEvent.Pre event )
  {
    if( event.map.textureType == 1 )
    {
      itemIconProvider.registerIcons( event.map );
    }
  }

  @Mod.EventHandler
  public void load(FMLInitializationEvent evt)
  {
    Localization.addLocalization("/lang/smartpipes/", "en_US");
  }
}

