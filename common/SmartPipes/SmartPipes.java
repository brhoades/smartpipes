package SmartPipes;
import SmartPipes.Base.SmartPipe;
import SmartPipes.blocks.BlockRecipeEncoder;
import buildcraft.BuildCraftCore;
import buildcraft.transport.blueprints.BptItemPipeEmerald;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import static buildcraft.BuildCraftTransport.buildPipe;

@Mod(modid = SmartPipes.modid, name = "Smart Pipes", version = "0.0.1a", dependencies = "required-after:Forge@7.7.2.964,);required-after:BuildCraft|Transport;required-after:BuildCraft|Energy")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)

public class SmartPipes
{
  public static final String modid = "SmartPipes";

  public static Item SmartPipeBase;
  public static int SmartPipeBaseID = 500;

  public static Block blockRecipeEncoder;

    @Mod.EventHandler
    public void preInitialize( FMLPreInitializationEvent event )
    {
        SmartPipeBase = buildPipe(SmartPipeBaseID, SmartPipe.class, "Base Smart Pipe" );
        SmartPipeBase.setTextureName("buildcraft:textures/blocks/pipeItemsGold.png");
        SmartPipeBase.setUnlocalizedName( "Base Smart Pipe" );

        blockRecipeEncoder = new BlockRecipeEncoder(1337);
        GameRegistry.registerBlock(blockRecipeEncoder, "blockRecipeEncoder");
        LanguageRegistry.addName(blockRecipeEncoder, "Recipe Encoder");

        BuildCraftCore.itemBptProps[SmartPipeBaseID] = new BptItemPipeEmerald();

    }

}

