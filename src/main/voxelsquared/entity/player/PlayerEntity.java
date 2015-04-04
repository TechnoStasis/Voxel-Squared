package voxelsquared.entity.player;

public class PlayerEntity {

	public double x = 0D;
	public double y = 0D;
	
	public double motionX = 0D;
	public double motionY = 0D;
	
	public PlayerEntity()
	{
		
	}
	
	public void update()
	{
		x+=motionX;
		y+=motionY;
		
		motionX-=0.1;
		motionY-=0.1;
	}
	
	public double x()
	{
		return x;
	}
	
	public double y()
	{
		return y;
	}
	
}
