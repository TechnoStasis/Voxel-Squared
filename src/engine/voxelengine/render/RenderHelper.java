package voxelengine.render;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import voxelengine.log.Logger;


/**
 * A rather crude render helper
 * @author X1000
 *
 */
public class RenderHelper {

	Logger log = new Logger("Render Helper");
	
	/**
	 * 
	 * Holds textures that were loaded before, so calling for loadTexture does not take up much memory;
	 *  
	 *  */
	public HashMap<String, Texture> loadedTextures = new HashMap<String, Texture>();

	public static void addVertexWithTexCoords(double x, double y, double tx, double ty) {
		GL11.glVertex2d(x, y);
		GL11.glTexCoord2d(tx, ty);
	}

	/**
	 * 
	 * Loads the texture with SlickUtils texture loader, or retrieves it from the texture map.
	 * 
	 * */
	public Texture loadTexture(String location) {
		Texture tex = loadedTextures.get("libs/" + location);
		if (tex == null) {
			try {
				FileInputStream str = new FileInputStream(new File("libs/", location));

				tex = TextureLoader.getTexture("PNG", str);
			} catch (Exception e) {
				log.err("Unable to find texture in %s", location);
				e.printStackTrace();
			}
		}
		return tex;
	}

	public void bindTexture(Texture tex) {
		if (tex != null) {
			tex.bind();
		} else {
			log.err("Unable to bind null texture");
		}
	}
	
	public static int genID() {
	    return GL11.glGenLists(1);
	}
}
