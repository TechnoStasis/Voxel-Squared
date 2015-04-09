package modloader.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * The annotation the marks the class as a mod provider, the class MUST contain the Mod.Init annotation if initialization is to be wished.
 * 
 * Classes are scanned at runtime for anything that contains this annotation, and organized into a list of classes where each are scanned again for
 * any Mod.Init methods. Data is gathered from the annotation and compiled into another list of ModInfoContainers, which contain the mod class and the name.
 * @author X1000
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Mod {

	String name();
	
	String version();
	
	/**
	 * Whether the mod should initialize, if set to false, it will not be activated in the loading phase.
	 * @return
	 */
	boolean shouldInit() default true;
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Init
	{
		
	}
}
