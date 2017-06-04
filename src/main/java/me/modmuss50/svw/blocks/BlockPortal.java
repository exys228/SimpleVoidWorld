package me.modmuss50.svw.blocks;

import me.modmuss50.svw.Config;
import me.modmuss50.svw.SimpleVoidWorld;
import me.modmuss50.svw.world.WorldTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nullable;

public class BlockPortal extends Block
{
    private static final String prefix = ":";

    public BlockPortal()
    {
        super(Material.PORTAL);
        setCreativeTab(CreativeTabs.MISC);
        String name = SimpleVoidWorld.MOD_ID + prefix + "portal";
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(5.0F);
        setResistance(2000.0F);
        setSoundType(SoundType.STONE);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable EnumFacing heldItem, float side, float hitX, float hitY)
    {
        if (!worldIn.isRemote && !playerIn.isSneaking())
        {
            if (worldIn.provider.getDimension() != Config.dimID)
            {
                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, Config.dimID, new WorldTeleporter(playerIn.getServer().worldServerForDimension(Config.dimID), pos));
            }
            else
            {
                FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, 0, new WorldTeleporter(playerIn.getServer().worldServerForDimension(0), pos));
            }
            return true;
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY);
    }

    // TODO: Find another way to make portal block unbreakable in void world.
    @Override
    public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
    {
        if (worldIn.provider.getDimension() == Config.dimID)
        {
            return -1.0F;
        }
        return super.getBlockHardness(blockState, worldIn, pos);
    }
}
