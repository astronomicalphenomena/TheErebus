package erebus.world.biomes.decorators;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.block.BlockDoubleHeightPlant;
import erebus.world.biomes.decorators.data.OreSettings;
import erebus.world.biomes.decorators.data.OreSettings.OreType;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.feature.decoration.WorldGenAmberGround;
import erebus.world.feature.decoration.WorldGenAmberUmberstone;
import erebus.world.feature.decoration.WorldGenPonds;
import erebus.world.feature.decoration.WorldGenRottenAcacia;
import erebus.world.feature.decoration.WorldGenSavannahRock;
import erebus.world.feature.plant.WorldGenBamboo;
import erebus.world.feature.tree.WorldGenAcaciaTree;
import erebus.world.feature.tree.WorldGenAsperTree;

//@formatter:off
public class BiomeDecoratorSubterraneanSavannah extends BiomeDecoratorBaseErebus{
	private final WorldGenPonds genPonds = new WorldGenPonds();
	private final WorldGenBamboo genBamboo = new WorldGenBamboo(7,true);
	private final WorldGenSavannahRock genRocks = new WorldGenSavannahRock();
	private final WorldGenRottenAcacia genRottenAcacia = new WorldGenRottenAcacia();
	private final WorldGenAmberGround genAmberGround = new WorldGenAmberGround();
	private final WorldGenAmberUmberstone genAmberUmberstone = new WorldGenAmberUmberstone();

	private final WorldGenTallGrass genGrass = new WorldGenTallGrass(ModBlocks.erebusGrass,1);

	private final WorldGenerator genTreeAcacia = new WorldGenAcaciaTree();
	private final WorldGenerator genTreeAsper = new WorldGenAsperTree();

	@Override
	public void populate(){
		if (rand.nextBoolean() && rand.nextBoolean()){
			for(attempt = 0; attempt < 8; attempt++){
				xx = x+16;
				yy = rand.nextInt(120);
				zz = z+16;

				if (checkSurface(SurfaceType.GRASS,xx,yy,zz)){
					genPonds.prepare((rand.nextDouble()+0.75D)*1.2D);
					genPonds.generate(world,rand,xx,yy,zz);
				}
			}

			if (rand.nextInt(3) != 0){
				for(yy = 100; yy > 20; yy--){
					xx = x+offsetXZ();
					zz = z+offsetXZ();

					if (checkSurface(SurfaceType.GRASS,xx,yy,zz)){
						genBamboo.generate(world,rand,xx,yy,zz);
					}
				}
			}
		}
	}

	@Override
	public void decorate(){
		if (rand.nextInt(12) == 0){
			for(attempt = 0; attempt < 5; attempt++){
				if (genAmberUmberstone.generate(world,rand,x+offsetXZ(),rand.nextInt(120),z+offsetXZ()))break;
			}
		}

		if (rand.nextInt(24) == 0){
			for(attempt = 0; attempt < 4; attempt++){
				if (genAmberGround.generate(world,rand,x+offsetXZ(),10+rand.nextInt(40),z+offsetXZ()))break;
			}
		}

		for(attempt = 0; attempt < 65; attempt++){
			xx = x+offsetXZ();
			yy = 15+rand.nextInt(90);
			zz = z+offsetXZ();

			if (checkSurface(SurfaceType.GRASS,xx,yy,zz)){
				genTreeAcacia.generate(world,rand,xx,yy,zz);
			}
		}

		if (rand.nextBoolean() && rand.nextBoolean()){
			xx = x+offsetXZ();
			zz = z+offsetXZ();

			for(yy = 100; yy > 20; yy--){
				if (checkSurface(SurfaceType.GRASS,xx,yy,zz)){
					genRocks.generate(world,rand,xx,yy,zz);
				}
			}
		}

		for(attempt = 0; attempt < 10; attempt++){
			xx = x+offsetXZ();
			yy = rand.nextInt(120);
			zz = z+offsetXZ();

			if (checkSurface(SurfaceType.GRASS,xx,yy,zz)){
				genTreeAsper.generate(world,rand,xx,yy,zz);
			}
		}

		for(attempt = 0; attempt < 28; attempt++){
			xx = x+offsetXZ();
			yy = 15+rand.nextInt(90);
			zz = z+offsetXZ();

			if (checkSurface(SurfaceType.GRASS,xx,yy,zz)){
				genRottenAcacia.generate(world,rand,xx,yy,zz);
			}
		}

		for(attempt = 0; attempt < 180; attempt++){
			xx = x+offsetXZ();
			yy = 15+rand.nextInt(90);
			zz = z+offsetXZ();

			if (checkSurface(SurfaceType.GRASS,xx,yy,zz)){
				genGrass.generate(world,rand,xx,yy,zz);
			}
		}

		for(attempt = 0; attempt < 40; attempt++){
			xx = x+offsetXZ();
			yy = 20+rand.nextInt(80);
			zz = z+offsetXZ();

			if (checkSurface(SurfaceType.GRASS,xx,yy,zz) && world.isAirBlock(xx,yy+1,zz)){
				world.setBlock(xx,yy,zz,ModBlocks.doubleHeightPlant,BlockDoubleHeightPlant.dataTallGrassBottom,2);
				world.setBlock(xx,yy+1,zz,ModBlocks.doubleHeightPlant,BlockDoubleHeightPlant.dataTallGrassTop,2);
			}
		}
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void modifyOreGen(OreSettings oreGen, OreType oreType, boolean extraOres){
		switch(oreType){
			case GOLD:
				oreGen.setChance(0.75F).setIterations(extraOres ? 1 : 2).setY(50,126).generate(world,rand,x,z); // split into 3 parts, higher areas have less gold
				oreGen.setChance(0.9F).setIterations(extraOres ? 1 : 2,extraOres ? 2 : 3).setY(25,50).generate(world,rand,x,z); // balanced so there are more veins per chunk
				oreGen.setIterations(extraOres ? 2 : 3,extraOres ? 3 : 4).setY(5,25);
				break;
			case EMERALD: oreGen.setChance(0.22F); break; // less common
			case DIAMOND: oreGen.setChance(0.4F).setIterations(1,2).setOreAmount(2).setY(5,16); break; // 2 diamonds per cluster, ~7-8 times smaller area thus lowered chance and iterations
			case JADE: oreGen.setChance(0.3F).setIterations(0,3); break; // less common
			case PETRIFIED_WOOD: oreGen.setChance(0.5F).setIterations(0,extraOres ? 2 : 3).setY(5,64); break; // less common and lowered area ~2 times, thus lowered chance and iterations
		}
	}
}
// @formatter:on