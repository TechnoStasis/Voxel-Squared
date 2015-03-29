package voxelengine.render;

import org.lwjgl.opengl.GL11;

public class Square {

	public double width;
	public double height;
	
	public double xStart;
	public double yStart;
	
	public Square(double x, double y, double w, double h)
	{
		width = w;
		height = h;
		xStart = x;
		yStart = y;
	}
	/**
	 * Invokes necessary render code for rendering a box.
	 * 
	 * Assumes glPushMatrix and glPopMatrix are called before and after calling this method.
	 *  */
	public void renderSquare()
	{
		GL11.glBegin(GL11.GL_QUADS);
		RenderHelper.addVertexWithTexCoords(xStart-width, yStart-height, 0, 0);
		RenderHelper.addVertexWithTexCoords(xStart+width, yStart-height, 1, 0);
		RenderHelper.addVertexWithTexCoords(xStart+width, yStart+height, 1, 1);
		RenderHelper.addVertexWithTexCoords(xStart-width, yStart+height, 0, 1);
		GL11.glEnd();
	}
}
