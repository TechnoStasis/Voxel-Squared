package voxelengine.world;

import voxelengine.impl.BasicTile;
import voxelengine.impl.GrassTile;
import voxelengine.impl.GreyTile;
import voxelengine.registry.BlockRegistry;

public class Blocks {
	
	public static final Block greyTile = new GreyTile();
	public static final Block grassTile = new GrassTile();	
	public static final Block basicTile = new BasicTile();

	static {
		BlockRegistry.registerBlock(basicTile);
		BlockRegistry.registerBlock(grassTile);
		BlockRegistry.registerBlock(greyTile);
	}
	
}
