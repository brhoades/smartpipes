package SmartPipes;

import SmartPipes.Base.SmartPipe;
import buildcraft.BuildCraftCore;
import buildcraft.transport.blueprints.BptItemPipeEmerald;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;

import static buildcraft.BuildCraftTransport.buildPipe;

@Mod(modid = SmartPipes.modid, name = "Smart Pipes", version = "0.0.1a", dependencies = "required-after:Forge@7.7.2.964,);required-after:BuildCraft|Transport;required-after:BuildCraft|Energy")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)

public class SmartPipes
{
  public static final String modid = "SmartPipes";

  public ItemIconProvider itemIconProvider = new ItemIconProvider();

  @Mod.Instance(modid)
  public static SmartPipes instance;

  public static Item SmartPipeBase;
  public static int SmartPipeBaseID = 500;

  @Mod.EventHandler
  public void preInitialize( FMLPreInitializationEvent event )
  {
    SmartPipeBase = buildPipe(SmartPipeBaseID, SmartPipe.class, "Base Smart Pipe" );
    SmartPipeBase.setTextureName("buildcraft:textures/blocks/pipeItemsGold.png");
    SmartPipeBase.setUnlocalizedName( "Base Smart Pipe" );

    BuildCraftCore.itemBptProps[SmartPipeBaseID] = new BptItemPipeEmerald();
  }

  @ForgeSubscribe
  @SideOnly(Side.CLIENT)
  public void textureHook(TextureStitchEvent.Pre event)
  {
    if (event.map.textureType == 1)
    {
      itemIconProvider.registerIcons( event.map );
    }
  }
}

