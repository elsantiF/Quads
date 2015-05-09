package prub;

import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Matrix4f;

import static org.lwjgl.opengl.GL11.*;

public class Main {

	public static void main(String[] args) {
		
		try{
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Hola!");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(30, 640f / 480f, 0.001f, 100);
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_DEPTH_TEST);

		Point[] points = new Point[1000];
		Random rand = new Random();
		
		for (int i = 0; i < points.length; i++) {

            points[i] = new Point((rand.nextFloat() - 0.5f) * 100f, (rand.nextFloat() - 0.5f) * 100f,
                    rand.nextInt(200) - 200);
        }
		
		float speed = (float) 0.01;
		
		while(!Display.isCloseRequested()){
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glTranslatef(0, 0, speed);
			glBegin(GL_POINTS);
			for(Point i : points){
				glVertex3f(i.x, i.y, i.z);
			}
			glEnd();
			Display.update();
			Display.sync(60);
			
		}
		
		Display.destroy();
		System.exit(0);
		
	}
	
	private static class Point{
		
		final float x;
		final float y;
		final float z;
		
		public Point(float x, float y, float z){
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
	}

}
