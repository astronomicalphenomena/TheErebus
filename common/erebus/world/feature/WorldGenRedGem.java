package erebus.world.feature;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

//@formatter:off
public class WorldGenRedGem extends WorldGenerator{

	private static final byte[] offsetX = new byte[]{ -1, 1, 0, 0, 0, 0 },
								offsetY = new byte[]{ 0, 0, -1, 1, 0, 0 },
								offsetZ = new byte[]{ 0, 0, 0, 0, -1, 1 };
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		if (!world.isAirBlock(x,y,z) || world.getBlockId(x,y+1,z)!=ModBlocks.umberstone.blockID) return false;

		world.setBlock(x,y,z,ModBlocks.redGem.blockID);

		for(int attempt=0,xx,yy,zz,dist=2,distUpd=0; attempt<400; ++attempt){
			xx=x+rand.nextInt(dist)-rand.nextInt(dist);
			yy=y-rand.nextInt(dist+4);
			zz=z+rand.nextInt(dist)-rand.nextInt(dist);

			if (world.isAirBlock(xx,yy,zz)){
				int adjacent=0;

				for(int side=0; side<6; ++side){
					if (world.getBlockId(xx+offsetX[side],yy+offsetY[side],zz+offsetZ[side])==ModBlocks.redGem.blockID)++adjacent;
				}

				if (adjacent==1)world.setBlock(xx,yy,zz,ModBlocks.redGem.blockID);
			}
			
			if (++distUpd>22+dist*30){
				dist=Math.min(8,dist+1);
				distUpd=0;
			}
		}

		return true;
	}
}
//@formatter:on