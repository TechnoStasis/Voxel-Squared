package voxelengine.impl;

import org.newdawn.slick.Color;

import voxelengine.world.Block;

public class BasicTile extends Block{

	public BasicTile() {
		super("not_grass");
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Color getColorBind() {
		// TODO Auto-generated method stub
		return Color.white;
	}

}
