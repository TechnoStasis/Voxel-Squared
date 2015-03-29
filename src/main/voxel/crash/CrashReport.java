package voxel.crash;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Objects;

import voxel.core.VoxelSquared;
import voxelengine.launch.LaunchData;
import voxelengine.log.Logger;

public class CrashReport {

	Logger log = new Logger("Crash Reporter", false);

	Throwable error;

	String desc;
	
	private final boolean generateFile = false;

	public CrashReport(Throwable e, String desc) {		
		error = Objects.requireNonNull(e);
		this.desc = desc;
		
		if (e instanceof OutOfMemoryError) {
			// Extremely vain attempt at freeing up enough memory for the crash report
			System.gc();
		}
	}

	public String toString()
	{
		StringBuilder b = new StringBuilder();
		b.append("\n");
		b.append("Voxel Squared Crash Report: ");
		b.append("\n\n");
		b.append("Exception while: " + desc);
		b.append("\n\n");
		
		b.append(error.toString());
		
		for(StackTraceElement element : error.getStackTrace())
		{
			b.append("\n");
			b.append("   " + element.toString());
		}
		
		b.append("\n\n");
		
		b.append("Game Details:");
		b.append("\n");
		b.append("Voxel Squared Version: " + VoxelSquared.VERSION);
		b.append("\n");
		b.append("Game State: " + VoxelSquared.getGameState());
		b.append("\n");
		b.append("Save File Format: " + VoxelSquared.FILE_FORMAT);
		
		b.append("\n");
		b.append("System Details: \n");
		b.append("Operating System: " + LaunchData.instance.os);
		b.append("\n");
		b.append("Java Virtual Machine: " + LaunchData.instance.vm);
		
		return b.toString();
	}
	
	public void print() {
		/*StackTraceElement[] elements = error.getStackTrace();

		log.log(" --- Voxel Squared Crash Report --- ");
		log.fill();
		log.log("Description: " + desc);
		log.fill();
		log.log(error.toString());

		for (StackTraceElement element : elements) {
			log.log("  " + element.toString());
		}

		for (int i = 0; i < 1; i++)
			log.fill();
		
		log.log(" -- Game State Details -- ");
		log.log("Voxel Squared Version: " + VoxelSquared.VERSION);
		log.log("Game State: " + VoxelSquared.state);*/
		
		log.info(toString());
	}
	
	public void createCrashFile()
	{
		if(!generateFile)
			return;
		
		try {
			log.info(toString(), new PrintStream("crash_"+ System.currentTimeMillis() + ".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void throwException() throws Throwable
	{
		throw error;
	}

}
