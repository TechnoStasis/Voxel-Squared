package voxelsquared.client;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import voxelengine.launch.ModLauncher;
import voxelengine.render.Square;
import voxelengine.world.World;
import voxelengine.world.util.WorldUtils;
import voxelsquared.client.render.world.WorldRenderer;
import voxelsquared.entity.player.PlayerEntity;
import voxelsquared.server.VoxelServer;

public class VoxelSquared extends Thread{

	public static final VoxelSquared instance = new VoxelSquared();

	public boolean running = true;

	private VoxelServer server = null;
	
	private WorldRenderer worldRenderer = null;
	
	
	public VoxelSquared()
	{
		this.setName("Voxel Squared Client");
	}
	
	public void setup() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));

			Display.create();
			Keyboard.create();
			Mouse.create();

			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, 800, 0, 600, 1, -1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);

			ModLauncher.instance.launchModLoader();
			
			server = new VoxelServer(this, new World(), new PlayerEntity());
			worldRenderer = new WorldRenderer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		
		setup();
		
		while (running) {
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Display.isCloseRequested()) {
				running = false;
				server.shutdown();
			}
			
			if(!server.isAlive())
				server.start();
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			GL11.glPushMatrix();

		    GL11.glTranslatef(-((float) server.player().x() - Display.getWidth() / 2), -((float)server.player().y() - Display.getHeight() / 2), 0);


		    int playerX = WorldUtils.convertToChunkCoord((int) server.player().x());
		    int playerY = WorldUtils.convertToChunkCoord((int) server.player().y());
		    
		    //worldRenderer.renderChunk(playerX, playerY, server.getWorld());
		    
			Color.red.bind();
			Square s = new Square((float) server.player().x(),(float)  server.player().y(), 10 ,10);
			s.renderSquare();

			GL11.glPopMatrix();

			//Ah screw it, place holder.
			
			if(Keyboard.isKeyDown(Keyboard.KEY_W))
			{
				server.player().setY(server.player().y() + 0.5D);
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_S))
			{
				server.player().setY(server.player().y() - 0.5D);
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_D))
			{
				server.player().setX(server.player().x() + 0.5D);
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_A))
			{
				server.player().setX(server.player().x() - 0.5D);
			}
			
			Display.sync(60);
			Display.update();
		}

		Display.destroy();
	}

}
