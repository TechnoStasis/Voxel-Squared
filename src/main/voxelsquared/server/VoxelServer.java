package voxelsquared.server;

import voxelengine.log.LogManager;
import voxelengine.log.Logger;
import voxelengine.world.World;
import voxelengine.world.util.WorldUtils;
import voxelsquared.client.VoxelSquared;
import voxelsquared.entity.player.PlayerEntity;

public class VoxelServer extends Thread {

	private World world = null;
	private PlayerEntity entity = null;
	
	private Logger log = LogManager.requestLogger("Server Logger");
	
	public VoxelServer(VoxelSquared client, World world, PlayerEntity player) {
		this.world = world;
		entity = player;
		this.setName("Voxel Squared Server");
	}

	private boolean running = false;
	
	public void run() {
		try {
			Thread.sleep(1000);
			running = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long past = mili();
		long updates = 0l;
		
		world.resetChunks();
		
		while(running && VoxelSquared.instance.running)
		{
			if(!running)
				break;
			
			long now = mili();
			
			long timePast = now - past;
			
			updates += timePast;
			
			if(updates >= 1000)
			{
				log.info("Updates taking too long! Resetting");
				updates = 0;
			}
			
			while(updates >= 1000 / 20)
			{
				update();
				updates-=1000/20;
			}
			
			try {
				past = now;
				
				Thread.sleep(Math.max(1, 1000 / 20 - updates));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		log.info("Shutting down server, saving world...");
		world.save();
	}
	
	public PlayerEntity player()
	{
		return entity;
	}
	
	public World getWorld()
	{
		return world;
	}
	// Called by run loop.
	private void update()
	{
		try {
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void shutdown()
	{
		running = false;
		update();
	}
	
	
	private long mili()
	{
		return System.currentTimeMillis();
	}

}
