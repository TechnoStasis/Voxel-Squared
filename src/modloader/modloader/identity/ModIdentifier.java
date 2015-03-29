package modloader.identity;

import java.io.DataInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.annotation.Annotation;
import modloader.api.Mod;

/** 
 * 
 * A utility class that searches a mod file for classes that contains
 *  the {@link modloader.api.Mod} annotation.
 * 
 * <nl> Depends on the Javassist library.
 * 
 * */
public class ModIdentifier {
	
	private List<String> classNames = new ArrayList<String>();
	private boolean hasClasses = false;
	
	public void identify(File modFile) throws Exception
	{

		try
		{
			JarFile file = new JarFile(modFile);
			
			for(ZipEntry entry : Collections.list(file.entries()))
			{
				if(entry.getName().endsWith(".class"))
				{
					if(!hasClasses)
					hasClasses = true;
					searchFile(file, entry);
				}
			}
			
			file.close();
		} catch(Exception e)
		{
			throw e;
		}
	}
	
	public void searchFile(ZipFile file, ZipEntry entry) throws Exception
	{
		ClassFile classFile = new ClassFile(new DataInputStream(file.getInputStream(entry)));
		AnnotationsAttribute annotations = (AnnotationsAttribute) classFile.getAttribute(AnnotationsAttribute.visibleTag);
		if(annotations != null)
		for(Annotation annotation : annotations.getAnnotations())
		{
			if(annotation.getTypeName().equals(Mod.class.getName()))
			{
				classNames.add(classFile.getName());
			}
		}
	}
	
	public boolean hasMods()
	{
		return !classNames.isEmpty();
	}
	
	public boolean containsClasses()
	{
		return hasClasses;
	}
	
	public List<String> getClassNames()
	{
		return classNames;
	}
	
}
