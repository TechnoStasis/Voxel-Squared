package voxelengine.impl;

import org.newdawn.slick.Color;

import voxelengine.world.Block;

public class GrassTile extends Block{

	public GrassTile() {
		super("grass");
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
		return Color.green;
	}

}
