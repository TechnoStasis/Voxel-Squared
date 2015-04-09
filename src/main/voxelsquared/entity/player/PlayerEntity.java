package voxelsquared.entity.player;

public class PlayerEntity {

	private double x = 0D;
	private double y = 0D;
	
	public PlayerEntity()
	{
		
	}
	
	public void update()
	{
		System.out.println(String.format("X: %s, Y: %s", x, y));
	}
	
	public double x()
	{
		return x;
	}
	
	public double y()
	{
		return y;
	}
	
	public void setX(double d)
	{
		x = d;
	}
	
	public void setY(double d)
	{
		y = d;
	}
	
}
