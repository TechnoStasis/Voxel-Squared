package modloader.identity;

import java.io.File;
import java.io.FilenameFilter;

import modloader.core.ModLoader;


public class ModFileFilter implements FilenameFilter{

	@Override
	public boolean accept(File dir, String name) {
		
		return !ModLoader.isLibrary(name);
	}

	
}
