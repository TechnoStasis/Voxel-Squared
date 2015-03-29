package modloader.core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import voxelengine.launch.TrackedClassLoader;

/**
 * A silly class wrapper for the tracked class loader
 * @author X1000
 *
 */
public class ModClassLoader {

	private TrackedClassLoader loader;

	public ModClassLoader(ClassLoader parent) {
		loader = (TrackedClassLoader) parent;
	}

	public void addFile(File file) throws MalformedURLException {
		URL url = file.toURI().toURL();
		loader.addURL(url);
	}
	
	public Class<?> loadClass(String name) throws ClassNotFoundException
	{
		return loader.loadClass(name);
	}

}
