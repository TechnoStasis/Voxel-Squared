package voxelsquared.client;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import voxelengine.world.World;
import voxelsquared.entity.player.PlayerEntity;
import voxelsquared.server.VoxelServer;

public class VoxelSquared {
	
	public static final VoxelSquared instance = new VoxelSquared();
	
	private boolean running = true;
	
	private VoxelServer server = null;
	
	public void setup()
	{
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			
			Display.create();
			Keyboard.create();
			Mouse.create();
			
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, 800, 0, 600, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			
			server = new VoxelServer(new World(), new PlayerEntity());
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		run();
	}
	
	public void run()
	{
		
		server.run();
		
		while(running)
		{
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
				running = false;
			
	        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
			
			
			Display.sync(60);
			Display.update();
		}
		
		Display.destroy();
	}
	
}
