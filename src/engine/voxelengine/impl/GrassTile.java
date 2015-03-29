package voxelengine.impl;

import org.newdawn.slick.Color;

import voxelengine.render.Square;
import voxelengine.world.Block;

public class GrassTile extends Block{

	public GrassTile() {
		super("grass");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(int x, int y) {
		Square square = new Square(x, y, 1, 1);
		Color.green.bind();
		square.renderSquare();
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
