package com.cement.gl.aster;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.opengl.GLUtils;
import com.cement.gl.aster.source.Utils;

public class SolarAster implements FitScene {

	private Sphere mSphere;
	private Bitmap[] mTextures;

	public SolarAster() {
		mSphere = new Sphere();
//		mTextures = createTextures();
	}

	public SolarAster(int accuracy) {
		mSphere = new Sphere(accuracy);
//		mTextures = createTextures();
	}

	@Override
	public void fitLlights(GL10 gl) {
		// TODO Auto-generated method stub
		genLights(gl);
	}

	@Override
	public void fitMateral(GL10 gl) {
		// TODO Auto-generated method stub
		genMateral(gl);
	}

	@Override
	public int[] fitTexture(GL10 gl) {
		// TODO Auto-generated method stub
		return genTexturesId(gl);
	}

	@Override
	public void fitDraw(GL10 gl) {
		// TODO Auto-generated method stub
		drawEntire(gl);
	}

	public void drawEntire(GL10 gl) {
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mSphere.getVertexBuffer());
		gl.glNormalPointer(GL10.GL_FLOAT, 0, mSphere.getNormalBuffer());
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mSphere.getTextureBuffer());
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, mSphere.getVertexBuffer().capacity() / 3);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}

	public int[] genTexturesId(GL10 gl) {
		// mTextures = createTextures();
		mTextures = Utils.createSolarSystemTextures();
		int[] textureIds = new int[mTextures.length];
		gl.glGenTextures(textureIds.length, textureIds, 0);
		for (int i = 0; i < textureIds.length; i++) {
			gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIds[i]);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mTextures[i], 0);
			mTextures[i].recycle();
		}
		return textureIds;
	}

//	private Bitmap[] createTextures() {
//		String prefix = "com/cement/gl/aster/source";
//		String[] texturePaths = new String[] { "solar.png", "mercury.png", "venus.png", "earth.png", "mooon.png",
//				"mars.png", "jupiter.png", "saturn.png", "uranus.png", "neptune.png", };
//		Bitmap[] textures = new Bitmap[texturePaths.length];
//		for (int i = 0; i < texturePaths.length; i++) {
//			InputStream ins = getClass().getClassLoader().getResourceAsStream(prefix + texturePaths[i]);
//			Bitmap bitmap = BitmapFactory.decodeStream(ins);
//			textures[i] = bitmap;
//		}
//		return textures;
//	}

//	private Bitmap[] createTextures() {
//		Context context = App.getContext();
//		Bitmap solar = BitmapFactory.decodeResource(context.getResources(), R.drawable.solar);
//		Bitmap mercury = BitmapFactory.decodeResource(context.getResources(), R.drawable.mercury);
//		Bitmap venus = BitmapFactory.decodeResource(context.getResources(), R.drawable.venus);
//		Bitmap earth = BitmapFactory.decodeResource(context.getResources(), R.drawable.world_map);
//		Bitmap moon = BitmapFactory.decodeResource(context.getResources(), R.drawable.mooon);
//		Bitmap mars = BitmapFactory.decodeResource(context.getResources(), R.drawable.mars);
//		Bitmap jupiter = BitmapFactory.decodeResource(context.getResources(), R.drawable.jupiter);
//		Bitmap saturn = BitmapFactory.decodeResource(context.getResources(), R.drawable.saturn);
//		Bitmap uranus = BitmapFactory.decodeResource(context.getResources(), R.drawable.uranus);
//		Bitmap neptune = BitmapFactory.decodeResource(context.getResources(), R.drawable.neptune);
//		Bitmap[] textures = new Bitmap[] { solar, mercury, venus, earth, moon, mars, jupiter, saturn, uranus, neptune };
//		return textures;
//	}

	public void genMateral(GL10 gl) {
		float[] mat_amb = { 0.2f, 0.2f, 0.2f, 0.5f };
		float[] mat_diff = { 0.9f, 0.9f, 0.9f, 0.9f };
		float[] mat_spec = { 1.0f, 1.0f, 1.0f, 1.0f };

		ByteBuffer mabb = ByteBuffer.allocateDirect(mat_amb.length * 4);
		mabb.order(ByteOrder.nativeOrder());
		FloatBuffer mat_ambBuf = mabb.asFloatBuffer();
		mat_ambBuf.put(mat_amb);
		mat_ambBuf.position(0);

		ByteBuffer mdbb = ByteBuffer.allocateDirect(mat_diff.length * 4);
		mdbb.order(ByteOrder.nativeOrder());
		FloatBuffer mat_diffBuf = mdbb.asFloatBuffer();
		mat_diffBuf.put(mat_diff);
		mat_diffBuf.position(0);

		ByteBuffer msbb = ByteBuffer.allocateDirect(mat_spec.length * 4);
		msbb.order(ByteOrder.nativeOrder());
		FloatBuffer mat_specBuf = msbb.asFloatBuffer();
		mat_specBuf.put(mat_spec);
		mat_specBuf.position(0);

		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambBuf);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat_diffBuf);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, mat_specBuf);
		gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 0.0f);
	}

	public void genLights(GL10 gl) {
		float[] amb = { 0.2f, 0.2f, 0.2f, 0.5f, };
		float[] diff = { 0.9f, 0.9f, 0.9f, 0.9f };
		float[] spec = { 1.0f, 1.0f, 1.0f, 1.0f, };
		float[] pos = { 0.0f, 0.0f, 0.0f, 1.0f, };
		// float[] spot_dir = { 0.0f, -1.0f, 0.0f, };

		ByteBuffer abb = ByteBuffer.allocateDirect(amb.length * 4);
		abb.order(ByteOrder.nativeOrder());
		FloatBuffer ambBuf = abb.asFloatBuffer();
		ambBuf.put(amb);
		ambBuf.position(0);

		ByteBuffer dbb = ByteBuffer.allocateDirect(diff.length * 4);
		dbb.order(ByteOrder.nativeOrder());
		FloatBuffer diffBuf = dbb.asFloatBuffer();
		diffBuf.put(diff);
		diffBuf.position(0);

		ByteBuffer sbb = ByteBuffer.allocateDirect(spec.length * 4);
		sbb.order(ByteOrder.nativeOrder());
		FloatBuffer specBuf = sbb.asFloatBuffer();
		specBuf.put(spec);
		specBuf.position(0);

		ByteBuffer pbb = ByteBuffer.allocateDirect(pos.length * 4);
		pbb.order(ByteOrder.nativeOrder());
		FloatBuffer posBuf = pbb.asFloatBuffer();
		posBuf.put(pos);
		posBuf.position(0);

		// ByteBuffer spbb = ByteBuffer.allocateDirect(spot_dir.length*4);
		// spbb.order(ByteOrder.nativeOrder());
		// FloatBuffer spot_dirBuf = spbb.asFloatBuffer();
		// spot_dirBuf.put(spot_dir);
		// spot_dirBuf.position(0);

		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);

		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, ambBuf);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, diffBuf);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, specBuf);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, posBuf);

		float[] mAmbi = { 1.0f, 1.0f, 1.0f, 1.0f };
		gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, mAmbi, 0);
		// gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPOT_DIRECTION, spot_dirBuf);
		// gl.glLightf(GL10.GL_LIGHT0, GL10.GL_SPOT_EXPONENT, 0.0f);
		// gl.glLightf(GL10.GL_LIGHT0, GL10.GL_SPOT_CUTOFF, 45.0f);
	}

}
