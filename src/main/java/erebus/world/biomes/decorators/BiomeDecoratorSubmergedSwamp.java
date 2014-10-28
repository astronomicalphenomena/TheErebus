package erebus.world.biomes.decorators;

import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.world.biomes.decorators.data.FeatureType;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenPonds;
import erebus.world.feature.decoration.WorldGenQuickSand;
import erebus.world.feature.decoration.WorldGenRottenAcacia;
import erebus.world.feature.decoration.WorldGenSwampWater;
import erebus.world.feature.plant.WorldGenMossPatch;

public class BiomeDecoratorSubmergedSwamp extends BiomeDecoratorBaseErebus
{
	private final WorldGenRottenAcacia genRottenAcacia = new WorldGenRottenAcacia();
	private final WorldGenSwampWater genMire = new WorldGenSwampWater(Blocks.water);
	private final WorldGenPonds genPonds = new WorldGenPonds();
	private final WorldGenQuickSand genQuickSand = new WorldGenQuickSand();
	private final WorldGenTallGrass genFerns = new WorldGenTallGrass(ModBlocks.fern, 1);
	private final WorldGenTallGrass genFiddleheads = new WorldGenTallGrass(ModBlocks.fiddlehead, 1);
	private final WorldGenTallGrass genSwampPlant = new WorldGenTallGrass(ModBlocks.swampPlant, 1);
	protected final WorldGenerator genMossPatch = new WorldGenMossPatch(0);
	
	@Override
	protected void populate() {
		
		for (attempt = 0; attempt < 10; attempt++)
		{
			xx = x + 16;
			yy = rand.nextInt(120);
			zz = z + 16;

			if (checkSurface(SurfaceType.MIXED, xx, yy, zz))
			{
				genMire.generate(world, rand, xx, yy, zz);
			}
		}
		
		for (attempt = 0; attempt < 1000; attempt++)
		{
			xx = x + 16;
			yy = rand.nextInt(120);
			zz = z + 16;

			if (checkSurface(SurfaceType.MIXED, xx, yy, zz))
			{
				genPonds.prepare((rand.nextDouble() + 1.5D) * 1.5D);
				genPonds.generate(world, rand, xx, yy, zz);
			}
		}
	}
	
	@Override
	public void decorate()
	{
		for (attempt = 0; attempt < 10; attempt++)
		{
			xx = x + offsetXZ();
			yy = 30 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (world.isAirBlock(xx, yy, zz))
			{
				genMossPatch.generate(world, rand, xx, yy, zz);
			}
		}
			
		for (attempt = 0; attempt < rand.nextInt(3); attempt++)
		{
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(25) * (1 + rand.nextInt(3)); // more likely in lower levels
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.MIXED, xx, yy, zz))
			{
				genRottenAcacia.generate(world, rand, xx, yy, zz);
			}
		}
		
		int offset;
		for (attempt = 0; attempt < 800; attempt++)
		{
			xx = x + offsetXZ();
			yy = 30 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (world.isAirBlock(xx, yy, zz))
			{
				offset = rand.nextInt(4);

				if (!world.getBlock(xx + Direction.offsetX[offset], yy, zz + Direction.offsetZ[offset]).isNormalCube())
				{
					continue;
				}

				for (int vineY = rand.nextInt(30); vineY > 0; vineY--)
				{
					if (world.isAirBlock(xx + Direction.offsetX[offset], yy - vineY, zz + Direction.offsetZ[offset]))
					{
						world.setBlock(xx + Direction.offsetX[offset], yy - vineY, zz + Direction.offsetZ[offset], Blocks.vine, offset == 3 ? 1 : offset == 2 ? 4 : offset == 1 ? 0 : 2, 3);
					}
				}
			}
		}
		
		for (attempt = 0; attempt < 40; attempt++)
		{
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
			{
				genFerns.generate(world, rand, xx, yy, zz);
			}
		}

		for (attempt = 0; attempt < 16; attempt++)
		{
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
			{
				genFiddleheads.generate(world, rand, xx, yy, zz);
			}
		}

		for (attempt = 0; attempt < 180; attempt++)
		{
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.GRASS, xx, yy, zz) && world.isAirBlock(xx, yy + 1, zz))
			{
				boolean fern = rand.nextInt(4) == 0;
				world.setBlock(xx, yy, zz, Blocks.double_plant, fern ? 3 : 2, 2);
				world.setBlock(xx, yy + 1, zz, Blocks.double_plant, 10, 2);
			}
		}

		for (attempt = 0; attempt < 200; attempt++)
		{
			xx = x + offsetXZ();
			yy = 20 + rand.nextInt(80);
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
			{
				genSwampPlant.generate(world, rand, xx, yy, zz);
			}
		}
		
		for (attempt = 0; attempt < 30; attempt++)
		{
			xx = x + offsetXZ();
			yy = rand.nextInt(120);
			zz = z + offsetXZ();

			if (checkSurface(SurfaceType.GRASS, xx, yy, zz))
			{
				genQuickSand.generate(world, rand, xx, yy, zz);
			}
		}
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres)
	{
		switch (oreType)
		{
			case COAL:
				oreGen.setChance(0.85F).setIterations(extraOres ? 2 : 3).setOreAmount(7, 10).setY(5, 56);
				break; // less common, lowered amount too, also ~2 times smaller area
			case EMERALD:
				oreGen.setIterations(1, 3);
				break; // one more vein
			case DIAMOND:
				oreGen.setIterations(3, 4);
				break; // one more vein
			case PETRIFIED_WOOD:
				oreGen.setIterations(extraOres ? 1 : 2, extraOres ? 2 : 3).setY(20, 64).setCheckArea(3);
				break; // more common, but ~1.5 times smaller area
			case FOSSIL:
				oreGen.setChance(0.06F);
				break; // more rare
		}
	}

	@Override
	public void generateFeature(FeatureType featureType)
	{
		if (featureType == FeatureType.REDGEM)
		{
			for (attempt = 0; attempt < 8; attempt++)
			{
				genRedGem.generate(world, rand, x + offsetXZ(), 64 + rand.nextInt(60), z + offsetXZ());
			}
		} else
		{
			super.generateFeature(featureType);
		}
	}
}