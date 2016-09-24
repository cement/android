package com.cement.gl.aster.source;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Utils {

	static String prefix = "com/cement/gl/aster/source/";
	static String[] solarTextures = new String[] { "solar.png", "mercury.png", "venus.png", "earth.png", "mooon.png",
			"mars.png", "jupiter.png", "saturn.png", "uranus.png", "neptune.png", };
	static String[] earthTextures = new String[] { "earth.png", "mooon.png"};
	public static Bitmap[] createSolarSystemTextures() {
		return createTextures(prefix,solarTextures);
	}
	public static Bitmap[] createEarthSystemTextures() {
		return createTextures(prefix,earthTextures);
	}
	private static Bitmap[] createTextures(String prefix,String[] fileNames) {
		Bitmap[] textures = new Bitmap[fileNames.length];
		for (int i = 0; i < fileNames.length; i++) {
			InputStream ins = Utils.class.getClassLoader().getResourceAsStream(prefix + fileNames[i]);
			Bitmap bitmap = BitmapFactory.decodeStream(ins);
			textures[i] = bitmap;
		}
		return textures;
	}
}
