package SmartPipes.blocks;

import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockRecipeEncoder extends Block
{
    public BlockRecipeEncoder(int id)
    {
        super(id, Material.wood);
        setHardness(2.0F);
        setResistance(5.0F);
        setUnlocalizedName("blockRecipeEncoder");
        setCreativeTab(CreativeTabs.tabBlock);
    }

    public boolean isOpaqueCube()
    {
        return true;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
    {
        int whichDirectionFacing = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, whichDirectionFacing, 2);
    }

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        icons = new Icon[2];
        icons[0] = iconRegister.registerIcon("smartpipes:genericPanel");
        icons[1] = iconRegister.registerIcon("smartpipes:systemServerFrontPanel");
    }

    public Icon getIcon(int side, int metadata)
    {
        if (side == 1) return icons[0];
        else if (side == 0) return icons[0];
        else if (metadata == 2 && side == 2) return icons[1];
        else if (metadata == 3 && side == 5) return icons[1];
        else if (metadata == 0 && side == 3) return icons[1];
        else if (metadata == 1 && side == 4) return icons[1];
        else return icons[0];
    }

}
