package voxel.core;

import java.io.File;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import voxel.crash.CrashReport;
import voxel.entity.player.EntityPlayer;
import voxelengine.impl.BasicTile;
import voxelengine.impl.GrassTile;
import voxelengine.impl.GreyTile;
import voxelengine.info.Info;
import voxelengine.launch.LaunchData;
import voxelengine.log.LogManager;
import voxelengine.log.Logger;
import voxelengine.registry.BlockRegistry;
import voxelengine.render.Square;
import voxelengine.state.StateInfo;
import voxelengine.world.Block;
import voxelengine.world.World;


public class VoxelSquared extends Thread {

	public static final String VERSION = "1.0.0";
	public static final File saveDir = new File("saves");
	
	public static EntityPlayer player = new EntityPlayer();
	private static StateInfo state = StateInfo.INITIALIZED;
	public static final String FILE_FORMAT = "Named Binary Tag - CUSTOM";
	
	public static final Logger logger = LogManager.requestLogger("Client");
	
	public World world = new World();

	Square square;
	Square playerLoc;
	boolean worldThreadRunning = false;
	
	public VoxelSquared() {
		this.setName("Voxel Squared Client");
		saveDir.mkdirs();
	}

	public void run() {
		setupEnv();
		LaunchData.instance.modLoader.loadMods();
		loop();
		
		Display.destroy();
	}

	public static Block grass = new GrassTile();
	public static Block greyTile = new GreyTile();
	public static Block basicTile = new BasicTile();
	
	
	public void setupEnv() {
		logger.info("Starting Voxel Squared Version: " + VERSION);
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setTitle("Voxel Squared");
			Display.setResizable(true);
			Display.create();

			Keyboard.create();
			Mouse.create();
			
			BlockRegistry.incrementID();
			BlockRegistry.registerBlock(grass);
			BlockRegistry.registerBlock(greyTile);
			BlockRegistry.registerBlock(basicTile);
			
		} catch (Exception e) {

			CrashReport report = new CrashReport(e, "Initializing display.");
			report.print();
			report.createCrashFile();
			System.exit(1);
		}

		world.gen(saveDir);
		
		logger.info("Startup complete");
		state = StateInfo.INITIALIZED;
	}
	
	public void loop() {
		state = StateInfo.RUNNING;
		try {
			while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				if (!worldThreadRunning) {
					worldThreadRunning = true;
				}
				GL11.glMatrixMode(GL11.GL_PROJECTION);
				GL11.glLoadIdentity();
				GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), 1, -1);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);

				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
				GL11.glPushMatrix();

				GL11.glTranslated(-(player.xCoord - Display.getWidth() / 2), -(player.yCoord - Display.getHeight() / 2), 0);
				
				
				GL11.glPushMatrix();
				Color.white.bind();
				
			 	int px = (int) Math.ceil(player.xCoord);
				int py = (int) Math.ceil(player.yCoord);
				
				//world.renderAll();
				
				int cx = (int) Math.floor(px / Info.CHUNK_SIZE);
				int cy = (int) Math.floor(py / Info.CHUNK_SIZE);
				
				world.render((int) Math.floor(px / Info.CHUNK_SIZE), (int) Math.floor(py / Info.CHUNK_SIZE));
				
				GL11.glPopMatrix();

				square = new Square((int) player.xCoord, (int) player.yCoord, 10, 10);
				Color.red.bind();
				square.renderSquare();

				GL11.glPopMatrix();
				Display.sync(200);
				Display.update();
			}
		} catch (Throwable e) {
			CrashReport report = new CrashReport(e, "Running game.");
			report.print();
			report.createCrashFile();
			System.exit(1);
		}
		
		try {
			world.save(saveDir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		state = StateInfo.EXITING;
	}

	public void updateLogistics()
	{

		player.update();
		
	 	int px = (int) Math.ceil(player.xCoord);
		int py = (int) Math.ceil(player.yCoord);
		
	//	int mx = ((int) Math.ceil(player.xCoord) + Mouse.getX()) / 10;
	//	int my = ((int) Math.ceil(player.yCoord) + Mouse.getY()) / 10;
	}
	
	public static StateInfo getGameState() {
		return state;
	}
}
