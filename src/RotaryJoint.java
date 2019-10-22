//Doreen He
//ID: 260761484


package comp557.a1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import mintools.parameters.DoubleParameter;



public class RotaryJoint extends GraphNode {
	//location
	double x;
	double y;
	double z;
	
	//axis
	double ax;
	double ay;
	double az;
	
	DoubleParameter r;
	
	
	public RotaryJoint(String name ) {
		super(name);
		x = 0;
		y = 0;
		z = 0;
		this.ax = 0;
	    this.ay = 0;
		this.az = 0;
		dofs.add( r = new DoubleParameter( name+" r", 0, -30, 30 ) );		
		
	}
	
	public void setPosition(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public void setAxis(double ax, double ay, double az) {
		this.ax = ax;
	    this.ay = ay;
		this.az = az;
	}
	
	public void setRange( double defaultValue, double min, double max) {
		r.setMinimum(min);
		r.setMaximum(max);
		r.setDefaultValue(defaultValue);
		
	}
	
	
	@Override
	public void display( GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		// TODO: implement the rest of this method​‌​​​‌‌​​​‌‌​​​‌​​‌‌‌​​‌
		gl.glPushMatrix();
		gl.glTranslated(x,y,z);
		gl.glRotated(r.getValue(),ax,ay,az);
		
		//gl.glColor3f(200, 200, 1);
		
		//glut.glutSolidCone(0.5, 5, 6, 6);
		//glut.glutSolidCylinder(0.5, 5, 6, 6);
		super.display(drawable);
		gl.glPopMatrix();
		
		       
		
	}
	

}
