package SmartPipes;

import SmartPipes.api.BuildCraftHandle;
import SmartPipes.blocks.BlockRecipeEncoder;
import SmartPipes.gui.GuiHandler;
import SmartPipes.misc.CreativeTabSP;
import SmartPipes.network.PacketHandler;
import buildcraft.BuildCraftCore;
import buildcraft.transport.blueprints.BptItemPipeEmerald;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
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

@Mod(modid = SmartPipes.modid, name = "Smart Pipes", version = "0.0.1a",
     dependencies = "required-after:Forge@7.7.2.964,);required-after:BuildCraft|Transport;required-after:BuildCraft|Energy")
@NetworkMod(clientSideRequired=true, serverSideRequired=false,
            channels={"GenericRandom"}, packetHandler = PacketHandler.class)

public class SmartPipes
{
  public static final String modid = "SmartPipes|Main";

  @Mod.Instance(modid)

  public static SmartPipes instance;

  public static CreativeTabSP creativeTabSP = new CreativeTabSP( );

  public BuildCraftHandle buildCraftHandle;
  public ItemIconProvider itemIconProvider = new ItemIconProvider();

  public static Item SmartPipeBase;
  public static int SmartPipeBaseID = 3902;

  public static Block blockRecipeEncoder;
  public static int BlockRecipeEncoderID = 3901;

  @Mod.EventHandler
  public void preInitialize( FMLPreInitializationEvent event )
  {
    blockRecipeEncoder = new BlockRecipeEncoder( BlockRecipeEncoderID );
    GameRegistry.registerBlock( blockRecipeEncoder, "blockRecipeEncoder" );
    LanguageRegistry.addName( blockRecipeEncoder, "Recipe Encoder" );

    BuildCraftCore.itemBptProps[SmartPipeBaseID] = new BptItemPipeEmerald();

    NetworkRegistry.instance().registerGuiHandler( this, new GuiHandler() );
  }

  @Mod.EventHandler
  public void init(FMLInitializationEvent evt)
  {
    // This sets up our Localizations, registers our pipes, and does nice stuff for us.
    buildCraftHandle = new BuildCraftHandle( );

    if( evt.getSide( ).isClient( ) )
    {
      buildCraftHandle.registerLocalizations();
    }
  }

  @Mod.EventHandler
  public void postInitialize( FMLPostInitializationEvent event )
  {
    buildCraftHandle.registerPipes( event.getSide( ) );
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
}

