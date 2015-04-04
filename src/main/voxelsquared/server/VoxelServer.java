package voxelsquared.server;

import voxelengine.world.World;
import voxelsquared.entity.player.PlayerEntity;

public class VoxelServer extends Thread {

	private World world = null;
	private PlayerEntity entity = null;

	public VoxelServer(World world, PlayerEntity player) {
		this.world = world;
		entity = player;
	}

	private boolean running = false;
	
	public void run() {
		try {
			Thread.sleep(1);
			running = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long past = mili();
		long updates = 0l;
		
		while(running)
		{
			long now = mili();
			
			long timePast = now - past;
			
			updates+=timePast;
			
			while(updates >= 1000 / 30)
			{
				update();
			}
			
			try {
				Thread.sleep(1000 / 30 - updates);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void update()
	{
		try {
			entity.update();
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private long mili()
	{
		return System.currentTimeMillis();
	}

}
