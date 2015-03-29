package modloader.core;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.annotation.Annotation;
import modloader.api.Mod;
import modloader.identity.ModFileFilter;
import modloader.identity.ModIdentifier;
import modloader.identity.ModInfoContainer;
import voxelengine.launch.TrackedClassLoader;
import voxelengine.log.LogManager;
import voxelengine.log.Logger;
import voxelengine.util.ReflectionUtils;

/**
 * Searches the classpath and the mods directory for any files that contains mod
 * classes. Files that are not found to contains mods are added as a code
 * library. Blacklists any default libraries.
 * 
 * Collects all valid mod classes for later use.
 * 
 * @since Version 1.0.0
 * @author X1000
 *
 */
public class ModLoader {

	private ModClassLoader loader;
	private List<Class<?>> modClasses = new ArrayList<Class<?>>();
	File dir = new File("mods");
	Pattern fileName = Pattern.compile("(.+).(zip|jar|mod)");

	private static final Logger log = LogManager.requestLogger("VML");

	/**
	 * The list of initialized mod containers.
	 */
	public static final Set<ModInfoContainer> modContainers = new HashSet<ModInfoContainer>();

	static String[] libraries = new String[] { "lwjgl.jar", "slick-util.jar",
			"lwjgl_util.jar", "javassist.jar" };

	public ModLoader(TrackedClassLoader parent) {
		loader = new ModClassLoader(parent);
		dir.mkdirs();
	}

	/**
	 * Search an array of files for any mods.
	 * 
	 * @param files
	 */
	public void searchFiles(File[] files) {
		try {
			Arrays.sort(files);
			for (File file : files) {
				Matcher match = fileName.matcher(file.getName());
				// if(!file.getName().endsWith(".class"))
				// log.info("Inspecting file %s for mods", file.getName());

				if (match.matches() && !file.isDirectory()) {
					searchFile(file);
				} else if (file.isDirectory()) {
					searchFiles(file.listFiles());
				} else if (file.getName().endsWith(".class")) {
					searchClass(file);
				} else {
					log.info("File %s is invalid, ignoring.", file.getName());
				}
			}
		} catch (Exception e) {
			log.err("Exception when searching for file ");
			e.printStackTrace();
		}

	}

	/**
	 * Read a class for mods.
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void searchClass(File file) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		ClassFile classFile = new ClassFile(new DataInputStream(
				new FileInputStream(file)));
		AnnotationsAttribute annotations = (AnnotationsAttribute) classFile
				.getAttribute(AnnotationsAttribute.visibleTag);
		if (annotations == null)
			return;
		for (Annotation annotation : annotations.getAnnotations()) {
			if (annotation.getTypeName().equals(Mod.class.getName())) {
				loader.addFile(file);
				modClasses.add(loader.loadClass(classFile.getName()));
			}
		}
	}

	/**
	 * Reads a file for mods.
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void searchFile(File file) throws Exception {
		ModIdentifier identifier = new ModIdentifier();
		identifier.identify(file);
		if (identifier.hasMods()) {
			log.info(
					"File %s was found to contain mod classes, adding to class loader",
					file.getName());
			loader.addFile(file);

			for (String className : identifier.getClassNames()) {
				Class<?> modClass = loader.loadClass(className);
				modClasses.add(modClass);
			}
		} else if (identifier.containsClasses()) {
			log.err("File %s does not contain a mod, but however contains java binaries, adding to class loader",
					file.getName());
			loader.addFile(file);
		}
	}

	/**
	 * Finds mods in classpath and mods folder, then loads all of them.
	 * 
	 * @throws Exception
	 */
	public void loadMods() {
		try {
			log.info("Mod load and search starting.");
			log.info("Searching mod directory for mods.");
			searchFiles(dir.listFiles(new ModFileFilter()));

			log.info("Searching classpath for mods.");
			searchFiles(ModLoader.getClasspathLibraries());
			log.info("Mod Loader search complete, found %s classes", modClasses.size());
			log.info("Loading found %s.", modClasses.size() > 0 ? "mods" : "mod");

			if(modClasses.size() <= 0)
				return;
			
			for (Class<?> modClass : modClasses) {

				if (modClass.getAnnotation(Mod.class).shouldInit()) {
					for (Method method : ReflectionUtils.getClassMethods(modClass)) {
						if (method.getAnnotation(Mod.Init.class) != null) {
							method.invoke(null);
						}
					}
				}

				Mod modAnnotation = modClass.getAnnotation(Mod.class);
				try {
				modContainers.add(ModInfoContainer.createModContainer(modClass, modAnnotation.name()));
				} catch(Exception e)
				{
					log.err("Caught exception initializing mod info container for %s", modAnnotation.name());
					e.printStackTrace();
				}
			}

			log.info("Mod Loader finished mod loading, %s mods loaded", modContainers.size());
		} catch (Exception e) {
			log.err("Error caught when loading mods.");
			e.printStackTrace();
		}

	}

	public static File[] getClasspathLibraries() {
		String[] pathElements = System.getProperty("java.class.path").split(File.pathSeparator);

		List<File> pathFiles = new ArrayList<>();
		for (String pathElement : pathElements) {
			{
				File file = new File(pathElement);
				if (file.exists() && !isLibrary(file.getName())) {
					pathFiles.add(file);
				}
			}
		}
		return pathFiles.toArray(new File[] {});
	}

	public static boolean isLibrary(String fileName) {

		for (String names : libraries) {
			if (names.equals(fileName))
				return true;
		}

		return false;
	}
}
