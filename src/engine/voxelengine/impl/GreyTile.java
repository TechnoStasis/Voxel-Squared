package voxelengine.impl;

import org.newdawn.slick.Color;

import voxelengine.world.Block;

public class GreyTile extends Block{

	public GreyTile() {
		super("stillnotgrass");
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Color getColorBind() {
		// TODO Auto-generated method stub
		return Color.gray;
	}

}
