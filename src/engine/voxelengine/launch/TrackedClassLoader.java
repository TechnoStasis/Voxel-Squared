package voxelengine.launch;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
/**
 * An extension of the URLClass loader of URLs added through it.
 * @author X1000
 *
 */
public class TrackedClassLoader extends URLClassLoader {

	public ArrayList<URL> urls = new ArrayList<URL>();
	
	public TrackedClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}
	
	@Override
	public void addURL(URL url)
	{
		super.addURL(url);
		urls.add(url);
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException
	{
		return super.loadClass(name);
	}

}
