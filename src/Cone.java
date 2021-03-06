//Doreen He
//ID: 260761484


package comp557.a1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;



public class Cone extends Geometry {
	
	public Cone(String name) {
		super(name);
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glPushMatrix();
		gl.glTranslated(tx,ty,tz);
		gl.glScaled(sx,sy,sz);
		gl.glRotated(rx, 1, 0, 0);
		gl.glRotated(ry, 0, 1, 0);
		gl.glRotated(rz, 0, 0, 1);
		gl.glColor3f(red, green, blue);
		glut.glutSolidCone(0.6, 5, 6, 6);
		super.display(drawable);
		gl.glPopMatrix();
		
	}

}
