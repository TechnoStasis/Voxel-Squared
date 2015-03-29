package voxel.core.launcher;

import voxel.core.VoxelSquared;
import voxel.server.VoxelServer;
import voxelengine.log.LogManager;
import voxelengine.log.Logger;

public class VoxelLauncher {

	public static VoxelSquared clientThread = new VoxelSquared();
	public static VoxelServer serverThread = new VoxelServer();
	
	static Logger log = LogManager.requestLogger("Launcher");
	
	public static void main(String[] args)
	{
		try {
			
			log.info("Starting Client");
			clientThread.start();
			log.info("Starting Server");
			serverThread.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
