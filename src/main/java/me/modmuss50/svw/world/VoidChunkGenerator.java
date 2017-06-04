package me.modmuss50.svw.world;

import me.modmuss50.svw.Config;
import net.minecraft.block.BlockTNT;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.List;

public class VoidChunkGenerator implements IChunkGenerator
{

    private final World world;

    public VoidChunkGenerator(World world)
    {
        this.world = world;
    }

    @Override
    public Chunk provideChunk(int x, int z)
    {
        ChunkPrimer chunkprimer = new ChunkPrimer();
        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        chunk.generateSkylightMap();
        byte[] abyte = chunk.getBiomeArray();
        for (int i1 = 0; i1 < abyte.length; ++i1)
        {
            abyte[i1] = (byte) Biome.getIdForBiome(Biome.getBiome(1)); //Plains
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int x, int z)
    {

    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z)
    {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        if (Config.canMobsSpawn) return this.world.getBiome(pos).getSpawnableList(creatureType);
        else return null;
    }

    @Nullable
    @Override
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean p_180513_4_)
    {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z)
    {

    }
}
