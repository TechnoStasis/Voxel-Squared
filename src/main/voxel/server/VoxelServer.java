package voxel.server;

import voxel.core.VoxelSquared;

/**
 * Handles game ticking an
 * 
 * @author X1000
 *
 */
public class VoxelServer extends Thread {

	private boolean running = true;

	private long tickRate = 10 / 1000;
	
	public VoxelServer()
	{
		this.setDaemon(true);
		this.setName("Server thread");
	}
	
	public void run() {
		long past = getMili();
		while (running) {
			long now = getMili();
			long timePast = past - now;
			try {
			VoxelSquared.player.update();
			if(timePast >= tickRate)
			{
				past = now;
				Thread.sleep(1);
			}
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public long getMili()
	{
		return System.currentTimeMillis();
	}
	
}
