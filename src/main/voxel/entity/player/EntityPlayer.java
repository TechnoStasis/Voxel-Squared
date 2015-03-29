package voxel.entity.player;

import org.lwjgl.input.Keyboard;

import voxelengine.datafile.DataHolder;

public class EntityPlayer {

	public double xCoord = 0;
	public double yCoord = 0;
	
	public EntityPlayer()
	{
		
	}
	
	public void onDataLoad(DataHolder data)
	{
		
	}
	
	public void onDataSave(DataHolder data)
	{
		
	}
	
	public void update()
	{
		if(!Keyboard.isCreated())
			return;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			yCoord+=0.01;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			yCoord-=0.01;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			xCoord-=0.01;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			xCoord+=0.01;	
		}
	}
	
}
