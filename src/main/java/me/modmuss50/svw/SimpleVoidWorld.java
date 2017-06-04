package me.modmuss50.svw;

import me.modmuss50.svw.blocks.BlockPortal;
import me.modmuss50.svw.proxy.CommonProxy;
import me.modmuss50.svw.world.VoidWorldProvider;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = SimpleVoidWorld.MOD_ID, name = "SimpleVoidWorld", version = "@MODVERSION@")
public class SimpleVoidWorld
{
    public static BlockPortal portal;

    public static final String MOD_ID = "simplevoidworld";

    @SidedProxy(clientSide = "me.modmuss50.svw.proxy.ClientProxy", serverSide = "me.modmuss50.svw.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static DimensionType type;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Config.load(event.getSuggestedConfigurationFile());

        portal = new BlockPortal();
        this.RegisterBlock(portal);

        proxy.init();

        type = DimensionType.register(MOD_ID, "void", Config.dimID, VoidWorldProvider.class, false);
        DimensionManager.registerDimension(Config.dimID, type);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        GameRegistry.addShapedRecipe(new ItemStack(portal),
                "OEO",
                "EDE",
                "OEO",

                'O', Blocks.OBSIDIAN,
                'E', Items.ENDER_EYE,
                'D', Blocks.DIAMOND_BLOCK);
    }

    public void RegisterBlock(Block block)
    {
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block), block.getRegistryName());
    }
}
